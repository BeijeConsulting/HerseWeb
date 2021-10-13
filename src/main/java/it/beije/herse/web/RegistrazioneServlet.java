package it.beije.herse.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.bean.*;
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
//		System.out.println("doPost");
		String name = request.getParameter("nome");
		String surname = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");

		if (password.equals(passwordCheck)) {
			EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Users user = new Users();

			user.setId(null);
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password);
			entityManager.persist(user);
			transaction.commit();

			response.sendRedirect("Home.html");
		} else {
			response.sendRedirect("Registrazione.jsp");
			System.out.println("password miss");
//			warningMessage = "<html><body>Inserite 2 password differenti</body></html>";
//			response.getWriter().append(warningMessage);
//			PrintWriter pw = response.getWriter();
//			pw.write(warningMessage);
//			pw.close();
		}
	}

}
