package it.beij.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CatchPage
 */
@WebServlet("/CatchPage")
public class CatchPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatchPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		StringBuilder sb = new StringBuilder("Benvenuto!");
		String user = (String) request.getSession().getAttribute("user");
		String psw = (String) request.getSession().getAttribute("password");
		String note = (String) request.getSession().getAttribute("note");
		sb.append("\nUser: " + user);
		sb.append("\nPassword: " + psw);
		sb.append("\nNote: " + note);
		sb.append("\nSession: " + request.getSession().getId());

		response.getWriter().append(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		HttpSession session = request.getSession();
		String note = request.getParameter("note");
		System.out.println("same session: " + session.getId());
		System.out.println("note: " + note);

		session.setAttribute("note", note);

		doGet(request, response);
	}

}
