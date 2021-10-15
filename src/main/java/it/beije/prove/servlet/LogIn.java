package it.beije.prove.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.bean.Carrello;
import it.beije.bean.CarrelloNew;
import it.beije.bean.SingletonEntityManagerFactory;
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
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Users users = checkUser(email, password, request, response);
		if (users != null) {
			session.setAttribute("authUser", users);
//			Carrello carrello = new Carrello(request);
//			session.setAttribute("Carrello", carrello);
			CarrelloNew carrelloNew = new CarrelloNew(request);
			session.setAttribute("CarrelloNew", carrelloNew);
			response.sendRedirect("Home.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	private Users checkUser(String email, String password, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
		HttpSession session = request.getSession();

		String jpqlSelect = "SELECT x FROM Users as x";
		Query query = entityManager.createQuery(jpqlSelect);

		List<Users> result = query.getResultList();
		for (Users us : result) {
			boolean emailEqual = us.getEmail().equalsIgnoreCase(email);
			boolean paswEqual = us.getPassword().equals(password);
			if (emailEqual && paswEqual) {
				return us;
			} else {
				if (us.getEmail().equals(email)) {

					String passwordError = "Password errata";

					request.setAttribute("passwordError", passwordError);
					session.setAttribute("passwordError", passwordError);
				}
			}
		}
		return null;
	}
}
