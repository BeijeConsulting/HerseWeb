package it.beije.herse.shop.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewOrderServlet
 */
@WebServlet("/NewOrderServlet")
public class NewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String path = "/temp/html/HerseShop/newOrder.html";
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		StringBuilder s = new StringBuilder();
//		while(br.ready()) s.append(br.readLine());
//		response.getWriter().append(s.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String submit = (String) request.getParameter("submitOrder");
		if(submit!=null && submit.equals("SUBMIT")) {
			
			Double p1 = 0.0;
			Integer q1 = 0;
			String checkboxProd1 = (String) request.getParameter("prod1");
			if(checkboxProd1!=null && checkboxProd1.equals("on")) {
				p1 = Double.valueOf(request.getParameter("price1")); 
				q1 = Integer.valueOf(request.getParameter("quantity1")); 	
			}
			
			Double p2 = 0.0;
			Integer q2 = 0;
			String checkboxProd2 = (String) request.getParameter("prod2");
			if(checkboxProd2!=null && checkboxProd2.equals("on")) {
				p2 = Double.valueOf(request.getParameter("price2")); 
				q2 = Integer.valueOf(request.getParameter("quantity2")); 	
			}
			
			Double total = p1*q1 + p2*q2;
//			response.getWriter().append("TOTAL: "+total);
			
			// Checkout
			if((checkboxProd1!=null && q1>0) || (checkboxProd2!=null && q2>0)) {
				StringBuilder s = new StringBuilder();
				s.append("<html><body bgcolor=\"black\" text=\"white\">");
				s.append("<h1>HERSE SHOP</h1><br>");
				s.append("ORDER ADDED: <br>");
				s.append("<table>");
				if(checkboxProd1!=null && q1>0) 
					s.append("<tr><td>\"Dune\"</td><td>QUANTITY: "+q1+"</td><td>PRICE: "+p1*q1+"$</td></tr>");
				if(checkboxProd2!=null && q2>0)
					s.append("<tr><td>\"Lenovo\"</td><td>QUANTITY: "+q2+"</td><td>PRICE: "+p2*q2+"$</td></tr>");
				s.append("</table>");
				s.append("<br>TOTAL: "+total+"$");
				s.append("</body></html>");
				response.getWriter().append(s.toString());
			}
		}
//		else response.getWriter().println("WAITING...");
		
//		doGet(request, response);
	}

}
