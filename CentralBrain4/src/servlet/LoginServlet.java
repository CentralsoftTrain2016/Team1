
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id1 = 0;
		int a = 0;
		int b = 0;
		LoginService ls = new LoginService();
		String pass = null;
		String message = null;
		boolean flg = true;

		try {
			String id = request.getParameter("id");
			pass = request.getParameter("pass");
			id1 = Integer.parseInt(id);
			request.getSession().setAttribute("userID", id1);
		} catch (NumberFormatException e) {
			message = "*数字以外が入力されました";
		}
				if (ls.idComparison(id1) == false) {
					message = "*idが存在しません";
					request.setAttribute("message", message);
					a = 1;
				}if (ls.passComparison(id1, pass) == false) {
					message = "*passwordとidが一致していません";
					request.setAttribute("message", message);
					b = 1;
			}
		if (a == 0 && b == 0) {
			String username = ls.getUserName(id1);
			request.getSession().setAttribute("username", username);
			RequestDispatcher disp = request.getRequestDispatcher("TopServlet");
			disp.forward(request, response);
		} else {
			RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
			disp.forward(request, response);
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
