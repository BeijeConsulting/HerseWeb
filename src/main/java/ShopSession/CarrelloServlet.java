package ShopSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Ecommerce.Product;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Carrello doPost");
		
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("userID");
		
		System.out.println("userString CarreloServlet" + user);

		String product = request.getParameter("productId");
		String quantityS = request.getParameter("quantity");
		String buyMore = request.getParameter("yes");
		
		int productId = Integer.parseInt(product);
		int quantity = Integer.parseInt(quantityS);
		int userId = Integer.parseInt(user);
		
		
		
		HashMap<Integer, Object> map;
		
		 if (session.isNew()) {
			 map = new HashMap<Integer, Object>();
		    } else {
		    	map = (HashMap<Integer, Object>) session.getAttribute("map");
		    }
		
		Integer cont = (Integer) session.getAttribute("cont");
		
		System.out.println("carrello");
		
		if(buyMore == null) {
			Shop shop = new Shop();
			if(shop.checkQuantity(productId, quantity, userId)) {
				Carrello carrello = new Carrello();
				carrello.setProductId(productId);
				carrello.setQuantity(quantity);
				map.put(cont, carrello);
				cont++;
				session.setAttribute("cont", cont);
				session.setAttribute("map", map);
				response.sendRedirect("riepilogo.jsp");

			} else {
				session.setAttribute("wrongQuantity", "Quantità troppo alta, prova ad abbasarla");
				response.sendRedirect("catalogo.jsp");
			}

		} else {
			Shop shop = new Shop();
			if(shop.checkQuantity(productId, quantity, userId)) {
				Carrello carrello = new Carrello();
				carrello.setProductId(productId);
				carrello.setQuantity(quantity);
				map.put(cont, carrello);
				cont++;
				session.setAttribute("cont", cont);
				session.setAttribute("map", map);
				response.sendRedirect("catalogo.jsp");
			}else {
				session.setAttribute("wrongQuantity", "Quantità troppo alta, prova ad abbasarla");
				response.sendRedirect("catalogo.jsp");
			}
		}
		
	}

}
