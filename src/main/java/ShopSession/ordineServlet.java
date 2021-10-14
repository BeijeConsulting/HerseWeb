package ShopSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ecommerce.Order;
import Ecommerce.Product;

/**
 * Servlet implementation class ordineServlet
 */
@WebServlet("/ordineServlet")
public class ordineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ordineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String user = (String) session.getAttribute("userID");
		int userId = Integer.parseInt(user);
		int orderId = 0;
		if(user != null){
			orderId = Shop.insertOrder(userId);
		}
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		for (Integer key : map.keySet()) {
			Object obj = map.get(key);
			Carrello carrello = (Carrello) obj;
			List<Product> products = Shop.getProductsById(carrello.getProductId());
			for (Product p : products) {
				Shop.insertOrderItem(orderId, p.getId(), carrello.getQuantity());
			}
		}
		Order order = Shop.changeOrder(orderId);
		
		System.out.println("Order: " + order);
		
		session.setAttribute("order", order);
		response.sendRedirect("ordine.jsp");
	}

}
