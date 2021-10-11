package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		StringBuilder html = new StringBuilder();
		html.append(INIZIO).append("<form action = \"shop\" method = \"post\">");
		html.append("<label for = \"username\">").append("Username").append("</label>").append(A_CAPO);
		html.append("<input type = \"text\" name = \"username\">").append(A_CAPO);
		html.append("<label for = \"password\">").append("Password").append("</label>").append(A_CAPO);
		html.append("<input type = \"password\" name = \"password\">").append(A_CAPO);
		html.append("<input type = \"submit\" value = \"submit\"");
		html.append(FINE);
		response.getWriter().append(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 username = request.getParameter("username");
		System.out.println("L'username è " + username);
		String password = request.getParameter("password");
		System.out.println("La password è " + password);
		
		response.sendRedirect("utente");
		doGet(request, response);
	}

}
