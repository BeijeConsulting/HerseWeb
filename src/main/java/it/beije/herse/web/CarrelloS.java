package it.beije.herse.web;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.db.Product;
import it.beije.herse.db.Singleton;

/**
 * Servlet implementation class CarrelloS
 */
// es un idea de Samuele Fraiolis con una pregunta correcta de Maximiliano
@WebServlet("/carrellos")
public class CarrelloS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrelloS() {
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
		

		Carrello carrello = null;

		if(session.getAttribute("carrello")==null) {

			carrello = new Carrello();
			
		} else {

			carrello= (Carrello) session.getAttribute("carrello");

		}

		carrello.addProduct( Integer.valueOf((String)request.getParameter("idP")), Integer.valueOf((String)request.getParameter("quantita")));
		session.setAttribute("carrello", carrello);

		response.sendRedirect("catalogo.jsp");


	}

}
