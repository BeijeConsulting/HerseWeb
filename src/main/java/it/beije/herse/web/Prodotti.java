package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prodotti
 */
@WebServlet("/prodotti")
public class Prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	static List<BeanProdotti> prodotti = new ArrayList<>();
	
	public static final String  A_CAPO = "<br>";
	public static final String  INIZIO = "<html><body>";
	public static final String  FINE = "</html></body>";
	
    public Prodotti() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    static void creaListaProdotti() {
    	BeanProdotti p= new BeanProdotti();
    	BeanProdotti q= new BeanProdotti();
    	BeanProdotti r= new BeanProdotti();
    	p.setId(1);
    	q.setId(2);
    	r.setId(3);
    	p.setName("occhiali");
    	q.setName("penna");
    	r.setName("libro");
    	p.setDescription("sono occhiali");
    	q.setDescription("è una penna");
    	r.setDescription("è un libro");
    	p.setPrice(13.99);
    	q.setPrice(9.99);
    	r.setPrice(29.99);
    	
    	prodotti.add(p);
    	prodotti.add(q);
    	prodotti.add(r);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		creaListaProdotti();
		
		StringBuilder html = new StringBuilder();
		
		html.append(INIZIO);
		html.append("<h1>").append("Ecco i prodotti disponibili per l'acquisto").append("</h1>");
		html.append("<ul>");
		for(BeanProdotti p : prodotti) {
			html.append("<li>");
			html.append(p.getId()).append(" ");
			html.append(p.getName()).append(" ");
			html.append(p.getDescription()).append(" ");
			html.append(p.getPrice()).append("</li>");
		}
		html.append("</ul>");
		
		html.append("<form action=\"prodotti\" method=\"post\">");
		html.append("<label for=\"id\">Id Prodotti:</label>").append(A_CAPO);
		html.append(" <input type=\"text\" name=\"id\">").append(A_CAPO).append(A_CAPO);
		html.append(" <input type=\"submit\" value=\"Inserisci\">");
		html.append("</form>");
		
		html.append("<a href = \"carrello\">").append("<input type=\"submit\" value = \"carrello\"").append("</a>");
		html.append(FINE);
		response.getWriter().append(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Integer idProdotto = Integer.parseInt(id);
		for(int i = 0; i<prodotti.size(); i++) {
			BeanProdotti p = prodotti.get(i);
			if(p.getId() == idProdotto) {
				Carrello.carrello.add(p);
				break;
			}
		}
		
		doGet(request, response);
	}

}
