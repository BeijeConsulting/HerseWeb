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
 * Servlet implementation class UserMenuServlet
 */
@WebServlet("/UserMenuServlet")
public class UserMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String path = "/temp/html/HerseShop/userMenu.html";
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
		String submit = (String) request.getParameter("submitUserAction");
		if(submit!=null && submit.equals("SUBMIT")) {
			
			String action = (String) request.getParameter("userAction");
			switch(action) {
			case "showProfile":
//				response.getWriter().println("NAME: Mario, SURNAME: Rossi");
				break;
			case "showOrderHistory":
//				response.getWriter().println("NO ORDERS YET");
				break;
			case "newOrder":
				response.sendRedirect("servlet/newOrder.html");
				break;
			case "updateProfile":
//				response.getWriter().println("STARTING PROFILE UPDATE PROCESS...");
				break;
			}
			
		}
//		else response.getWriter().println("WAITING...");
		
//		doGet(request, response);
	}

}
