package it.beije.herse.shop.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.beans.Cart;
import it.beije.herse.shop.beans.Order;
import it.beije.herse.shop.beans.OrderItem;
import it.beije.herse.shop.beans.Product;
import it.beije.herse.shop.beans.User;
import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;
//import it.beije.herse.shop.manager.ShopVecchia;

/**
 * Servlet implementation class NewOrderServlet
 */
@WebServlet("/NewOrderServlet")
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Cart cart = new Cart();
		List<Product> products = new ProductManager().selectProducts();
//		List<OrderItem> items = new ArrayList<OrderItem>();
//		Order order = new Order();
		
		// SAVE ORDER
//		Integer checked[] = new Integer[products.size()+1];
		for(Product p : products) {
			String check = (String) request.getParameter("check"+p.getId());
			if(check!=null && check.equalsIgnoreCase("on")) {
				Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
				if(quantity>0) cart.addQuantity(p.getId(), quantity);
//				checked[p.getId()] = Integer.valueOf(request.getParameter("quantity"+p.getId()));
			}
		}
//		session.setAttribute("checked", checked);
		
		String submit = (String) request.getParameter("submitOrder");
		if(submit!=null && submit.equals("ADD TO CART")) {
			for(Product p : products) {
				String check = (String) request.getParameter("check"+p.getId());
				if(check!=null && check.equalsIgnoreCase("on")) {
					Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
					if(quantity<=0) continue;
					OrderItem item = new OrderItem();
					item.setProductId(p.getId());
					item.setQuantity(quantity);
					cart.addItem(item);
//					items.add(item);
				}
			}
			
			Integer userId = ((User)session.getAttribute("loggedUser")).getId();
			cart.setOrderUserId(userId);
			cart.setOrderDateTime(LocalDateTime.now());
//			order.setUserId(((User)session.getAttribute("loggedUser")).getId());
//			order.setDateTime(LocalDateTime.now());
			
//			session.setAttribute("order", order);
//			session.setAttribute("items", items);
			
//			OrderManager.insertOrder(order, items);
			
			response.sendRedirect("checkout.jsp");
		}
		
		for(Product p : products) {
			String details = (String) request.getParameter("prodDetails"+p.getId());
			if(details!=null && details.equalsIgnoreCase(p.getName())) {
				session.setAttribute("prodId", p.getId());
				response.sendRedirect("productdetails.jsp");
			}	
		}
		
		String back = (String) request.getParameter("backToMenu");
		if(back!=null && back.equalsIgnoreCase("BACK TO MENU")) {
			response.sendRedirect("usermenu.jsp");
			return;
		}
		
		session.setAttribute("cart", cart);
	}

}
