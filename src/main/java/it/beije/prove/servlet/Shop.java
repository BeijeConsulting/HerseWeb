package it.beije.prove.servlet;

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

import it.beije.bean.Carrello;
import it.beije.bean.CarrelloNew;
import it.beije.bean.OrderItems;
import it.beije.bean.Products;
import it.beije.bean.SingletonEntityManagerFactory;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Shop() {
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
//		HttpSession session = request.getSession();
//		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
//		Carrello carrello = (Carrello) session.getAttribute("Carrello");
//
//		if (carrello != null) {
//			String jpqlSelect = "SELECT x FROM Products as x";
//			Query query = entityManager.createQuery(jpqlSelect);
//
//			List<Products> result = query.getResultList();
//			for (Products p : result) {
//				int productId = p.getId();
//				String tagId = request.getParameter("" + productId);
//				if (tagId != null && !tagId.equals("")) {
//					int quantity = Integer.parseInt(tagId);
//					double tot = carrello.getTotale() + p.getPrice() * quantity;
//					carrello.setTotale(tot);
//
//					double TotQuantity = carrello.getQuantita() + quantity;
//					carrello.setQuantita(TotQuantity);
//
//					for (int i = 0; i < quantity; i++) {
//						carrello.getProdotti().add(p);
//					}
//
//					session.setAttribute("Carrello", carrello);
//				}
//			}
//
//			response.sendRedirect("CarrelloBis.jsp");
//		}else {
//			String authError = "Effettua prima il log in o registrati";
//			request.setAttribute("authError", authError);
//			session.setAttribute("authError", authError);
//			response.sendRedirect("Prodotti.jsp");
//		}

		HttpSession session = request.getSession();
		CarrelloNew carrello = (CarrelloNew) session.getAttribute("CarrelloNew");
		if (carrello != null) {
			EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
			String jpqlSelect = "SELECT x FROM Products as x";
			Query query = entityManager.createQuery(jpqlSelect);

			List<Products> result = query.getResultList();
			if (carrello.getOrderItems().size() != 0) {
				for (Products products : result) {
					int productId = products.getId();
					String tagId = request.getParameter("" + productId);
					if (tagId != null && tagId.equals("")) {
						OrderItems orderItems = new OrderItems();

						orderItems.setId(null);
						orderItems.setOrderId(null);
						orderItems.setProductId(products.getId());
						int quantity = Integer.parseInt(tagId);
						orderItems.setQuantity(quantity);
						double price = products.getPrice() * quantity;
						orderItems.setSellPrice((int) price);

						carrello.addOrderItems(orderItems);
					}
				}

				response.sendRedirect("CarrelloTris.jsp");
			}else {
				String carrelloError = "Carrello vuoto";
				request.setAttribute("carrelloError", carrelloError);
				session.setAttribute("carrelloError", carrelloError);
				response.sendRedirect("Prodotti.jsp");
			}
		} else {
			String authError = "Effettua prima il log in o registrati";
			request.setAttribute("authError", authError);
			session.setAttribute("authError", authError);
			response.sendRedirect("Prodotti.jsp");
		}
	}

}
