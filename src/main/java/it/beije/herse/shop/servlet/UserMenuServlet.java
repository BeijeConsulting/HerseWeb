package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;
import it.beije.herse.shop.manager.UserManager;

/**
 * Servlet implementation class UserMenuServlet
 */
@WebServlet("/UserMenuServlet")
public class UserMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String submit = (String) request.getParameter("submitUserAction");
		if(submit!=null && submit.equalsIgnoreCase("SUBMIT")) {
			String userAction = (String) request.getParameter("userAction");
			if(userAction!=null) {
				HttpSession session = request.getSession();
				switch(userAction) {
				case "newOrder":
//					session.setAttribute("prodManager", new ProductManager());
					response.sendRedirect("neworder.jsp");
					break;
				case "showProfile":
					response.sendRedirect("userprofile.jsp");
					break;
				case "updateProfile":
					response.sendRedirect("updateprofile.jsp");
					break;	
				case "showOrderHistory":
//					session.setAttribute("userManager", new UserManager());
//					session.setAttribute("orderManager", new OrderManager());
					response.sendRedirect("orderhistory.jsp");
					break;
				}
			}
		}
		
		String exit = (String) request.getParameter("exit");
		if(exit!=null && exit.equalsIgnoreCase("EXIT")) response.sendRedirect("login.jsp");
	}

}
