package it.beije.herse.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static it.beije.herse.shop.MyShop.*;
import it.beije.herse.shop.*;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = getUser(request.getParameter("email"), request.getParameter("password"));
		HttpSession session = request.getSession();
		
		if(user == null) {
			session.setAttribute("error", "Credenziali errate");
			response.sendRedirect("index.html");
		}
		
		session.setAttribute("user", user);
		response.sendRedirect("viewmenu.jsp");
		doGet(request, response);
		
	}

}
