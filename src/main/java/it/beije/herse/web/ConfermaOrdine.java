package it.beije.herse.web;

import java.io.IOException;
import java.time.LocalDateTime;

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

import it.beije.herse.web.entity.Order;
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
		
		Product product = (Product)session.getAttribute("productCart");
		System.out.println("ordine");
		System.out.println(product);
		
		response.getWriter().append(openHtml)
		.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
		.append(openBody)
		.append("<h1>Ordine confermato</h1>");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Order order = new Order();
		order.setDateTime(LocalDateTime.now());
		order.setUserId(null);
		order.setAmount(null);
		
//		entityManager.persist(order);
//		transaction.commit();
		entityManager.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
