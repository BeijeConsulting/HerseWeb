package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop")
public class Accesso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String  A_CAPO = "<br>";
	public static final String  INIZIO = "<html><body>";
	public static final String  FINE = "</html></body>";

	public static String username;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accesso() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		System.out.println("username: " + username);
		System.out.println("password " + password);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		System.out.println("session id " + session.getId());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		if(username.equalsIgnoreCase("Max"))
			response.sendRedirect("utente");
		else {
			session.setAttribute("error","Credenziali errate");
			response.sendRedirect("Accesso.jsp");
		}
	}

}
