package it.beije.herse.web.my;

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

		List<Integer> listQuantita = new ArrayList<>();
		List<Product> listProduct = new ArrayList<>();
		List<OrderItem> listOrderItem = new ArrayList<>();

		if (idProdotti.length == quantitaProdotti.length) {

			for (int i = 0; i < idProdotti.length; i++) {
				Product product = new Product();
				product.setId(Integer.valueOf(idProdotti[i].trim()));
				listProduct.add(product);
				
				listQuantita.add(Integer.valueOf(quantitaProdotti[i].trim()));

				OrderItem orderItem = new OrderItem();
				orderItem.setProductId(Integer.valueOf(idProdotti[i].trim()));
				listOrderItem.add(orderItem);
			}

			String select = "SELECT p FROM Product AS p";
			Query query = entityManager.createQuery(select);

			Carrello carrelloContenitore = new Carrello();
			HashMap<Product, Integer> cart = new HashMap<>();

			try {
				List<Product> listResultProduct = query.getResultList();
				System.out.println("risultati db" + listResultProduct);

				// verificare quantità sufficiente e procedere solo con quella
				for (int i = 0; i < quantitaProdotti.length; i++) {
					if (listResultProduct.get(i).getQuantity() >= listQuantita.get(i)) {

						listOrderItem.get(i).setQuantity(listQuantita.get(i));
						
						for (Product pResult : listResultProduct) {
							if (listProduct.get(i).getId() == pResult.getId()) {
								listProduct.get(i).setName(pResult.getName());
								listProduct.get(i).setDescription(pResult.getDescription());
								listProduct.get(i).setPrice(pResult.getPrice()); // prezzo del singolo prodotto, non considero ancora la quantita
								listProduct.get(i).setQuantity(pResult.getQuantity());
								listOrderItem.get(i).setSellPrice(pResult.getPrice());

								cart.put(listProduct.get(i), listQuantita.get(i)); // aggiungo i due elemento al carrello, la quantita ha la stessa posizione del prodotto
								System.out.println("lunghezza cart" + cart.size());
								System.out.println("prodotto i" + listProduct.get(i));
								System.out.println("quantita i" + listQuantita.get(i));
								System.out.println("order item i" + listOrderItem.get(i));
								cart.forEach((key, value) -> System.out.println(key + ":" + value));

							}
						}
					} else if (listOrderItem.get(i).getQuantity() < listOrderItem.get(i).getQuantity()) {

						session.setAttribute("quantita","Quantità del prodotto non sufficiente. Inserire una quantità minore.");
						response.sendRedirect("nuovoOrdine.jsp");
					}
				}
				if(cart.size() >=1) {
					
					carrelloContenitore.setCarrello(cart);
					System.out.println("carrello" + carrelloContenitore);					
					session.setAttribute("carrello", carrelloContenitore);
					response.sendRedirect("shopping_cart.jsp");
				} else {
					response.sendRedirect("nuovoOrdine.jsp");
				}
				
			} catch (PersistenceException e) {
				session.setAttribute("error", "Prodotto non presente");
				response.sendRedirect("nuovoOrdine.jsp");
			}
			entityManager.close();
		} else {
			session.setAttribute("quantity_input_error", "Prodotto o quantità non inseriti");
			entityManager.close();
			response.sendRedirect("nuovoOrdine.jsp");
		}

	}

}
