package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.beije.herse.bean.*;
import it.beije.herse.bean.User;


@WebServlet("/prodotti")
public class Prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	
	public static final String  A_CAPO = "<br>";
	public static final String  INIZIO = "<html><body>";
	public static final String  FINE = "</html></body>";
	
    public Prodotti() {
        super();

    }
    
   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		StringBuilder html = new StringBuilder();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String nome = user.getName();
		String cognome = user.getSurname();
		html.append(INIZIO);
		html.append("<h1>").append("Benvenuto ").append(nome + " ").append(cognome).append("</h1>");
		html.append("<h3>").append("Ecco i prodotti disponibili per l'acquisto").append("</h3>");
		
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		String jpqlSelect = "SELECT p FROM Product as p";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Product> prodotti = query.getResultList();
		
		html.append("<form action=\"prodotti\" method=\"post\">");
		html.append("<label for=\"id\">Id Prodotti:</label>").append(A_CAPO);
		html.append(" <input type=\"text\" name=\"id\">").append(A_CAPO).append(A_CAPO);
		html.append(" <input type=\"submit\" value=\"Inserisci\">");
		html.append("</form>");
		
		html.append("<a href = \"carrello\">").append("<input type=\"submit\" value = \"carrello\"").append("</a>");
		html.append(FINE);
		response.getWriter().append(html.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		doGet(request, response);
	}

}
