package it.beije.herse.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.web.entity.Order;
import it.beije.herse.web.entity.Shop;

@WebServlet("/OrdiniUser")
public class OrdiniUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String openHtml = "<html><body>";
	String closeHtml = "</body></html>";
	String br = "<br>";
    
    public OrdiniUser() {
        super();
    }
    
    protected List<Order> selectOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Object email = request.getAttribute("email");
    	System.out.println(email);
    	System.out.println("ok");
    	int idUser = new LoginUser().correctLogin(request, response);
    	List<Order> listOrdersUser = Shop.ordersOfUser(idUser);
    	return listOrdersUser;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<Order> listOrdersUser = selectOrders(request, response);
		
		response.getWriter().append(openHtml).append("<h1>Lista ordini</h1>");
		
		for(Order o : listOrdersUser) {
			response.getWriter().append(o.toString());
		}
		response.getWriter().append(br).append("<a href=\"menuUser.jsp\" style=\"text-decoration: none; color:black;\">-> Torna al menu</a>").append(closeHtml);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
