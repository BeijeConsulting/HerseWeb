package it.beije.herse.web;

import java.io.IOException;
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
import it.beije.herse.bean.Product;
import it.beije.herse.bean.ShopEntityManager;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();


		Carrello carrello = (Carrello) session.getAttribute("carrello");

		// succedono cose strane
		Set<Integer> set = carrello.getMappa().keySet();
		Iterator<Integer> indice = set.iterator();
		double total = 0.0;

		while (indice.hasNext()) {

			Integer i = indice.next();
			Integer quantita = carrello.getMappa().get(i);

			Product p = entityManager.find(Product.class,i);
			p.setQuantity(p.getQuantity()-quantita);
			entityManager.persist(p);
		}

		transaction.commit();
		entityManager.close();
		session.removeAttribute("carrello");

		response.getWriter().append("<html><p>Acquisto completato </p> <a href='catalogo.jsp'>Torna al catalogo</a></html>");

	}

}
