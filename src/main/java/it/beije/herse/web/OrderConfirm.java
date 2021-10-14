package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.*;
import it.beije.herse.shop.User;
import static it.beije.herse.shop.MyShop.*;

/**
 * Servlet implementation class OrderConfirm
 */
@WebServlet("/OrderConfirm")
public class OrderConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Carrello c = (Carrello)session.getAttribute("carrello");
		Order o = initOrder(user.getId());
		o.setAmount(calcAmountOrder(c.getItems()));
		
		manager.begin();
		manager.insert(o);
		manager.commit();
		
		c.setListItem(setListOrderItemId(c.getItems(), o.getId()));
		
		registerOrderItem(c.getItems());
		
		updateQtaProduct(c.getItems());
		
		session.removeAttribute("carrello");
		
		session.setAttribute("orderConfirm", "Ordine Confermato!");
		
		response.sendRedirect("viewproduct.jsp");
		
	}
	
	private void registerOrderItem(List<OrderItem> items) {
		
		manager.begin();
		
		for(OrderItem item : items)
			manager.insert(item);
		
		manager.commit();
		
	}
	
	private void updateQtaProduct(List<OrderItem> items) {
		
		manager.begin();
		
		for(OrderItem item : items) {
			Product p = getProduct(item.getProductId());
			p.setQuantity(p.getQuantity()-item.getQuantity());
			manager.insert(p);
		}
		
		manager.commit();
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
