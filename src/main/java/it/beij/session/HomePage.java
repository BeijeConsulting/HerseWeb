package it.beij.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
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
		HttpSession session = request.getSession();

		System.out.println("doGet error: " + session.getAttribute("error"));
		if (session.getAttribute("error") == null) {
			response.sendRedirect("CatchPage.html");
		} else {
			response.sendRedirect("HomePage.jsp");
		}
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

		String user = request.getParameter("user");
		String psw = request.getParameter("password");

		if (user.equals("1") && psw.equals("1")) {

			session.setAttribute("user", user);
			session.setAttribute("password", psw);
			session.removeAttribute("error");

			System.out.println("User: " + user);
			System.out.println("Password: " + psw);

			System.out.println("session post: " + session.getId());

		} else {
			String err = "Log in Error";
			request.setAttribute("error", err);
			session.setAttribute("error", err);
			System.out.println("error: " + request.getAttribute("error"));
		}

		doGet(request, response);
	}

}
