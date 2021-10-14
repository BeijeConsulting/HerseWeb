package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shop.Funzioni;
import Shop.Order;
import Shop.Product;
import Shop.ShopEntityManager;
import Shop.User;
import Shop.Carrello;

/**
 * Servlet implementation class EndServlet
 */
@WebServlet("/EndServlet")
public class EndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager manager=ShopEntityManager.newEntityManager();
		HttpSession session=request.getSession();
		Funzioni.terminaOrdine((ArrayList<Carrello>)session.getAttribute("carrello"),(Order)session.getAttribute("order"));
		Order o=(Order)session.getAttribute("order");
		Order order=manager.find(Order.class, o.getId());
		manager.getTransaction().begin();
		order.setAmount(o.getAmount());
		manager.getTransaction().commit();
		response.sendRedirect("End.jsp");
		
	}

}
