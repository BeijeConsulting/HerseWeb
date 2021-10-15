package it.beije.herse.shop.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ShopLoginServletSession")
public class TestLoginSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestLoginSession() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		String path = "/temp/html/HerseShop/shopIndex.html";
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		StringBuilder s = new StringBuilder();
//		while(br.ready()) s.append(br.readLine());
//		response.getWriter().append(s.toString());
				
//		response.getWriter().println("Hello World");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String login = (String) request.getParameter("submitLogin");
		if(login!=null && login.equals("LOGIN")) {
			
			String email = (String) request.getParameter("email");
			session.setAttribute("email", email);
			String password = (String) request.getParameter("password");
			session.setAttribute("password", email);
			if(email.equals("mrossi@gmail.com") && password.equals("mr")) {
				response.sendRedirect("userMenu.html");
			}
			else response.sendRedirect("failedLogin.html");
			//else response.sendRedirect("failedLogin.html");
			
		}
//		else response.getWriter().println("WAITING...");
		
//		doGet(request, response);
	}

}
