package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TopService;
import service.bean.Bean;

/**
 * Servlet implementation class Top
 */
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userId = request.getParameter("id");
		int userId = (int)request.getSession().getAttribute("userID");
		String username = (String)request.getSession().getAttribute("username");
		TopService topService = null;
		try{
				Bean bean;
				topService = new TopService();
				topService.start();
				bean = topService.createBean();


				bean.setUserID(userId);
				bean.setUsername(username);
				RequestDispatcher disp = request.getRequestDispatcher("top.jsp");
				request.setAttribute("bean", bean);
				disp.forward(request, response);
		}catch( Throwable t)
		{
			topService.rollebackEnd();
			throw t;
		}
		finally
		{
			topService.end();
		}


		RequestDispatcher disp = request.getRequestDispatcher("top.jsp");
		disp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
