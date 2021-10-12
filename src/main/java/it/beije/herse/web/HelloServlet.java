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
import javax.servlet.http.HttpSession;

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
		User u = new User();
		u.setId(2);
		u.setEmail("anna@frank.de");
		
		response.sendRedirect("prova.jsp?id=" + u.getId() + "&email=" + request.getParameter("username"));
		/*
		response.getWriter().append("Ciao").append(result);
		System.out.println("HelloServlet doGet");

		System.out.println("fname : " + request.getParameter("fname"));
		System.out.println("lname : " + request.getParameter("lname"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		*/

		HttpSession session = request.getSession();
		String fname = (String) session.getAttribute("fname");
		String lname = (String) session.getAttribute("lname");

		System.out.println("fname : " + fname);
		System.out.println("lname : " + lname);
		
		response.getWriter().append("<html><body>fname in sessione : ").append(fname)
		.append("<br>").append("lname in sessione : ").append(lname).append("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet doPost");
		
		String username = request.getParameter("username");
		String result = login(username,request.getParameter("password"));
		User u = new User();
		u.setId(2);
		u.setEmail("anna@frank.de");
		
		response.sendRedirect("prova.jsp?id=" + u.getId() + "&email=" + u.getEmail());

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		HttpSession session = request.getSession();
		System.out.println("session id : " + session.getId());
		session.setAttribute("fname", fname);
		session.setAttribute("lname", lname);
		
		System.out.println("fname : " + fname);
		System.out.println("lname : " + lname);

		if (fname.equalsIgnoreCase("Pippo") && lname.equalsIgnoreCase("Pluto")) {
			response.sendRedirect("herse.jsp");
		} else {
			//response.sendRedirect("index.jsp?error=credenziali errate");
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("index.jsp");
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
