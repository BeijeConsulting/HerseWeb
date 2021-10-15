package it.beije.prove.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import it.beije.bean.Products;
import it.beije.bean.SingletonEntityManagerFactory;

/**
 * Servlet implementation class EliminaProdotti
 */
@WebServlet("/EliminaProdotti")
public class EliminaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaProdotti() {
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
		HttpSession session = request.getSession();

		Carrello carrello = (Carrello) session.getAttribute("Carrello");

		if (carrello != null) {
			List<Products> products = carrello.getProdotti();
			List<Products> toDelete = new ArrayList<Products>();
			Products precedente = null;
			for (Products p : products) {
				if (precedente != p) {
					precedente = p;
					String param = request.getParameter("p" + precedente.getId());
					int paramInt = Integer.parseInt(param);
					if (paramInt == 0) {
						toDelete.add(precedente);
					}
				}
			}
			
			System.out.println(toDelete.toString());
			
			products.removeAll(toDelete);
			double totDelete=0;
			int quantityDelete=toDelete.size();
			for(Products p : toDelete) {
				totDelete += p.getPrice();
			}
//			for(Products p : products) {
//				for (Products ptd : toDelete) {
//					if(p.equals(ptd)) {
//						products.removeAll((Collection<?>) p);
//					}
//				}
//			}
			totDelete = carrello.getTotale() - totDelete;
			quantityDelete = (int) (carrello.getQuantita()-quantityDelete);
			
			System.out.println(totDelete);
			System.out.println(quantityDelete);
			
			carrello.setProdotti(products);
			carrello.setQuantita(quantityDelete);
			carrello.setTotale(totDelete);
			session.setAttribute("Carrello", carrello);
			response.sendRedirect("CarrelloBis.jsp");
		}

		// TODO Auto-generated method stub
//		doGet(request, response);
//		System.out.println("Post");
//		HttpSession session = request.getSession();
//		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
//		Carrello carrello = (Carrello) session.getAttribute("Carrello");
//		System.out.println(carrello);
//		if (carrello != null && carrello.getProdotti().size() > 0) {
//			String jpqlSelect = "SELECT x FROM Products as x";
//			Query query = entityManager.createQuery(jpqlSelect);
//			List<Products> prodottiNelCarrello = carrello.getProdotti();
//			List<Products> result = query.getResultList();
//
//			for (int i = 0; i < prodottiNelCarrello.size(); i++) {
//				for (Products p : result) {
//					int productId = p.getId();
//					String tagId = request.getParameter("" + productId);
//					if (tagId != null && tagId.equalsIgnoreCase("on")) {
//						if (productId == prodottiNelCarrello.get(i).getId()) {
//							double tot = carrello.getTotale() - p.getPrice();
//							carrello.setTotale(tot);
//
//							double quantity = carrello.getQuantita() - 1;
//							carrello.setQuantita(quantity);
//
//							prodottiNelCarrello.remove(i);
//							if (prodottiNelCarrello.size() <= 0) {
//								break;
//							} else {
//								i--;
//							}
//						}
//
//					}
//				}
//				if (prodottiNelCarrello.size() <= 0) {
//					i = prodottiNelCarrello.size();
//
//				}
//			}
////			
//			System.out.println(carrello);
//			session.setAttribute("Carrello", carrello);
//		}
//		response.sendRedirect("Carrello.jsp");

//		response.sendRedirect("CarrelloBis.jsp");

	}
}
