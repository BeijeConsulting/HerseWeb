package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.web.entity.Order;
import it.beije.herse.web.entity.Product;

@WebServlet("/lista")
public class ListaProdotti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String openHtml = "<html>";
	String openBody = "<body>";
	String closeHtml = "</html>";
	String closeBody = "</body>";
	String br = "<br>";
   
    public ListaProdotti() {
        super();
    }
    
    protected List<Product> selectProducts() {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		List<Product> products = new ArrayList<Product>();
		
		String productSelect = "SELECT p FROM Product AS p";
		Query query = entityManager.createQuery(productSelect);
		products = query.getResultList();
		
		entityManager.close();
		return products;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = selectProducts();
		
		response.getWriter().append(openHtml)
		.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
		.append(openBody)
		.append("<h1>Lista prodotti</h1>");
		
		for(Product p : products) {
			response.getWriter().append("Type: ").append(p.getName()).append(", Description: ").append(p.getDescription()).append(", Price: ").append(p.getPrice().toString()).append(br);
		}
		response.getWriter().append(br).append("<a href=\"menuUser.jsp\" >-> Torna al menu</a>").append(closeBody).append(closeHtml);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
