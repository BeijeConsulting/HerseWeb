package Ecommerce;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetProductServlet
 */
@WebServlet("/ServletProduct")
public class GetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductServlet() {
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
		String product = request.getParameter("productId");
		String quantityS = request.getParameter("quantity");
		
		int productId = Integer.parseInt(product);
		int quantity = Integer.parseInt(quantityS);
		
		boolean quantityBool = false;
		
		
		if(request.getParameter("yes") == null){
			System.out.println("unchecked");//checkbox not checked
			quantityBool = Shop.checkQuantity(productId, quantity);
			if(!quantityBool) {
				response.getWriter().append("Too high quantity");
				response.sendRedirect("index.html");
			} else {
//				Order order = Shop.changeOrder();
				
//				response.getWriter().append("Your Order: ").append(order.toString());
			}
			
		}else{
			System.out.println("checked");//checkbox checked
			quantityBool= Shop.checkQuantity(productId, quantity);
			if(!quantityBool) {
				response.getWriter().append("Too high quantity");
				response.sendRedirect("index.html");
			} else {
				StringBuilder sb = new StringBuilder("<html><body><form action='/Login' method=\"get\">\r\n\"\r\n"
						+ "       <p><input type=\"submit\" value=\"Go to buy more \"></p>\r\n"
						+ "					    </form> </body></html>");
				response.getWriter().append(sb);
			}
			
		}
		
		
		
		
		
	}

}
