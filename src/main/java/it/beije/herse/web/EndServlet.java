package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shop.Funzioni;
import Shop.Product;
import Shop.User;

/**
 * Servlet implementation class EndServlet
 */
@WebServlet("/EndServlet")
public class EndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Double tot=0.0;
		ArrayList<Integer> quant=(ArrayList<Integer>)session.getAttribute("qta");
		ArrayList<Product> carrello=(ArrayList<Product>)session.getAttribute("carrello");
		for(int i=0;i<carrello.size();i++) {
			tot+=carrello.get(i).getPrice()*quant.get(i);
		}
		session.setAttribute("tot", tot);
		Funzioni.inserisciOrdineItem(carrello, tot, (User)session.getAttribute("authUser"),(ArrayList<Integer>)session.getAttribute("qta"));
		response.sendRedirect("End.jsp");
		
	}

}
