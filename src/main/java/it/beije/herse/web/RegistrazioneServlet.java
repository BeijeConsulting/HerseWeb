package it.beije.herse.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.bean.Users;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneServlet() {
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
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String warningMessage;

		if (name.equals("")) {
			String nameWarning = "inserire Nome";
		}
		if (surname.equals("")) {
			String surnameWarning = "inserire Cognome";
		}
		if (email.equals("")) {
			String emailWarning = "Inserire nome";
			if (!email.contains("@")) {
				String emailError = "L'email non e corretta";
			}
		}
		if (password.equals("")) {
			String passwordWarning = "inserire Password";
		}
		if (passwordCheck.equals("")) {
			String passwordCheckWarning = "inserire Password di conferma";
		}
		if (password.equals(passwordCheck)) {
			Users user = new Users();
			user.setId(null);
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password);
			addUserToDb(user);
			LogInServlet.logIn(user);
			response.sendRedirect("Home.html");
		} else {
			response.sendRedirect("Registrazione.html");
			System.out.println("password miss");
//			warningMessage = "<html><body>Inserite 2 password differenti</body></html>";
//			response.getWriter().append(warningMessage);
//			PrintWriter pw = response.getWriter();
//			pw.write(warningMessage);
//			pw.close();
		}
	}
	
	private void addUserToDb(Users user) {
		//Aggiungo user al database
	}


}
