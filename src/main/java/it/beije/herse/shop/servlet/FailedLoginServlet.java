package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append(request.getParameter("email"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String submit = (String) request.getParameter("submitFailed");
		if(submit!=null && submit.equals("SUBMIT")) {
			
			String action = (String) request.getParameter("failedLoginAction");
			if(action!=null)
				switch(action) {
				case "retry":
					response.sendRedirect("servlet/index.html");
					break;
				case "signIn":
					String email = (String) request.getParameter("email");
					response.sendRedirect("servlet/userMenu.html?email="+email);
					break;
				}
			
		}
		
		doGet(request, response);
	}

}
