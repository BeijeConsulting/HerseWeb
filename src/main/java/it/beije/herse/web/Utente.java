package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Utente
 */
@WebServlet("/utente")
public class Utente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String  A_CAPO = "<br>";
	public static final String  INIZIO = "<html><body>";
	public static final String  FINE = "</html></body>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder html = new StringBuilder();


		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");

		html.append(INIZIO);
		html.append("<h1>").append("Benvenuto ").append(username).append("</h1>").append(A_CAPO);
		html.append("<form action = \"carrello.jsp\" method = \"post\">");
		html.append("  <input type=\"submit\"  value=\"Carrello\">").append(A_CAPO);
		html.append("</form>");
		html.append("<form action = \"prodotti\" method = \"post\">");
		html.append("  <input type=\"submit\"  value=\"Ordini\">");
		html.append("</form>");
		System.out.println("username " + username);
		System.out.println("password " + password);



		response.getWriter().append(html.toString());

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.sendRedirect("carrello.jsp");
	}
}