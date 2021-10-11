package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Shop.Funzioni;

/**
 * Servlet implementation class EndServlet
 */
@WebServlet("/EndServlet")
public class EndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder=new StringBuilder();
		builder.append("<html><head><title>Insert title here</title></head><body><form name='End' method='post'>");
		builder.append("<p>Acquisto completato!</p><p><button type='submit'>Ritorna alla HomePage</button>");
		builder.append("</p></form></body></html>");
		response.getWriter().append(builder);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Funzioni.inserisciOrdineItem();
		response.sendRedirect("LoginServlet");
	}

}
