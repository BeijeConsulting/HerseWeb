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

import it.beije.herse.shop.beans.Order;
import it.beije.herse.shop.beans.OrderItem;
import it.beije.herse.shop.beans.Product;
import it.beije.herse.shop.beans.User;
import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;
import it.beije.herse.shop.manager.ShopVecchia;

/**
 * Servlet implementation class CreateOrderServlet
 */
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderServlet() {
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
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		Order order = new Order();
		
		List<Product> products = ProductManager.selectProducts();
		String submit = (String) request.getParameter("submitOrder");
		if(submit!=null && submit.equals("ADD TO CART")) {
			for(Product p : products) {
				String check = (String) request.getParameter("check"+p.getId());
				if(check!=null && check.equalsIgnoreCase("on")) {
					OrderItem item = new OrderItem();
					item.setProductId(p.getId());
					Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
					item.setQuantity(quantity);
					items.add(item);
				}
			}
			
			order.setUserId(((User)session.getAttribute("loggedUser")).getId());
			order.setDateTime(LocalDateTime.now());
			
			session.setAttribute("order", order);
			session.setAttribute("items", items);
			
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
	}

}