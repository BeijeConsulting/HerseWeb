package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.ManagerCRUD;
import it.beije.herse.shop.Product;
import static it.beije.herse.shop.MyShop.*;

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
		HttpSession session = request.getSession();
		StringBuilder htmlEl = new StringBuilder();
		String errorQta = (String) session.getAttribute("erroQta");
		String confirm = (String) session.getAttribute("orderConfirm");
		
		if (session.getAttribute("user") == null) {
			response.sendRedirect("index.html");
		} else if (confirm != null && !confirm.isEmpty()) {
			confirm = "<p style=\"color:green\">" + confirm + "</p>";
			session.setAttribute("orderConfirm",confirm);
		} else if (errorQta != null && !errorQta.isEmpty()) {
			errorQta = "<p style=\"color:red\">" + errorQta + "</p>";
			session.setAttribute("errorQta", errorQta);
		}
			
		ManagerCRUD m = (ManagerCRUD)session.getAttribute("managerCRUD");
		List<Product> products = getProducts(m);

		for (Product p : products)
			htmlEl.append(newHTMLProd(p));
		
		session.setAttribute("htmlEl", htmlEl.toString());
		
		response.sendRedirect("viewproduct.jsp");
		
	}
	
	public String newHTMLProd(Product p) {
		return "<input type=\"checkbox\" name=\"" + p.getId() + "\" value=\"" + p.getId() + "\">"
				+ "<input type=\"text\" name=\"prodName" + p.getId() + "\" value=\"" + p.getName() + "\" readonly>\n"
				+ "<input type=\"text\" name=\"prodDescription" + p.getId() + "\" value=\"" + p.getDescription()
				+ "\" readonly>\n" + "<input style=\"width:30px\" type=\"number\" name=\"prodQta" + p.getId()
				+ "\" placeholder=\"0\"><br>\n";
	}

}
