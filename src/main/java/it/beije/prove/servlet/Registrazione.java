package it.beije.prove.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
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
		String name = request.getParameter("userName");
		String surname = request.getParameter("userSurname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passworCheck = request.getParameter("passwordCheck");

		if (!checkUser(email, password)) {
			if (password.equals(passworCheck) && email.contains("@")) {
				response.sendRedirect("Home.html");
			} else {
				if (!password.equals(passworCheck)) {
					System.out.println("Password errata");
				}
				if (!email.contains("@")) {
					System.out.println("email errata");
				}
				response.sendRedirect("Registrazione.html");
			}
		} else {
			response.sendRedirect("Registrazione.html");
		}

	}

	private boolean checkUser(String email, String password) {
		boolean exist;
		boolean controllo = false;
		// controllo nel db se esiste l'utente
		if (controllo) {
			exist = true;
			System.out.println("l'utente si e gia registrato");
			return exist;
		} else {
			System.out.println("checkuser e ok");
			exist = false;
			return exist;
		}
	}

}
