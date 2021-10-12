package it.beije.herse.web;
import Shop.*;
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
		builder.append("<form name='Login' method='post'>");
		builder.append("<p> Inserisci il tuo Id :</p>");
		builder.append("<p> <input type='text' name='idUser'><br> </p>");
		builder.append("<p> <button type='submit'> Invio </button></p></form></body></html>");
		response.getWriter().append(builder);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user = Integer.parseInt(request.getParameter("idUser"));
		if(!Funzioni.login(user))
			response.sendRedirect("LoginServlet");
		else
			response.sendRedirect("CatalogoServlet");
	}
}
