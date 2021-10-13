package it.beije.herse.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.herse.shop.beans.User;
import it.beije.herse.shop.manager.UserManager;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");
		
		String submit = (String) request.getParameter("submitUpdate");
		if(submit!=null && submit.equalsIgnoreCase("UPDATE")) {
			
			// NAME UPDATE
			String newName = (String) request.getParameter("newName");
			if(newName.length()!=0) {
				u.setName(newName);
				UserManager.updateUsers("NAME", newName, u.getId());
			}
			
			// SURNAME UPDATE
			String newSurname = (String) request.getParameter("newSurname");
			if(newSurname.length()!=0) {
				u.setSurname(newSurname);
				UserManager.updateUsers("SURNAME", newSurname, u.getId());
			}
			
			// EMAIL UPDATE
			String newEmail = (String) request.getParameter("newEmail");
			if(newEmail.length()!=0) {
				u.setEmail(newEmail);
				UserManager.updateUsers("EMAIL", newEmail, u.getId());
			}
			
			// PASSWORD UPDATE
			String newPassword = (String) request.getParameter("newPassword");
			if(newPassword.length()!=0) {
				u.setPassword(newPassword);
				UserManager.updateUsers("PASSWORD", newPassword, u.getId());
			}
			
			// loggedUser UPADTE
			session.setAttribute("loggedUser", u);
			
			response.sendRedirect("userMenu.jsp");
		}
	}

}
