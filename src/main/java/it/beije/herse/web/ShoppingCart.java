package it.beije.herse.web;

import java.io.IOException;

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
import it.beije.herse.web.entity.Product;

@WebServlet("/carrello")
public class ShoppingCart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public ShoppingCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		HttpSession session = request.getSession();
		
		String idProdotto = request.getParameter("id");
		String quantita = request.getParameter("quantita");
		
		System.out.println(idProdotto);
		System.out.println(quantita);
		
		Product product = new Product();
		
		product.setId(Integer.valueOf(idProdotto));
		product.setQuantity(Integer.valueOf(quantita));
		String select = "SELECT p FROM Product AS p WHERE id = "+idProdotto;
		Query query = entityManager.createQuery(select);
		
		Double totale = new Double(0);
		Carrello carrello = new Carrello();
		
		try {
			Product pResult = (Product)query.getSingleResult();
			double prezzo = pResult.getPrice();
			totale = prezzo* Integer.valueOf(quantita);
			
			product.setPrice(totale);
			product.setName(pResult.getName());
			product.setDescription(pResult.getDescription());
			session.setAttribute("productCart", product);
			
			response.sendRedirect("shopping_cart.jsp");
			
		} catch (PersistenceException e) {
			session.setAttribute("error", "Prodotto non presente");
			response.sendRedirect("nuovoOrdine.jsp");
		}

		entityManager.close();
	}

}
