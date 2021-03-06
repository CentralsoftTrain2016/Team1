/* Replyテーブルに関するDAO
 */

package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Answer;
import domain.value.AdviceDetail;
import domain.value.AnswerDetail;
import domain.value.AnswerID;
import domain.value.BestAnswerFlag;
import domain.value.InvisiblePeriod;
import domain.value.NumberOfGood;
import domain.value.PostDate;
import domain.value.QuestionID;
import domain.value.UserID;

public class AnswerDao extends Dao {

	public AnswerDao(Connection con) {
		super(con);
	}

	/**
	 * 回答を入力するメソッド
	 *
	 * @param questionID
	 *            質問ID
	 * @param answerDetail
	 *            質問内容
	 * @param viewableDate
	 *            閲覧制限
	 * @param adviceDetail
	 *            アドバイス
	 * @param postDate
	 *            日時
	 * @param userId
	 *            ユーザーID
	 * @throws SQLException
	 */
	public void setAnswer(int questionID, String answerDetail, InvisiblePeriod ip, String adviceDetail, int userId,
			String postDate) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {

			/* Statementの作成 */
			stmt = con.prepareStatement("insert into Answers("
					+ "  AnswerID, QUESTIONID,AnswerDetail,InvisiblePeriod,AdviceDetail,PostDate,UserID) " + "values"
					+ "    ( AnswerID_seq.nextval,?,?,?,?,?,? ) ");

			stmt.setInt(1, questionID);
			stmt.setString(2, answerDetail);
			stmt.setString(3, ip.get());
			stmt.setString(4, adviceDetail);
			stmt.setString(5, postDate);
			stmt.setInt(6, userId);

			/* ｓｑｌ実行 */
			stmt.executeUpdate();

		}

		catch (SQLException e) {
			throw e;
		} finally {
			/* クローズ処理 */
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
		}

	}

	private static String GETANSWER_SQL = "SELECT " + "	ANSWERID	," + "	QUESTIONID	," + "	NUMBEROFGOOD	,"
			+ "	BESTANSWERFLAG	," + "	USERID	," + "	ANSWERDETAIL	," + "	INVISIBLEPERIOD	,"
			+ "	ADVICEDETAIL	," + "	POSTDATE	" + "	FROM 	" + "	Answers	" + "	WHERE	"
			+ "	ANSWERID = ?	" + "order by ANSWERID";

	public Answer getAnswer(AnswerID aID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Answer answer;
		try {
			/* Questionsテーブルから最新のQuestionIDを取得 */
			stmt = con.prepareStatement(GETANSWER_SQL);
			// SQL実行
			stmt.setInt(1, aID.get());
			answer = new Answer();
			rset = stmt.executeQuery();
			while (rset.next()) {
				answer.setAnswerID(new AnswerID(rset.getInt(1)));
				answer.setQuestionID(new QuestionID(rset.getInt(2)));
				answer.setNUMBEROFGOOD(new NumberOfGood(rset.getInt(3)));
				answer.setBestAnswerFlag(new BestAnswerFlag(rset.getInt(4)));
				answer.setUSERID(new UserID(rset.getInt(5)));
				answer.setANSWERDETAIL(new AnswerDetail(rset.getString(6)));
				answer.setINVISIBLEPERIOD(new InvisiblePeriod(rset.getString(7)));
				answer.setAdviceDetail(new AdviceDetail(rset.getString(8)));
				answer.setPostDate(new PostDate(rset.getString(9)));
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			/* クローズ処理 */
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				stmt = null;
			}
		}
		return answer;
	}

	private static String GETANSWERS_SQL = "SELECT " + "	ANSWERID	," + "	QUESTIONID	," + "	NUMBEROFGOOD	,"
			+ "	BESTANSWERFLAG	," + "	USERID	," + "	ANSWERDETAIL	," + "	INVISIBLEPERIOD	,"
			+ "	ADVICEDETAIL	," + "	POSTDATE	" + "	FROM 	" + "	Answers	" + "	WHERE	"
			+ "	QUESTIONID = ?	" + "  ORDER BY ANSWERID desc";

	public List<Answer> getAnswers(QuestionID qID, AnswerID bestAnswerID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<Answer> answers = new ArrayList<Answer>();;
		try {
//			answers = new ArrayList<Answer>();
			/* Questionsテーブルから最新のQuestionIDを取得 */
			stmt = con.prepareStatement(GETANSWERS_SQL);
			// SQL実行
			stmt.setInt(1, qID.get());

			rset = stmt.executeQuery();
			while (rset.next()) {
				Answer answer = new Answer();
				answer.setAnswerID(new AnswerID(rset.getInt(1)));
				answer.setQuestionID(new QuestionID(rset.getInt(2)));
				answer.setNUMBEROFGOOD(new NumberOfGood(rset.getInt(3)));
				answer.setBestAnswerFlag(new BestAnswerFlag(rset.getInt(4)));
				answer.setUSERID(new UserID(rset.getInt(5)));
				answer.setANSWERDETAIL(new AnswerDetail(rset.getString(6)));
				answer.setINVISIBLEPERIOD(new InvisiblePeriod(rset.getString(7)));
				answer.setAdviceDetail(new AdviceDetail(rset.getString(8)));
				answer.setPostDate(new PostDate(rset.getString(9)));
				if (bestAnswerID == null) {
					answers.add(answer);
				}
			}

		} catch (SQLException e) {
			throw e;

		} finally {
			/* クローズ処理 */
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				stmt = null;
			}
		}
		return answers;
	}

	public void setFlag(int questionID) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(
					"update " + "Questions " + "SET " + "AnswerFlag = 1 " + "WHERE " + "questionID = ?");

			stmt.setInt(1, questionID);

			/* ｓｑｌ実行 */
			//rset = stmt.executeQuery();
			stmt.executeUpdate();

		}

		catch (SQLException e) {
			throw e;
		} finally {
			/* クローズ処理 */
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
		}

	}
	// //未解答の質問を取ってくる
	// public List<UnansweredBean> unanswered() throws SQLException {
	//
	// PreparedStatement stmt = null;
	// ResultSet rset = null;
	//
	// List<UnansweredBean> list = new ArrayList<UnansweredBean> ();
	//
	// try{
	//
	// /* Statementの作成 */
	// stmt = con.prepareStatement(
	// "select "
	// + " q.QuestionID, q.QuestionDetail "
	// + "from"
	// + " Questions as q, Categories as c, QuestionsCategories as qc "
	// + "where"
	// + " q.QuestionID = qc.QuestionID and qc.CategoryID = c.CategoryID "
	// + "and"
	// + " c.CategoryName = ? "
	// + "and"
	// + " q.QuestionDetail Like '%?%' "
	// );
	//
	//
	// /* ｓｑｌ実行 */
	// rset = stmt.executeQuery();
	//
	// /* 取得したデータを表示します。 */
	// while (rset.next())
	// {
	// Question em = new Question();
	// em = new Question();
	// em.setQuestionID( new QuestionID (rset.getInt(1)) );
	// em.setQuestionDetail( new QuestionDetail(rset.getString(2)));
	//
	// list.add(em);
	//
	// }
	// }
	//
	// catch (SQLException e) {
	// throw e;
	// }
	// finally{
	// /* クローズ処理 */
	// if(stmt != null){
	// stmt.close();
	// stmt = null;
	// }
	// if(rset != null){
	// rset.close();
	// rset = null;
	// }
	// }
	//
	// return list;
	// }
	//
	//
	//
	//
	//
	//

}
