package it.beije.herse.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Carello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get-Carrello");
		HttpSession session = request.getSession();
		String ug=request.getParameter("userId");
		int u=Integer.parseInt(ug);
	
		String qp=request.getParameter("quantita");
		int q=Integer.parseInt(qp);

		String id=request.getParameter("id");
		int i=Integer.parseInt(id);
		
		EntityManager manager = ShopEntityManager.newEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Cart c=new Cart();
		
		List<Cart> carts = new ArrayList<>();
		//sets
			c.setIdProduct(i);	
			c.setIdUser(u);
			c.setqProduct(q);
			carts.add(c);
	
		for (Cart cc : carts) manager.persist(cc);

		transaction.commit();
		manager.close();
		session.setAttribute("cart", carts);
		response.sendRedirect("cart-shop.jsp");	
	
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post-Carrello");
		response.sendRedirect("cart-shop.jsp");
	}

}
