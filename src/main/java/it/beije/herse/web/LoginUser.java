package it.beije.herse.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.web.entity.User;

@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 String openHtml = "<html><body>";
	 String closeHtml = "</body></html>";
	 String br = "<br>";
   
    public LoginUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected int correctLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User();
		int idUser = -1;
		
		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = "+password;
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			idUser = user.getId();
			request.setAttribute("email", email);
			request.setAttribute("password", password);
//			RequestDispatcher reqDis = request.getRequestDispatcher("menuUser.jsp");
//			reqDis.forward(request, response);
			
			response.sendRedirect("menuUser.jsp");
		} catch (PersistenceException e) {
			response.getWriter().append(openHtml).append("Utente non trovato. Utenticati nuovamente").append("<a href=\"login.html\" >-> Login</a>").append(closeHtml);
		}

		entityManager.close();
		return idUser;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		correctLogin(request, response);
	}

}
