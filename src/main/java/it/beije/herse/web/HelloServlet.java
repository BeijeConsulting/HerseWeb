package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.db.Singleton;
import it.beije.herse.db.User;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String result = login(request.getParameter("username"),request.getParameter("password"));
		response.sendRedirect("prova.jsp");
		response.getWriter().append("Ciao").append(result);
		System.out.println("HelloServlet doGet");
		System.out.println("fname : " + request.getParameter("fname"));
		System.out.println("lname : " + request.getParameter("lname"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet doPost");

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");

		//....

		System.out.println("fname : " + fname);

		System.out.println("lname : " + lname);

		if (fname.equalsIgnoreCase("Pippo") && lname.equalsIgnoreCase("Pluto")) {
			response.getWriter().append("BENVENUTO!!!");
		} else {
			response.sendRedirect("index.html");
		}

		//		response.getWriter().append("<html><body>fname : ").append(fname)
		//		.append("<br>").append("lname : ").append(lname).append("</body></html>");
	}


	private static String login(String user,String password) {


		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT user FROM User as user WHERE email='" + user + "' AND PASSWORD='" + password + "'");
		List<User> feed = q.getResultList();

		String result = null;

		if (feed.size()==1) {


			result= "DAJE";


		} else{


			result= "NONE";

		}

		entityManager.close();
		return result;



	}


}
