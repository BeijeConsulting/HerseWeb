package it.beije.prove.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import it.beije.bean.Carrello;
import it.beije.bean.OrderItems;
import it.beije.bean.Orders;
import it.beije.bean.Products;
import it.beije.bean.SingletonEntityManagerFactory;
import it.beije.bean.Users;

/**
 * Servlet implementation class Acquista
 */
@WebServlet("/Acquista")
public class Acquista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Acquista() {
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
		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
		Orders order = new Orders();
		OrderItems orderItems = new OrderItems();
		Users users = (Users) session.getAttribute("authUser");
		Carrello carrello = (Carrello) session.getAttribute("Carrello");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// order
		order.setId(null);
		order.setAmount((int) carrello.getQuantita());
		order.setDateTime(LocalDateTime.now());
		order.setUserId(users.getId());
		
		entityManager.persist(order);

		// orderItems
		for (Products p : carrello.getProdotti()) {
			orderItems.setId(null);
			orderItems.setOrderId(order.getId());
			orderItems.setProductId(p.getId());
			int quantity = getNumberOfItems(carrello, p);
			orderItems.setQuantity(quantity);
			double price = p.getPrice()*quantity;
			orderItems.setSellPrice((int) price);
			entityManager.persist(orderItems);
			
			//products
			Products products = entityManager.find(Products.class, p.getId());;
			int rimasti = products.getQuantity() - quantity;
			p.setQuantity(rimasti);
			entityManager.persist(products);
		}
		
		session.setAttribute("Carrello", carrello);

		transaction.commit();
		response.sendRedirect("Acquista.jsp");
	}

	private int getNumberOfItems(Carrello carrello, Products p) {
		List<Products> prodotti = carrello.getProdotti();
		int count = 0;

		for (int i = 0; i < prodotti.size(); i++) {
			if (p.equals(prodotti.get(i))) {
				count++;
				prodotti.remove(i);
				if (prodotti.size() > 0) {
					i--;
				} else {
					i = prodotti.size();
				}
			}

		}
		return count;
	}

}
