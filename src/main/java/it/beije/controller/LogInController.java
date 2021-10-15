package it.beije.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.bean.CarrelloNew;
import it.beije.bean.Users;
import it.beije.model.UserModel;
import javassist.expr.NewArray;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/LogInController")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInController() {
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
//		doGet(request, response);
		
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");		
		UserModel userModel = new UserModel();
		
		List<Users> users = userModel.getUsers();
		boolean registrato = false;
		for (Users user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				registrato = true;
			}
			if(user.getEmail().equalsIgnoreCase(email)&&user.getPassword().equals(password)) {
				CarrelloNew carrello = new CarrelloNew(request);
				
				session.setAttribute("authUser", user);
				session.setAttribute("carrello", carrello);
				
				response.sendRedirect("Home.jsp");
			}
		}
		if(session.getAttribute("authUser")==null) {
			if(!registrato) {
				// error utente registrtrato ma sbagliata la psw
				String errorUserNotReg = "error user";
				session.setAttribute("errorUserNotReg", errorUserNotReg);
				response.sendRedirect("LogIn.jsp");
			}else {
				// error utente non registrato
				String errorPasswordLogIn = "error user";
				session.setAttribute("errorPasswordLogIn", errorPasswordLogIn);
				response.sendRedirect("LogIn.jsp");
			}
		}
		
	}

}
