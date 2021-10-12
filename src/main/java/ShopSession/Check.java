package ShopSession;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
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
		// TODO Auto-generated method stub
		System.out.println("Check doPost");
		
		HttpSession session = request.getSession();

		int userId = (int) session.getAttribute("userId");
		
		System.out.println("Check userId" + userId);
		
		String product = request.getParameter("productId");
		String quantityS = request.getParameter("quantity");
		String buyMore = request.getParameter("yes");
		
		int productId = Integer.parseInt(product);
		int quantity = Integer.parseInt(quantityS);
		
		System.out.println("non vuoi comprare ancora");
		int orderId = (int) session.getAttribute("orderId");
		
		if(buyMore == null) {
			
			System.out.println("Check order: " +orderId);
			if(Shop.checkQuantity(productId, quantity, userId)) {
				response.sendRedirect("fine.jsp");
				Shop.insertOrderItem(orderId, productId, quantity);
					
			} else {
				session.setAttribute("wrongQuantity", "Wrong Quantity, too High");
				response.sendRedirect("sessionForm");
			}
			
		} else {
			System.out.println("vuoi comprare ancora");
			if(Shop.checkQuantity(productId, quantity, userId)) {
				Shop.insertOrderItem(orderId, productId, quantity);
				response.sendRedirect("sessionForm.jsp");
			}else {
				session.setAttribute("wrongQuantity", "Wrong Quantity");
				response.sendRedirect("sessionForm");
			}
		}
	}

}
