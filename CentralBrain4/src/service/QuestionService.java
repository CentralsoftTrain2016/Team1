package service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import db.AnswerDao;
import db.QuestionDao;
import db.ViewingAnswerDao;
import domain.Answer;
import domain.Question;
import domain.ViewingAnswer;
import domain.value.InvisiblePeriod;
import domain.value.QuestionID;
import domain.value.UserID;
import service.bean.QuestionBean;
import service.bean.SearchResultBean;

public class QuestionService extends CategoryService {
public	QuestionService(){
	}
	private QuestionDao qdao;
	private AnswerDao adao;
	private ViewingAnswerDao viewDao;
	//使用するDaoをnewする
	@Override
	void prePare() {
		this.qdao = new QuestionDao( this.con);
		this.adao = new AnswerDao( this.con);
		this.viewDao = new ViewingAnswerDao( this.con );

	}


public SearchResultBean getSearchResultBean( String category,String searchWord ){
	start();
	List<Question> question = new ArrayList<Question>();

	SearchResultBean bean = null;
	try {
		if( searchWord.equals("") || searchWord == null ){
			searchWord = "%";
		}

		question = qdao.getSearchQuestion(category,searchWord);

		bean = new SearchResultBean();
		bean.setSearchCategory(category);

		if( searchWord.equals( "%" ) ){
			searchWord = "";
		}

		bean.setSearchWord(searchWord);
		this.getCategories(bean);	// 検索バーのカテゴリ追加分
	}
	catch (SQLException e)
	{
		throw new RuntimeException(e);
	} finally {
		end();
	}

	bean.setQuestion(question);
	return bean;
}

	public QuestionBean getQuestion(UserID uID, QuestionID qID){
		QuestionBean qBean = new QuestionBean();
		Question question;
		Answer bestAnswer;
		List<Answer> answers;
		// QuestionDaoから持ってくる
		try {
			question = qdao.getQuestion(qID);
			qBean.setQuestion( question );
			// Daoから持ってくる
			bestAnswer = adao.getAnswer(question.getBESTANSWERID());
			qBean.setBestAnswer(bestAnswer);
			// Daoから持ってくる
			answers = adao.getAnswers(qID, bestAnswer.getAnswerID());
			qBean.setAnswers(answers);
			this.getCategories(qBean);
			this.toLimitAnswers(qBean, uID);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return qBean;
	}

	/**
	 * 質問閲覧制限メソッド
	 * @param bean 質問ドメイン、質問に対する回答リストが入っている。あとベストアンサーも
	 * @param userId 閲覧した人のユーザID
	 * @return
	 */
	public QuestionBean toLimitAnswers( QuestionBean bean ,UserID userId) throws SQLException{

		// 閲覧する質問が、自分が投稿した質問の場合のみ閲覧制限をつける
		if( bean.getQuestion().getUSERID().get() == userId.get()){

			// 回答の数だけ回す
			for(Answer answer: bean.getAnswers()){
				// 制限ないやつは無視
				if( answer.getINVISIBLEPERIOD().get().equals( "00:00:00" )){
					continue;
				}
				toLimitAnswer( answer, userId );
			}
		}

		// bean返す
		return bean;
	}


	/**
	 * 閲覧できるか一つの回答に対してやるメソッド
	 * 閲覧不可能なら「回答欄」にnullを挿入する
	 * 閲覧可能なら特に何もしない
	 * @param answer 回答の情報が入ってるドメイン
	 * @return
	 * @throws SQLException
	 */
	private Answer toLimitAnswer( Answer answer , UserID userId){

		start();
		ViewingAnswer viewingAnswer;

		// 現在時刻を取得
		LocalDateTime now = LocalDateTime.now();
		// 比較日時
		LocalDateTime ldt;

		try{
			// ViewAnswerテーブルから回答IDを元にデータを取得
			viewingAnswer = viewDao.getViewStartTime( answer.getANSWERID().get() );

			// データがViewingAnswerテーブルになかったら挿入処理
			if( viewingAnswer.getVIEWSTARTDATETIME() == null ){

				// "時,分,秒"形式の閲覧制限時間を取得
				InvisiblePeriod invisiblePeriod = answer.getINVISIBLEPERIOD();

				// 閲覧可能日時を計算
				ldt= now.plus((long)invisiblePeriod.getDuration().toSeconds(), ChronoUnit.SECONDS);
				String strLdt = ldt.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME);

				// 挿入Dao
				viewDao.setViewingAnswer( answer.getANSWERID().get(), userId.get(), strLdt );

			// データがあったら閲覧可能時間を入れる
			} else {
				ldt = viewingAnswer.getVIEWSTARTDATETIME().getLocalDateTime();
			}

			// 現在時刻と閲覧可能時刻を比較し、ダメの場合nullを入れる。
			if( now.isBefore( ldt ) ){
				answer.setAnswerDetail(null);
			}

		}catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			end();
		}

		// 回答ドメインを返す
		return answer;
	}
}


