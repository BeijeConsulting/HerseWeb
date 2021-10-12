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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
      
		response.getWriter().append("torna indietro");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HelloServlet doPost");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User result = login(username,password);
		
		if(result!=null) {
			
			session.setAttribute("user", result);
			response.sendRedirect("ordini");
		} else {
			session.setAttribute("error", "credenziali errate");
			response.sendRedirect("index.jsp?error=dne");
		}
	}


	private static User login(String user,String password) {


		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT user FROM User as user WHERE email='" + user + "' AND password='" + password + "'");
		List<User> feed = q.getResultList();

		User u = null;

		if (feed.size()==1) {
			
            u = feed.get(0);
            System.out.println(u.getName());
		}

		entityManager.close();
		return u;



	}


}
