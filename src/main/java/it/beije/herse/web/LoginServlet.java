package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder=new StringBuilder();
		builder.append("<html><head><title>Insert title here</title></head><body>");
		builder.append("<form name='Login' action='CatalogoServlet'>");
		builder.append("<p> Inserisci il tuo Id :</p>");
		builder.append("<p> <input type='text' name=Id><br> </p>");
		builder.append("<p> <button type='submit'> Invio </button></p></form></body></html>");
		response.getWriter().append(builder);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
