package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.web.entity.Carrello;
import it.beije.herse.web.entity.OrderItem;
import it.beije.herse.web.entity.Product;

@WebServlet("/carrello")
public class ShoppingCart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ShoppingCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		HttpSession session = request.getSession();

		String idProdotto = request.getParameter("id");
		String quantita = request.getParameter("quantita");

		System.out.println(idProdotto);
		System.out.println(quantita);

		String[] idProdotti = idProdotto.split(",");
		String[] quantitaProdotti = quantita.split(",");

		//List<Integer> listId = new ArrayList<>();
		//List<Integer> listQuantita = new ArrayList<>();
		//List<Product> listProduct = new ArrayList<>();
		List<OrderItem> listOrderItem = new ArrayList<>();
		
		if(idProdotti.length == quantitaProdotti.length) {
			
			for (int i = 0; i < idProdotti.length; i++) {
				//listId.add(Integer.valueOf(idProdotti[i].trim()));
				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(Integer.valueOf(idProdotti[i].trim()));
				orderItem.setQuantity(Integer.valueOf(quantitaProdotti[i].trim()));
				listOrderItem.add(orderItem);
			}

//			for (int i = 0; i < quantitaProdotti.length; i++) {
//				listQuantita.add(Integer.valueOf(quantitaProdotti[i].trim()));
//			}
//			System.out.println("id" + listId);
//			System.out.println("quantita" + listQuantita);

			
			//popolo listProduct
//			for (int i = 0; i < listId.size(); i++) {
//				Product product = new Product();
//				product.setId(listId.get(i));
//				listProduct.add(product);
//				System.out.println(product);
//			}
			
			
			//popolo listOrderItem
//			for(int i = 0; i < listProduct.size(); i++) {
////				OrderItem orderItem = new OrderItem();
////				orderItem.setProductId(listId.get(i));
//				orderItem.setQuantity(listQuantita.get(i));
//				orderItem.setSellPrice(listProduct.get(i).getPrice());
//				listOrderItem.add(orderItem);
//			}

			//System.out.println("numero prodotti" + listProduct.size());

			String select = "SELECT p FROM Product AS p";
			Query query = entityManager.createQuery(select);

			Carrello carrelloContenitore = new Carrello();
			HashMap<Product, Integer> cart = new HashMap<>();

			try {
				List<Product> listResult = query.getResultList();
				System.out.println("risultati db" + listResult);

//				for (int i = 0; i < listOrderItem.size(); i++) {
//					for (Product p : listResult) {
//						if (listProduct.get(i).getId() == p.getId()) {
//							listProduct.get(i).setName(p.getName());
//							listProduct.get(i).setDescription(p.getDescription());
//							listProduct.get(i).setPrice(p.getPrice()); // prezzo del singolo prodotto, non considero ancora la quantita
//
//							cart.put(listProduct.get(i), listQuantita.get(i)); // aggiungo i due elemento al carrello, la quantita ha la stessa posizione del prodotto
//							System.out.println("lunghezza cart" + cart.size());
//							System.out.println("prodotto i" + listProduct.get(i));
//							System.out.println("quantita i" + listQuantita.get(i));
//							cart.forEach((key, value) -> System.out.println(key + ":" + value));
//						} 
//					}
//				}

				carrelloContenitore.setCarrello(cart);
				System.out.println("carrello" + carrelloContenitore);

				session.setAttribute("carrello", carrelloContenitore);

				response.sendRedirect("shopping_cart.jsp");

			} catch (PersistenceException e) {
				session.setAttribute("error", "Prodotto non presente");
				response.sendRedirect("nuovoOrdine.jsp");
			}

			entityManager.close();
		} else {
			session.setAttribute("quantity_input_error", "Prodotto o quantità non inseriti");
			response.sendRedirect("nuovoOrdine.jsp");
		}
		
	}

}
