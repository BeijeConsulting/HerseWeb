package ShopSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ecommerce.Product;

/**
 * Servlet implementation class riepilogoServlet
 */
@WebServlet("/riepilogoServlet")
public class riepilogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public riepilogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		
		List<Product> prodRiepilogo = new ArrayList<Product>();
		
		for (Integer key : map.keySet()) {
			Object obj = map.get(key);
			Carrello carrello = (Carrello) obj;
			List<Product> products = new Shop().findProductsById(carrello.getProductId());
			for (Product p : products) {
				prodRiepilogo.add(p);
			}
		}
		
		session.setAttribute("map", map);
		session.setAttribute("prodRiepilogo", prodRiepilogo);
		response.sendRedirect("riepilogo.jsp");
	}

}
