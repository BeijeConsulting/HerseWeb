package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.persistence.Query;

import it.beije.herse.bean.User;
import it.beije.herse.bean.*;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop")
public class Accesso extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Accesso() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		HttpSession session = request.getSession();
//		String username = (String) session.getAttribute("username");
//		String password = (String) session.getAttribute("password");
//		
//		System.out.println("username: " + username);
//		System.out.println("password " + password);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		EntityManager entityManager = ShopEntityManager.newEntityManager(); 

		HttpSession session = request.getSession();
		System.out.println("session id " + session.getId());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT u FROM User as u WHERE email = '").append(username).append("' AND password = '").append(password).append("'");

		String jpqlSelect = jpql.toString();
		Query query = entityManager.createQuery(jpqlSelect);
		List <User> utenti = query.getResultList();
		if(utenti.size()==0) {
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("accesso_ecommerce.jsp");
		}
		else if(utenti.size()==1) {
			System.out.println(utenti.get(0));
			User user =  utenti.get(0);
			session.setAttribute("user", user);
			response.sendRedirect("catalogo.jsp");
		}
		
		
		}
	}


