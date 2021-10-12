package ShopSession;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ecommerce.Order;

/**
 * Servlet implementation class ServletShop
 */
@WebServlet("/ServletShop")
public class ServletShop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletShop() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ServletShop doGet");

		String user = request.getParameter("userId");

		HttpSession session = request.getSession();
		System.out.println("Session id: " + session.getId());

		int userId = Integer.parseInt(user);

		session.setAttribute("userId", userId);

		Boolean exists = Shop.existUser(userId);

		if(exists == false){
			session.setAttribute("error", "Credenziai Errate");
			response.sendRedirect("indexSession.jsp");
		} else {	
			response.sendRedirect("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("ServletShop doPost");

		String user = request.getParameter("userId");

		HttpSession session = request.getSession();
		System.out.println("Session id: " + session.getId());

		int userId = Integer.parseInt(user);

		session.setAttribute("userId", userId);

		Boolean exists = Shop.existUser(userId);

		if(exists == false){
			session.setAttribute("error", "Credenziai Errate");
			response.sendRedirect("indexSession.jsp");
		} else {	
			int orderId = Shop.insertOrder(userId);
			session.setAttribute("orderId", orderId);
			System.out.println("ServletShop OrderId"+orderId);
			response.sendRedirect("sessionForm.jsp");
			
		}


	}


}
