package it.beije.herse.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.web.User;
import it.beije.herse.web.MetodiJPA;
import it.beije.herse.web.ShopEntityManager;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory entityManagerFactory;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		if (entityManagerFactory == null) 
			entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
			EntityManager manager = entityManagerFactory.createEntityManager();
			
			List<User> users=manager.createQuery("select u from User as u").getResultList();
			
			for(User u:users) {
				if(u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
					System.out.println("Login effettuato!");	
					session.setAttribute("user", u);
					response.sendRedirect("dashboard.jsp");
				}else {
					System.out.println("Credenziali errate");
					session.setAttribute("error", "Credenziali Errate");
				//	response.sendRedirect("login.jsp");
					
				}
	}
	}
}
