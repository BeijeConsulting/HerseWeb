package it.beije.herse.web;
import Shop.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session=request.getSession();
		if(Funzioni.login(username,password)!=null) {
			User user=Funzioni.login(username,password);
			List<Product> carrello=new ArrayList<Product>();
			List<Integer> qta=new ArrayList<Integer>();
			session.setAttribute("authUser", user);
			session.setAttribute("carrello", carrello);
			session.setAttribute("qta", qta);
			response.sendRedirect("Catalogo.jsp");
		}else {
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("Login.jsp");
		}
	}
}
