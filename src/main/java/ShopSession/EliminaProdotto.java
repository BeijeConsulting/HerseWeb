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
		
		String product = request.getParameter("productIdE");
		String quantityS = request.getParameter("quantityE");
		
		int productId = Integer.parseInt(product);
		int quantity = Integer.parseInt(quantityS);
		
//		HashMap<Integer, Object> tmp = new HashMap<Integer, Object>();
		
		int quanTot = 0;
		
		int cont = 1;
		
		for(int i = 1; i <= map.size(); i++) {
			Object obj = map.get(i);
			Carrello carrello = (Carrello) obj;
			if(carrello.getProductId() == productId) {
				quanTot += carrello.getQuantity();
			}
		}
		
		if(quanTot < quantity) {
			session.setAttribute("wrongQuantity", "Quantità troppo alta, prova ad abbasarla");
//			response.sendRedirect("riepilogo.jsp");
		} else {
			for(int i = 1; i <= map.size(); i++) {
				Object obj = map.get(i);
				Carrello carrello = (Carrello) obj;
				if(carrello.getProductId() == productId) {
					int quantityCar = carrello.getQuantity();
					if(quantityCar == quantity) {
						map.remove(i);
						i = map.size()+1;
					}else if(quantityCar > quantity) {
						carrello.setQuantity(quantityCar - quantity);
						i = map.size()+1;
					} else if(quantityCar < quantity) {
						quantity -= quantityCar; 
						map.remove(i);
					}
				}
			}
//			response.sendRedirect("riepilogo.jsp");
		}
		
		System.out.println(map);
		
	}

}
