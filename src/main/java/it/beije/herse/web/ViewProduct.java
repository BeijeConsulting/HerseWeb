package it.beije.herse.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.shop.Product;
import it.beije.herse.shop.Order;
import static it.beije.herse.shop.MyShop.*;

import static it.beije.herse.shop.MyShop.getProducts;

/**
 * Servlet implementation class ViewProduct
 */
@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> list = getProducts();
		
		Order order = new Order();
		order.setAmount(0.0);
		order.setUserId(Integer.parseInt(request.getParameter("id")));
		order.setDateTime(LocalDateTime.now());
		
		manager.insert(order);
		
		response.getWriter().append(createPage(list,order.getUserId()));
		
	}
	
	private String createPage(List<Product> list, int userId) {
		
		StringBuilder page = new StringBuilder();

		page.append("<html><body><form method=\"POST\" action=\"finalizeShop.jsp?id=" + userId + "\">");
		for(Product p : list)
			page.append("<input type=\"text\" name=\"prodName\" value=\"" + p.getName() + "\"readonly>")
				.append("<input type=\"text\" value=\"" + p.getPrice() + "\"readonly>")
				.append("<input type=\"text\" placeholder=\"quantity\"><br>");
		page.append("<input type=\"submit\" value=\"Buy\">")
			.append("</form></body></html>");
		
		return page.toString();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
