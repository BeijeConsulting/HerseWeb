package it.beije.herse.web;

import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Rimuovi
 */
@WebServlet("/Rimuovi")
public class Rimuovi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rimuovi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet get rimuovi");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet post rimuovi");
		HttpSession session=request.getSession();
		EntityManager manager=ShopEntityManager.newEntityManager();
		manager.getTransaction().begin();
		
		String idcart=request.getParameter("cartId");
		Integer id=Integer.parseInt(idcart);
		manager.remove(manager.find(Cart.class, id));

		manager.getTransaction().commit();
		manager.close();
		response.sendRedirect("cart-shop.jsp");
		
		
	}

}
