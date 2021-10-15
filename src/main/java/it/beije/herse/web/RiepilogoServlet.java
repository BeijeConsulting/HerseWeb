package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.Carrello;
import it.beije.herse.shop.ManagerCRUD;
import it.beije.herse.shop.OrderItem;
import it.beije.herse.shop.Product;

import static it.beije.herse.shop.MyShop.*;;

/**
 * Servlet implementation class RiepilogoServlet
 */
@WebServlet("/RiepilogoServlet")
public class RiepilogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiepilogoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null)
			response.sendRedirect("index.html");
		if(session.getAttribute("htmlEl") != null)
			session.removeAttribute("htmlEl");
			
		ManagerCRUD m = (ManagerCRUD)session.getAttribute("managerCRUD");
		List<Product> list = getProducts(m);
		StringBuilder error = new StringBuilder();
		Carrello c = new Carrello();
		
		for(Product p : list){
		
			String qta = request.getParameter("prodQta" + p.getId());
			String id = request.getParameter(p.getId().toString());
			
			if(id != null) {
				
				
				if(validateQta(qta, p)){
					
					OrderItem item = setOrderItem(Integer.valueOf(id), Integer.valueOf(qta), p.getPrice());
					c.addItem(item);
					
				}else{
					
					if(p.getQuantity() > 1)
						error.append("Sono disponibili " + p.getQuantity() + " di " + p.getName());
					else
						error.append("E' disponibile " + p.getQuantity() + " di " + p.getName());
					
				}
				
			}
			
		}
		
		if(!error.toString().isEmpty()){
			session.setAttribute("errorQta", error.toString());
			response.sendRedirect("ViewProduct");
		} else {
			StringBuilder htmlEl = new StringBuilder();
			for(OrderItem item : c.getItems()){
				Product p = getProduct(item.getProductId(), (ManagerCRUD)request.getSession().getAttribute("managerCRUD"));
				htmlEl.append("<html><body><input style=\"width:100px\" type=\"text\" value=\"" + p.getName() + "\" readonly>\n"
								+ "<input style=\"width:100px\" type=\"text\" value=\"" + p.getDescription() + "\" readonly>\n"
								+ "<input style=\"width:70px\" type=\"text\" value=\"" + item.getSellPrice() + "\" readonly>\n"
								+ "<input style=\"width:40px\" type=\"text\" value=\"" + item.getQuantity() + "\" readonly><br>\n");
			}
			session.setAttribute("htmlEl", htmlEl.toString());
			session.setAttribute("carrello", c);
			response.sendRedirect("Riepilogo.jsp");
		}
		
	}

}
