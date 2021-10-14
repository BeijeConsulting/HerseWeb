package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserChoiceServlet
 */
@WebServlet("/UserChoiceServlet")
public class UserChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserChoiceServlet() {
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
		String userAction = (String) request.getParameter("userAction");
		if(userAction!=null)
			switch(userAction) {
			case "newOrder":
				response.sendRedirect("neworder.jsp");
				break;
			case "showProfile":
				response.sendRedirect("userprofile.jsp");
				break;
			case "updateProfile":
				response.sendRedirect("updateprofile.jsp");
				break;	
			case "showOrderHistory":
				response.sendRedirect("orderhistory.jsp");
				break;
			}
	}

}
