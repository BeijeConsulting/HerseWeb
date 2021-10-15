package it.beije.herse.web;
import Shop.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session=request.getSession();
		if(login(username,password)!=null) {
			User user=login(username,password);
			Order order=newOrder(user.getId());
			List<Carrello> carrello=new ArrayList<Carrello>();
			EntityManager manager=ShopEntityManager.newEntityManager();
			session.setAttribute("manager", manager);
			session.setAttribute("authUser", user);
			session.setAttribute("order", order);
			session.setAttribute("carrello", carrello);
			response.sendRedirect("Catalogo.jsp");
		}else {
			session.setAttribute("error", "Credenziali Errate");
			response.sendRedirect("Login.jsp");
		}
	}
	protected User login(String username, String password) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<User> users=manager.createQuery("select u from User as u").getResultList();
		for(User u:users)
			if(u.getEmail().equalsIgnoreCase(username)&&u.getPassword().equals(password)) 
				return u;
		return null;
	}
	protected  Order newOrder(Integer userId) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		Order order=new Order();
		manager.getTransaction().begin();
		order.setAmount(0.0);
		order.setDataTime(LocalDateTime.now());
		order.setUserId(userId);
		manager.persist(order);
		manager.getTransaction().commit();
		return order;
	}
}
