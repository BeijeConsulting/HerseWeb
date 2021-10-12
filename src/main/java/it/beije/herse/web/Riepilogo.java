package it.beije.herse.web;

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

import it.beije.herse.db.Order;
import it.beije.herse.db.OrderItem;
import it.beije.herse.db.Product;
import it.beije.herse.db.Singleton;
import it.beije.herse.db.User;

/**
 * Servlet implementation class Prima
 */
@WebServlet("/ordini")
public class Riepilogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Riepilogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private static String ordini(User u) {
 
		EntityManager entityManager = Singleton.createEntity("herse-shop");
		Query q = entityManager.createQuery("SELECT order FROM Order as order WHERE user_id='"+ u.getId()+ "'");
		List<Order> orderList = q.getResultList();
		
        String s = "Ci sono i seguenti ordini a nome di " + u.getEmail() + " <ul>";
        		
		if(orderList.size()>0) {
			System.out.println("Ci sono i seguenti ordini a nome di "+ u.getEmail());
			
			for(Order order : orderList) {
				
				System.out.println(order);
				s = s.concat("<li>" + order.toString());
				
				q = entityManager.createQuery("SELECT OrderItem FROM OrderItem as OrderItem WHERE order_id='"+ order.getId()+ "'");
				List<OrderItem> OrderItemsList = q.getResultList();
				
				for(OrderItem orderItem : OrderItemsList) {
					
					q = entityManager.createQuery("SELECT product FROM Product as product WHERE id='"+ orderItem.getProductId()+ "'");
					List<Product> OrderProductList = q.getResultList();
					
					for(Product product : OrderProductList) {
						System.out.println(orderItem.getQuantity() + " " + product.getName() + " a " + orderItem.getSellPrice() + "€");
						s= s.concat(" " + orderItem.getQuantity() + " " + product.getName() + " a " + orderItem.getSellPrice() + "€ </li>");
					}
				}
			}
			
			s= s.concat("<ul>");
			
		} else {
			System.out.println("Non ci sono ordini nel tuo storico");
			s = "";
			s= s.concat("Non ci sono ordini nel tuo storico");
		}
		
		return s;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		
		if(u!=null) ordini(u); else response.sendRedirect("index.jsp");
		
		if(u!=null) { 
			
			session.setAttribute("lista", ordini(u));
			response.sendRedirect("riepilogo.jsp");
			
		}
		
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
