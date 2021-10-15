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
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RegistrazioneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String conf_email = request.getParameter("conf_email");
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String password=request.getParameter("password");
		String conf_password = request.getParameter("conf_password");
		HttpSession session=request.getSession();
		switch(ControlloRegistrazione(email,name,surname,password,conf_email,conf_password)) {
		case -1:
			session.setAttribute("error", "Formato e-mail non corretto!");
			response.sendRedirect("Registrazione.jsp");
			break;
		case -2:
			session.setAttribute("error", "Utente già registrato!");
			response.sendRedirect("Registrazione.jsp");
			break;
		case -3:
			session.setAttribute("error", "Le e-mail non corrispondono!");
			response.sendRedirect("Registrazione.jsp");
			break;
		case -4:
			session.setAttribute("error", "Le password non corrispondono!");
			response.sendRedirect("Registrazione.jsp");
			break;
		case 0:
		default:
			User user=Registrazione(email,name,surname,password);
			List<Product> carrello=new ArrayList<Product>();
			List<Integer> qta=new ArrayList<Integer>();
			EntityManager manager=ShopEntityManager.newEntityManager();
			session.setAttribute("manager", manager);
			session.setAttribute("authUser", user);
			session.setAttribute("carrello", carrello);
			session.setAttribute("qta", qta);
		}
		response.sendRedirect("Catalogo.jsp");
	}
	protected  int ControlloRegistrazione(String email, String name,String surname,String password,String conf_email,String conf_password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		if(!email.contains("@"))
			return -1;
		if(!email.equals(conf_email))
			return -3;
		if(!password.equals(conf_password))
			return -4;
		for(User u:users)
			if(u.getEmail().equalsIgnoreCase(email)) 
				return -2;
		return 0;
	}
	protected  User Registrazione(String email,String name,String surname,String password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		User user=new User();
		manager.getTransaction().begin();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setSurname(surname);
		manager.persist(user);
		manager.getTransaction().commit();
		return user;
	}

}
