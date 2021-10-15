package it.beije.herse.web.my;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.web.entity.Order;
import it.beije.herse.web.entity.Product;
import it.beije.herse.web.entity.RequestDb;

@WebServlet("/catalogo")
public class ListaProdotti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public ListaProdotti() {
        super();
        System.out.println("lista prodotti servlet");
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("catalogo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
