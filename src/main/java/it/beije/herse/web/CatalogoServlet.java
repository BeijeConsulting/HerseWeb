package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder=new StringBuilder();
		builder.append("<html><head><title>Insert title here</title></head><body>Benvenuto, di seguito il nostro catalogo:");
		builder.append("<br><br><table><thead><tr><td>Id</td><td>Nome</td><td>Descrizione</td><td>Prezzo</td>");
		builder.append("</tr></thead><tr><td>1</td><td>Cuffie</td><td>Bluetooth</td><td>19.99</td></tr><tr>");
		builder.append("<td>2</td><td>SSD</td><td>250 GB</td><td>39.99</td></tr></table><form name='Carrello' action='ContinuoServlet'>");
		builder.append("<p>Id oggetto:</p><p><input type='text' name=Id></p>Quantità:<p><input type='text' name=Quantitò>");
		builder.append("</p><p><button type='submit'>Invio</button></p></form></body></html>");
		response.getWriter().append(builder);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
