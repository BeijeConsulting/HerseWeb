package it.beije.herse.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ordine
 */
@WebServlet("/Ordine")
public class Ordine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ordine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get-Ordine");
		
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post-Ordine");
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		
		HttpSession session = request.getSession();
		
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);

		
		List<Cart> listCart =manager.createQuery("select u from Cart as u where u.idUser="+userId+"").getResultList();
		List<Order> listOrder=new ArrayList<>();
		
		Order order=new Order();	OrderItem item=new OrderItem();
		
		for(Cart c: listCart) {
	
				order.setAmount((double)c.getqProduct());
				order.setDateTime(LocalDateTime.now());
				order.setUserId(userId);
				listOrder.add(order);

		}
			
		for (Order o : listOrder) manager.merge(o);
		
	
		transaction.commit();
		manager.close();		
		response.sendRedirect("ConfermaOrdine.jsp");
	}

}
