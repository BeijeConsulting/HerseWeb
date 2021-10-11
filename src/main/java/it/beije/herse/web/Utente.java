package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		html.append(INIZIO);
		html.append("<h1>").append("Benvenuto " + Accesso.username).append("</h1>");
		html.append("<h2>").append("Ecco i prodotti che hai acquistato").append("</h2>");
		html.append("<ul>").append("<li>").append("Id ordine Data Amount").append("</li>").append("</lu>");

		html.append("<a href = \"carrello\">").append("<input type=\"submit\" value = \"carrello\"").append("</a>");
		html.append(A_CAPO).append(A_CAPO);
		html.append("<a href = \"prodotti\">").append("<input type=\"submit\" value = \"prodotti\"").append("</a>");
		html.append(FINE);
		
		response.getWriter().append(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
