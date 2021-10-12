package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.bean.Users;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Users user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
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
		System.out.println("doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		System.out.println("doPost");

		logIn(request, response);
	}

	public static void logIn(Users users) {
		boolean userExist = true;
		//da implementare il controllo sull'esistenza dellutente
		if (userExist) {
			user.setEmail(users.getEmail());
			user.setPassword(users.getPassword());
			user.setId(users.getId());
			user.setName(users.getName());
			user.setSurname(users.getSurname());
		}
	}

	private void logIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// ....

		System.out.println("email : " + email);
		System.out.println("password : " + password);

		if (email.equalsIgnoreCase("n") && password.equalsIgnoreCase("m")) {
			Users users = new Users();
			// da inizializzare users
			user = users;
			response.sendRedirect("Home.html");
		} else {
			response.sendRedirect("LogIn.html");
		}
	}
}
