package it.beije.herse.web.my;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.web.entity.Carrello;
import it.beije.herse.web.entity.Order;
import it.beije.herse.web.entity.OrderItem;
import it.beije.herse.web.entity.Product;

@WebServlet("/ConfermaOrdine")
public class ConfermaOrdine extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String openHtml = "<html>";
	String openBody = "<body style=\"margin:1%\">";
	String closeHtml = "</html>";
	String closeBody = "</body>";
	String br = "<br>";

    public ConfermaOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Carrello carrello = (Carrello)session.getAttribute("carrello");
		System.out.println("carrello"+carrello);
		
		it.beije.herse.web.entity.User user = (it.beije.herse.web.entity.User)session.getAttribute("authUser");
		System.out.println(user);
		if( user != null) {
			Double total = (Double)session.getAttribute("total");
			
			response.getWriter().append(openHtml)
			.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
			.append(openBody)
			.append("<h1>Ordine confermato</h1>")
			.append("<a href=\"menuUser.jsp\" style=\"text-decoration: none; color:blue;\">-> Torna al menu</a>");
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			Order order = new Order();
			order.setDateTime(LocalDateTime.now());
			order.setUserId(user.getId());
			order.setAmount(total);
			
			entityManager.persist(order);
			//transaction.commit();
			
			for ( Map.Entry<Product, Integer> entry : carrello.getCarrello().entrySet()) {
			    Integer quantity = entry.getValue();
			    
			    Product differentProduct = entityManager.find(Product.class, entry.getKey().getId());
			    
			    differentProduct.setQuantity(entry.getKey().getQuantity() - quantity);
			    
			    OrderItem orderItem = new OrderItem();
			    orderItem.setQuantity(quantity);
			    orderItem.setProductId(differentProduct.getId());
			    orderItem.setSellPrice(differentProduct.getPrice());
			    orderItem.setOrderId(order.getId()); 	///errore
			    
			    entityManager.persist(orderItem);
			    entityManager.persist(differentProduct);
			}
			transaction.commit();

			entityManager.close();
		} else {
			response.getWriter().append(openHtml)
			.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
			.append(openBody)
			.append("<h1 style=\"color:red;\">Utente non loggato</h1>")
			.append("<a href=\"loginUser.jsp\" style=\"text-decoration: none; color:blue;\">-> Login</a>");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
