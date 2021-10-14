package it.beije.herse.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.db.Order;
import it.beije.herse.db.OrderItem;
import it.beije.herse.db.Product;
import it.beije.herse.db.Singleton;
import it.beije.herse.db.User;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/pay")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(session.getAttribute("carrello")==null) {
			response.sendRedirect("catalogo.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("carrello"));
		if((Carrello)session.getAttribute("carrello")==null) {
			response.sendRedirect("catalogo.jsp");
		} else {
			EntityManager entityManager = Singleton.createEntity("herse-shop");
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();


			Carrello carrello = (Carrello) session.getAttribute("carrello");

			Set<Integer> set = carrello.getMappa().keySet();
			Iterator<Integer> indice = set.iterator();
			Double amount = (Double) session.getAttribute("amount");

			// Creo ordine
			Order order = new Order();
			order.setAmount(amount);
			User u = (User)session.getAttribute("user");
			order.setUserId(u.getId());
			order.setCreationDateTime(LocalDateTime.now());
			entityManager.persist(order);


			while (indice.hasNext()) {

				Integer i = indice.next();
				Integer quantita = carrello.getMappa().get(i);

				Product p = entityManager.find(Product.class,i);
				p.setQuantity(p.getQuantity()-quantita);
				// Creo order items
				OrderItem oi = new OrderItem(order.getId(), p.getId(), p.getPrice(), quantita);
				entityManager.persist(oi);
				entityManager.persist(p);
			}

			transaction.commit();
			entityManager.close();
			session.removeAttribute("carrello");

			response.getWriter().append("<html><p>Acquisto completato </p> <a href='catalogo.jsp'>Torna al catalogo</a></html>");
		}
	}

}
