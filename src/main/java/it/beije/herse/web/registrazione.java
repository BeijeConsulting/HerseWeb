package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.web.MetodiJPA;
import it.beije.herse.web.User;

/**
 * Servlet implementation class registrazione
 */
@WebServlet("/registration")
public class registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory entityManagerFactory;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ServletRegistration");
		HttpSession session = request.getSession();

		

			
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (entityManagerFactory == null) 
			entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
			EntityManager manager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		User user=new User();
		List<User> users =manager.createQuery("select u from User as u").getResultList();
		
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setSurname(surname);
		users.add(user);
		
		for (User u : users) {
			if(u.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Utente registrato!");
				response.sendError(0, "Utente registrato!");
			
			}else {
				manager.persist(user);
				System.out.println("Nuovo utente registrato!");
				session.setAttribute("user", u);
				response.sendRedirect("dashboard.jsp");		
		}

		}
		transaction.commit();
		manager.close();
	}
	
}
