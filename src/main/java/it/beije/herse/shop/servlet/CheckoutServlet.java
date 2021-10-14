package it.beije.herse.shop.servlet;

import java.io.IOException;
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
import it.beije.herse.shop.manager.OrderManager;
import it.beije.herse.shop.manager.ProductManager;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
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
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		List<OrderItem> items = cart.getItems();
//		List<OrderItem> items = (List<OrderItem>) session.getAttribute("items");
//		Integer checked[] = (Integer[]) session.getAttribute("checked");
		for(OrderItem i : items) {
			System.out.println("DELETE ID: "+i.getProductId()+"?");
			String delete = (String) request.getParameter("deleteItem"+i.getProductId());
			if(delete!=null) {
				System.out.println("ID "+i.getProductId()+" DELETED");
				
				cart.removeQuantity(i.getProductId());
				cart.removeItem(i);
				session.setAttribute("cart", cart);
//				checked[i.getProductId()] = null;
//				items.remove(i);
//				session.setAttribute("items", items);
//				session.setAttribute("checked", checked);
				response.sendRedirect("checkout.jsp");
				return;
			}
		}
		
		String submit = (String) request.getParameter("submitPayment");
//		Double total = (Double) session.getAttribute("total");
		if(submit!=null && submit.equalsIgnoreCase("CONFIRM & PAY")) {
			if(cart.getTotal()<=0) {
				response.sendRedirect("neworder.jsp");
				return;
			}
			cart.confirmOrder();
//			Order order = (Order) session.getAttribute("order");
//			OrderManager.insertOrder(order, items);
			response.sendRedirect("confirmorder.jsp");
			return;
		}
		
		String back = (String) request.getParameter("back");
		if(back!=null && back.equalsIgnoreCase("BACK")) {
			response.sendRedirect("neworder.jsp");
			return;
		}
		
	}
}
