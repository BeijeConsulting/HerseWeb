package it.beije.herse.web;

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

import it.beije.herse.db.Order;
import it.beije.herse.db.Singleton;
import it.beije.herse.db.User;

/**
 * Servlet implementation class provaC
 */
@WebServlet("/prova")
public class provaC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public provaC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// QUESTA SERVLET è DI PROVA 
		EntityManager entityManager = Singleton.createEntity("herse-shop");
		HttpSession session = request.getSession();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		User user = (User)session.getAttribute("user");
		User u = entityManager.find(User.class,user.getId());
		u.setId(22);
		User nuovo = metodoProva(u);
		
		entityManager.persist(u);
		transaction.commit();
		entityManager.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static User metodoProva(User user) {
		user.setSurname("pallo");
		return user;
	}

}
