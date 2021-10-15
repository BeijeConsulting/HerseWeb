package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.manager.UserManager;

/**
 * Servlet implementation class FailedLoginServlet
 */
@WebServlet("/FailedLoginServlet")
public class FailedLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FailedLoginServlet() {
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
		HttpSession session = request.getSession();
	    String failedLoginAction = request.getParameter("failedLoginAction");
	    
	    if(failedLoginAction!=null) {
	    	session.setAttribute("failedLoginAction", failedLoginAction);
	    	if(failedLoginAction.equalsIgnoreCase("signIn")) {
	    		String email = (String) session.getAttribute("email");
	    		String password = (String) session.getAttribute("password");
	    		new UserManager().signIn(email, password);
	    	}
	    	response.sendRedirect("login.jsp");
	    }
	}

}
