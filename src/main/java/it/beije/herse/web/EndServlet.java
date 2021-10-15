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
import Shop.*;

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
		HttpSession session=request.getSession();
		EntityManager manager=(EntityManager)session.getAttribute("manager");
		terminaOrdine((ArrayList<Carrello>)session.getAttribute("carrello"),(Order)session.getAttribute("order"));
		Order o=(Order)session.getAttribute("order");
		Order order=manager.find(Order.class, o.getId());
		manager.getTransaction().begin();
		order.setAmount(o.getAmount());
		manager.getTransaction().commit();
		response.sendRedirect("End.jsp");
		
	}
	protected  void terminaOrdine(ArrayList<Carrello> carrello,Order order) {
		for(Carrello c:carrello)
			commit(c,order.getId());
	}
	
		protected  void commit(Carrello c,int id_order) {
			EntityManager manager=ShopEntityManager.newEntityManager();
			Product p=manager.find(Product.class, c.getId_product());
			OrderItem orderItem=new OrderItem();
			manager.getTransaction().begin();
			orderItem.setOrderId(id_order);
			orderItem.setProductId(c.getId_product());
			orderItem.setSellPrice(p.getPrice());
			orderItem.setQuantity(c.getQty());
			manager.persist(orderItem);
			manager.getTransaction().commit();
		}

}
