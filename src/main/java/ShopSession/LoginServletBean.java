package ShopSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Ecommerce.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServletBean
 */
@WebServlet("/LoginServletBean")
public class LoginServletBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletBean() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("LoginServletBean doPost");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email + " " + password);
		
		HttpSession session = request.getSession();
		
		int user = Shop.checkCredential(email, password);
		
		String userId = String.valueOf(user);
		System.out.println("userId - LS: " + userId);
		
		if(user == 0) {
			session.setAttribute("error", "Credenziali Sbagliate");
			response.sendRedirect("LoginShop.jsp");
		} else {
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			Integer cont = 1;
			session.setAttribute("map", map);
			session.setAttribute("cont", cont);
			session.setAttribute("userID", userId);
			response.sendRedirect("catalogo.jsp");
		}
		
		
	}

}
