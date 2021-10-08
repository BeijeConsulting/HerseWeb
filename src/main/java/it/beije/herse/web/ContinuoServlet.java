package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContinuoServlet
 */
@WebServlet("/ContinuoServlet")
public class ContinuoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContinuoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		builder.append("<html><head><title>Insert title here</title></head><body>Vuoi continuare gli acquisti?");
		builder.append("<form action='CatalogoServlet'><button type='submit'>Sì</button></form><form action='EndServlet'>");
		builder.append("<button type='submit'>No</button></form></body></html>");
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
