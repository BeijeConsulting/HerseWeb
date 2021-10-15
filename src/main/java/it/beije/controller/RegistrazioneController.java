package it.beije.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.bean.CarrelloNew;
import it.beije.bean.Users;
import it.beije.model.UserModel;

/**
 * Servlet implementation class RegistrazioneController
 */
@WebServlet("/RegistrazioneController")
public class RegistrazioneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneController() {
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
		UserModel userModel = new UserModel();
		Users user = new Users();
		user.setId(null);
		user.setName(request.getParameter("nome"));
		user.setSurname(request.getParameter("cognome"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		String passwordCheck = request.getParameter("passwordCheck");

		if (!userModel.existUser(user)) {
			if (user.getPassword().equals(passwordCheck)) {
				if (user.getEmail().contains("@")) {
					if (!user.getName().equals("") && !user.getSurname().equals("")) {
						CarrelloNew carrello = new CarrelloNew(request);

						userModel.createUser(user);

						session.setAttribute("authUser", user);
						session.setAttribute("carrello", carrello);

						response.sendRedirect("Home.jsp");

					} else {
						// error campi incompleti name e surname
						String error = "error name | surname";
						session.setAttribute("errorEmptyText", error);
						response.sendRedirect("Registrazione.jsp");
					}
				} else {
					// error nella mail
					String error = "error email";
					session.setAttribute("errorEmail", error);
					response.sendRedirect("Registrazione.jsp");
				}
			} else {
				// Error per la psw
				String error = "error password";
				session.setAttribute("errorPassword", error);
				response.sendRedirect("Registrazione.jsp");
			}
		} else {
			// error gia esiste
			String error = "error user";
			session.setAttribute("errorUser", error);
			response.sendRedirect("Registrazione.jsp");
		}
	}

}
