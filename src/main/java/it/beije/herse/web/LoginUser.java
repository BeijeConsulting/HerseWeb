package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

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
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		
		response.getWriter().append("<html><body>password in sessione : ").append(password)
		.append("<br>").append("email in sessione : ").append(email).append("</body></html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		System.out.println("session id : " + session.getId());
		System.out.println("email login: "+email);
		System.out.println("password login: "+password);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		
		User user = new User();

		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = "+password;
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			session.setAttribute("authUser", user);

			response.sendRedirect("menuUser.jsp");
		} catch (PersistenceException e) {
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("loginUser.jsp");
		}

		entityManager.close();
	}

}
