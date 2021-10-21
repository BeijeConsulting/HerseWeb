package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Prodotti
 */
@WebServlet("/Prodotti")
public class Prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory entityManagerFactory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prodotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Product-Servlet doGet");
		HttpSession session=request.getSession();
		response.sendRedirect("prodotti.jsp");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
		
	}


