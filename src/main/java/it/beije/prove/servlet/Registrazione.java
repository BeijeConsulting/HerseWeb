package it.beije.prove.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.bean.Carrello;
import it.beije.bean.SingletonEntityManagerFactory;
import it.beije.bean.Users;

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
		HttpSession session = request.getSession();
		String name = request.getParameter("nome");
		String surname = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passworCheck = request.getParameter("passwordCheck");
		String emailError = "Email errata";
		String passwordError = "Password errata";
		String userError = "Utente gia esistente";

		if (!checkUser(email, password)) {
			if (password.equals(passworCheck)) {
				if (email.contains("@")) {
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
					session.setAttribute("authUser", user);
					Carrello carrello = new Carrello(request);
					session.setAttribute("Carrello", carrello);
					response.sendRedirect("Home.jsp");
				}else {
					request.setAttribute("emailError", emailError);
					session.setAttribute("emailError", emailError);
					response.sendRedirect("Registrazione.jsp");				}
			} else {
				request.setAttribute("passwordError", passwordError);
				session.setAttribute("passwordError", passwordError);
				response.sendRedirect("Registrazione.jsp");
			}
		} else {
			request.setAttribute("userError", userError);
			session.setAttribute("userError", userError);
			response.sendRedirect("Registrazione.jsp");
		}

	}

	private boolean checkUser(String email, String password) {
		boolean exist = false;

		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();

		String jpqlSelect = "SELECT x FROM Users as x";
		Query query = entityManager.createQuery(jpqlSelect);

		List<Users> result = query.getResultList();
		for (Users us : result) {
			if (us.getEmail().equals(email)) {
				exist = true;
				return exist;
			}
		}
		return exist;
	}

}
