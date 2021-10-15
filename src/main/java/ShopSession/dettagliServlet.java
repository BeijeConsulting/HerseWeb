package ShopSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ecommerce.Product;

/**
 * Servlet implementation class dettagliServlet
 */
@WebServlet("/dettagliServlet")
public class dettagliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dettagliServlet() {
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
		
		HttpSession session = request.getSession();
		
		List<Product> products = new Shop().findProducts();

		List<Integer> ids = new ArrayList<Integer>();

		for(Product p: products){
			String prodId = request.getParameter(String.valueOf(p.getId())); 
			if(prodId != null ){
				ids.add(p.getId());
			}
		}

		List<Product> prodToPrint = new ArrayList<Product>();

		for(int x: ids){
			List<Product> prod = new Shop().findProductsById(x);
			for(Product p: prod){
				prodToPrint.add(p);
			}

		}
		
		for(Product p: prodToPrint) {
			System.out.println(p);
		}
		session.setAttribute("prodToPrint", prodToPrint);
		response.sendRedirect("dettagli.jsp");
	}

}
