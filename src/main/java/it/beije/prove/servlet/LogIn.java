package it.beije.prove.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.bean.Users;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Users> onLineUsers = new ArrayList<Users>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (userExist(email, password)) {
			onLineUsers.add(getUser(email, password));
			response.sendRedirect("Home.html");

		} else {
			response.sendRedirect("LogIn.html");
		}

	}

	private boolean userExist(String email, String psw) {
		boolean exist;
		// Controll che lutente esista all'interno di un db
		// per ora creo un account temp per fare i test
		Users user = new Users();
		user.setId(1);
		user.setName("nico");
		user.setSurname("man");
		user.setEmail("n@");
		user.setPassword("1234");

		if (email.equals(user.getEmail()) && psw.equals(user.getPassword())) {
			System.out.println("Esiste");
			exist = true;
		} else {
			System.out.println("Non esiste");
			exist = false;
		}
		return exist;
	}

	public Users getUser(String email, String psw) {
		Users user = null;
		//user di prova
		user = new Users();
		user.setId(1);
		user.setName("nico");
		user.setSurname("man");
		user.setEmail("n@");
		user.setPassword("1234");
		/////
		//se lutente non compare nella lista dei loggati allora faccio il log
		if (!alreadyLog(user)) {
			// Cerco le info sull'user e lo configuro e lo retituisco per aggiungerlo alla
			// lista, controllando che non sia gia loggato
			user.setId(1);
			user.setName("nico");
			user.setSurname("man");
			user.setEmail("n@");
			user.setPassword("1234");
		} else {
			System.out.println("Utente gia loggato");
		}
		return user;
	}
	
	private boolean alreadyLog(Users user) {
		//Controlla nella lista che non ci sia gia l'utente
		boolean logged = false;
		for (Users users : onLineUsers) {
			if(users.getId().equals(user.getId())) {
				logged = true;
				System.out.println("Already logged");
				return logged;
			}
		}
		return logged;
	}
}
