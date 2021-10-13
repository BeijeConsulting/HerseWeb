package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shop.*;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		int quta = Integer.parseInt(request.getParameter("qta"));
		HttpSession session=request.getSession();
		if(Funzioni.controlloIdProdotto(idProdotto)&&Funzioni.controlloQuantità(idProdotto, quta)) {
			session.setAttribute("carrello", Funzioni.carrello(idProdotto, (ArrayList<Product>)session.getAttribute("carrello")));
			session.setAttribute("qta", Funzioni.qta(quta, (ArrayList<Integer>)session.getAttribute("qta")));
			session.setAttribute("prodotto_aggiunto", "Prodotto aggiunto al carrello!");
			response.sendRedirect("Catalogo.jsp");
		}else {
			session.setAttribute("prodotto_aggiunto", "ERRORE!");
			response.sendRedirect("Catalogo.jsp");
		}
	}

}
