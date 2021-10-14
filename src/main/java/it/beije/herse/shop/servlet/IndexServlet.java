package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.beans.User;
import it.beije.herse.shop.manager.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "IndexServlet", urlPatterns = { "/IndexServlet" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
//		System.out.println("RequestURI: "+request.getRequestURI());
//		System.out.println("RequestURL: "+request.getRequestURL());
		
		String email = request.getParameter("email"); 
	    String password = request.getParameter("password");
	  
	    if(email!=null && password!=null){
	    	session.setAttribute("email", email);
	    	session.setAttribute("password", password);
	    	if(!UserManager.loginUser(email, password)) response.sendRedirect("failedlogin.jsp");
	    	else {
	    		User loggedUser = UserManager.selectUser(email, password).get(0);
	    		session.setAttribute("loggedUser", loggedUser);
	    		session.removeAttribute("email");
	    		session.removeAttribute("password");
	    		session.removeAttribute("cart");
//	    		session.removeAttribute("order");
//	    		session.removeAttribute("items");
//	    		session.removeAttribute("checked");
	    		
	    		response.sendRedirect("usermenu.jsp");
	    	}
	    }
	}

}
