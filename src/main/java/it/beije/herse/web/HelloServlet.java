package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet doGet");
		
		HttpSession session = request.getSession();
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");

		System.out.println("fname : " + fname);
		System.out.println("lname : " + lname);
		
		response.getWriter().append("<html><body>fname in sessione : ").append(fname)
		.append("<br>").append("lname in sessione : ").append(lname).append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet doPost");

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		HttpSession session = request.getSession();
		System.out.println("session id : " + session.getId());
		session.setAttribute("fname", fname);
		session.setAttribute("lname", lname);
		 
		//....

		System.out.println("fname : " + fname);
		System.out.println("lname : " + lname);
		

//		if (fname.equalsIgnoreCase("Pippo") && lname.equalsIgnoreCase("Pluto")) {
//			response.getWriter().append("BENVENUTO!!!");
//		} else {
//			response.sendRedirect("index.html");
//		}

		if (fname.equalsIgnoreCase("Pippo") && lname.equalsIgnoreCase("Pluto")) {
			response.sendRedirect("herse.jsp");
		} else {
			//response.sendRedirect("index.jsp?error=credenziali errate");
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("index.jsp");
		}

		
		response.getWriter().append("<html><body>fname : ").append(fname)
		.append("<br>").append("lname : ").append(lname).append("</body></html>");
	}

}
