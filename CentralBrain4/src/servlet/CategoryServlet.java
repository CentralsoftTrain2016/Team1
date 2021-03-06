//package servlet;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import service.CategoryService;
//import service.bean.CategoryBean;
//
///**
// * Servlet implementation class CategoryServlet
// */
//@WebServlet("/CategoryServlet")
//public class CategoryServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public CategoryServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		/* カテゴリーサービスのインスタンス生成 */
//		CategoryService categoryService = new CategoryService();
//
//		/* カテゴリーの情報をビーンにセット */
//		CategoryBean bean = categoryService.getCategoryBean();
//
//		request.setAttribute("bean", bean);
//		RequestDispatcher disp = request.getRequestDispatcher("category.jsp");
//		disp.forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
