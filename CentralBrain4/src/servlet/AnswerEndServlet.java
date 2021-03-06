package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.value.InvisiblePeriod;
import service.AnswerService;
import service.bean.QuestionBean;

/**
 * Servlet implementation class AnswerEndSevlet
 */
@WebServlet("/AnswerEndServlet")
public class AnswerEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnswerEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String QuestionID = request.getParameter("questionID");

		String answerDetail = request.getParameter("answerDetail");

		// メッセージ用変数
		String message = null;

		// 文字化けのおまじない
		answerDetail = new String(answerDetail.getBytes("ISO8859-1"), "UTF-8");
		// 字数確認
		if (answerDetail.length() > 2000) {
			// 文字数オーバーメッセージをセットする
//			message = "※解答欄の文字数が多すぎます";
//			request.setAttribute("message", message);
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		} else if (answerDetail.length() < 10) {
			// 文字数アンダーメッセージをセットする
//			message = "※解答欄の文字数が足りません";
//			request.setAttribute("message_ans", message);
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		}
		// 閲覧制限解除までの時
		String viewableTimeH = request.getParameter("viewableTimeH");
		// 分
		String viewableTimeM = request.getParameter("viewableTimeM");
		// 秒
		String viewableTimeS = request.getParameter("viewableTimeS");

		String adviceDetail = request.getParameter("adviceDetail");
		// 文字化けのおまじない
		adviceDetail = new String(adviceDetail.getBytes("ISO8859-1"), "UTF-8");
		if (adviceDetail.length() > 2000) {
			// 文字数オーバーメッセージをセットする
//			message = "※アドバイス欄の文字数が多すぎます";
//			request.setAttribute("message_adv", message);
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		} else if (adviceDetail.length() < 10) {
			// 文字数アンダーメッセージをセットする
//			message = "※アドバイス欄の文字数が足りません";
//			request.setAttribute("message_adv", message);
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		}

		// ↓実際はこっちを使う
		// HttpSession s = request.getSession();
		// int userID = (int) s.getAttribute("userID");
		// ↓最終的にはここをコメントアウトしてください
		int userID = 000;

		int questionID = Integer.parseInt(QuestionID);

		// 回答文字が空白の場合は元の画面に戻る
		if (answerDetail == null || answerDetail.equals("")) {
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		}
		// アドバイス文字が空白の場合は元の画面に戻る
		if (adviceDetail == null || adviceDetail.equals("")) {
			RequestDispatcher disp = request.getRequestDispatcher("AnswerServlet");
			disp.forward(request, response);
			return;
		}
		// 閲覧制限時間の時・分・秒それぞれについて、空欄の場合は0に設定する。
		// 今はベタ書きなので、あとでうまいことまとめる
		if (viewableTimeH == null || viewableTimeH.equals("")) {
			viewableTimeH = "0";
		}
		if (viewableTimeM == null || viewableTimeM.equals("")) {
			viewableTimeM = "0";
		}
		if (viewableTimeS == null || viewableTimeS.equals("")) {
			viewableTimeS = "0";
		}
		// 閲覧制限時間の時・分・秒それぞれについて、範囲を超える値ならば元の画面に戻る
		int hi = Integer.parseInt(viewableTimeH);
		int mi = Integer.parseInt(viewableTimeM);
		int si = Integer.parseInt(viewableTimeS);
		if ((hi < 0) || (hi > 24) || (mi < 0) || (mi > 59) || (si < 0) || (si > 59)) {
			// 文字数アンダーメッセージをセットする
//			message = "※閲覧制限時間は 0h0m0s ～ 23h59m59s の間で設定してください。";
//			request.setAttribute("message_adv", message);
			RequestDispatcher disp = request.getRequestDispatcher("answer.jsp");
			disp.forward(request, response);
		}
		// 閲覧制限の時間を文字列結合する（例・・・ 12:30:00）
		String viewableTime = viewableTimeH + ":" + viewableTimeM + ":" + viewableTimeS;
		InvisiblePeriod ip = new InvisiblePeriod(viewableTimeH, viewableTimeM, viewableTimeS);
		AnswerService aService = new AnswerService();
		aService.start();
		QuestionBean bean = new QuestionBean();
		try {


			// ※postDateとviewableTimeから、viewableDateを求める。
			// String viewableDate = aService.getViewableTime(postDate,
			// viewableTime);
			aService.setAnswer(questionID, answerDetail, ip, adviceDetail, userID);
			bean = aService.getMyAnswerQuestionID(questionID);

			RequestDispatcher disp = request.getRequestDispatcher("answerEnd.jsp");
			request.setAttribute("bean", bean);
			disp.forward(request, response);

		} catch (Throwable t) {
			aService.rollebackEnd();
			throw t;
		} finally {
			aService.end();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
