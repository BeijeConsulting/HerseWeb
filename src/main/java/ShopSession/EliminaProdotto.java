package ShopSession;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EliminaProdotto
 */
@WebServlet("/EliminaProdotto")
public class EliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaProdotto() {
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
		
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		
		System.out.println("PRE: " + map);
		
		String product = request.getParameter("productIdE");
		String quantityS = request.getParameter("quantityE");
		
		int productId = Integer.parseInt(product);
		int quantity = Integer.parseInt(quantityS);
		
		session.removeAttribute("map");
		
		System.out.println("quantity par: " + quantity);
		
		int quanTot = 0;
		
		int cont = 1;
		
		for (Integer key : map.keySet()) {
			Object obj = map.get(key);
			Carrello carrello = (Carrello) obj;
			if(carrello.getProductId() == productId) {
				quanTot += carrello.getQuantity();
			}
		}
		
		System.out.println("quantity tot: " + quanTot);
		
		if(quanTot < quantity) {
			session.setAttribute("wrongQuantityE", "Quantità troppo alta, prova ad abbasarla");
			response.sendRedirect("riepilogo.jsp");
		} else {
			for(int i = 1; quantity != 0; i++) {
				System.out.println("i: " +i);
				if(map.containsKey(i)) {
					Object obj = map.get(i);
					Carrello carrello = (Carrello) obj;
					if(carrello.getProductId() == productId) {
						int quantityCar = carrello.getQuantity();
						System.out.println("quantityCar: " + quantityCar);
						if(quantityCar == quantity) {
							System.out.println("car==par");
							Shop.addQuantityProduct( productId, quantity);
							quantity -= quantityCar;
							map.remove(i);
						}
						if(quantityCar > quantity) {
							System.out.println("car>par");
							carrello.setQuantity(quantityCar - quantity);
							Shop.addQuantityProduct( productId, quantity);
							quantity = 0;
						} 
						if(quantityCar < quantity) {
							System.out.println("car<par");
							quantity -= quantityCar; 
							Shop.addQuantityProduct( productId, quantityCar);
							System.out.println("quanity-quantityCar:" + quantity);
							map.remove(i);
							
						}
					}
				}
				
			}
			session.setAttribute("map", map);
			response.sendRedirect("riepilogo.jsp");
		}
		
		System.out.println("POST: " + map);
		
	}

}
