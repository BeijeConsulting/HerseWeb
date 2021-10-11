package Ecommerce;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> products = Shop.getProducts();
		StringBuilder sb = new StringBuilder("<html><body>").append("<h1 align='center'>Our Products</h1>");

		for(Product p: products) {
			sb.append(p);
			sb.append("<br>");
		}
		sb.append("<hr>");
		sb.append("<center><h3>What do you want to buy</h3></center>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "    <form action='ServletProduct' method='post'>\r\n"
				+ "\r\n"
				+ "        <p>Product Id : <input type=\"text\" name=\"productId\"  placeholder=\"Product Id\" required></p>\r\n"
				+ "\r\n"
				+ "        <p>Quantity : <input type=\"text\" name=\"quantity\"  placeholder=\"Quantity\" required></p>\r\n"
				+ "\r\n"
				+" <input type=\"checkbox\" id=\"yes\" name=\"yes\">\r\n"
				+ "  <label for=\"yes\">Yes</label>\r\n"
				+ "\r\n"
				+ "        <p><input type=\"submit\" value=\"Confirm\"></p>\r\n"
				+ "\r\n"
				+ "    </form>\r\n"
				+ "\r\n"
				+ "    <form action='index.html' method=\"post\">\r\n"
				+ "        <p><input type=\"submit\" value=\"Back\"></p>\r\n"
				+ "    </form>")
		.append("</body>\r\n"
				+ "</html>");
		response.getWriter().append(sb);
		response.getWriter().append("Served at: ").append(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("userId");

		//		response.getWriter().append(user);

		int userId = Integer.parseInt(user);

		Boolean exists = Shop.existUser(userId);

		if(exists == false) {
			response.sendRedirect("index.html");
		} else {

			List<Product> products = Shop.getProducts();
			StringBuilder sb = new StringBuilder("<html><body>").append("<h1 align='center'>Our Products</h1>");

			for(Product p: products) {
				sb.append(p);
				sb.append("<br>");
			}
			sb.append("<hr>");
			sb.append("<center><h3>What do you want to buy</h3></center>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "    <form action='ServletProduct' method='post'>\r\n"
					+ "\r\n"
					+ "        <p>Product Id : <input type=\"text\" name=\"productId\"  placeholder=\"Product Id\" required></p>\r\n"
					+ "\r\n"
					+ "        <p>Quantity : <input type=\"text\" name=\"quantity\"  placeholder=\"Quantity\" required></p>\r\n"
					+ "\r\n"
					+" <input type=\"checkbox\" id=\"yes\" name=\"yes\">\r\n"
					+ "  <label for=\"yes\">Yes</label>\r\n"
					+ "\r\n"
					+ "        <p><input type=\"submit\" value=\"Confirm\"></p>\r\n"
					+ "\r\n"
					+ "    </form>\r\n"
					+ "\r\n"
					+ "    <form action='index.html' method=\"post\">\r\n"
					+ "        <p><input type=\"submit\" value=\"Back\"></p>\r\n"
					+ "    </form>")
			.append("</body>\r\n"
					+ "</html>");
			response.getWriter().append(sb);


		}

	}

}
