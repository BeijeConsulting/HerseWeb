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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		
		response.getWriter().append("<html><body>password in sessione : ").append(password)
		.append("<br>").append("email in sessione : ").append(email).append("</body></html>");
	
	}
	
//	protected void correctLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//correctLogin(request, response);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		System.out.println("session id : " + session.getId());
		System.out.println("email"+email);
		System.out.println("password"+password);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		System.out.println("email : " + email);
		System.out.println("password : " + password);

		
		User user = new User();
		int idUser = -1;
//		String select = "SELECT u FROM User AS u";
//		Query query = entityManager.createQuery(select);
//		List<User> listUsers = query.getResultList();
//		System.out.println("list"+listUsers);
		
		
		
//		for(int i = 0; i < listUsers.size(); i++) {
//			if(listUsers.get(i).getEmail().equals(email) && listUsers.get(i).getPassword().equals(password)) {
//				response.sendRedirect("menuUser.jsp");
//			} if(i == listUsers.size()-1) {
//				session.setAttribute("error", "Credenziali Errate");
//				response.sendRedirect("loginError.jsp");
//			} else continue;
//		}
		
		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = "+password;
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			idUser = user.getId();
			session.setAttribute("idUser", idUser);
//			request.setAttribute("email", email);
//			request.setAttribute("password", password);
//			RequestDispatcher reqDis = request.getRequestDispatcher("menuUser.jsp");
//			reqDis.forward(request, response);
			
			response.sendRedirect("menuUser.jsp");
		} catch (PersistenceException e) {
			//response.getWriter().append(openHtml).append("Utente non trovato. Utenticati nuovamente").append("<a href=\"login.html\" >-> Login</a>").append(closeHtml);
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("loginError.jsp");
		}

		entityManager.close();
	}

}
