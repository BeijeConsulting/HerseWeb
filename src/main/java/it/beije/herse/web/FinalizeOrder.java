package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.shop.Order;
import it.beije.herse.shop.OrderItem;
import it.beije.herse.shop.Product;
import it.beije.herse.shop.User;

import static it.beije.herse.shop.MyShop.*;

/**
 * Servlet implementation class FinalizeOrder
 */
@WebServlet("/FinalizeOrder")
public class FinalizeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizeOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		manager.begin();
		List<Product> list = getProducts();
		Order order = initOrder(((User)(request.getSession().getAttribute("user"))).getId());
		manager.insert(order);
		List<OrderItem> items = new ArrayList<>();
		
		for(Product p : list) {
			
			String id = request.getParameter(p.getId().toString());
			String qta = request.getParameter("prodQta" + id);
			
			if(!(id.isEmpty())) {
				if(Integer.valueOf(qta) <= p.getQuantity())
					items.add(setOrderItem(p.getId().toString(), qta, p.getPrice(), order.getId()));
				else
					response.sendRedirect("viewproduct.jsp?error=quantity");
			}
			
		}
		
		manager.commit();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
