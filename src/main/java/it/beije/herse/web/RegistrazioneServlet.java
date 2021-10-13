package it.beije.herse.web;
import Shop.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RegistrazioneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String conf_email = request.getParameter("conf_email");
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String password=request.getParameter("password");
		String conf_password = request.getParameter("conf_password");
		HttpSession session=request.getSession();
		if(Funzioni.ControlloRegistrazione(email,name,surname,password,conf_email,conf_password)==-1) {
			session.setAttribute("error", "Formato e-mail non corretto!");
			response.sendRedirect("Registrazione.jsp");
		}else if(Funzioni.ControlloRegistrazione(email,name,surname,password,conf_email,conf_password)==-2){
			session.setAttribute("error", "Utente già registrato!");
			response.sendRedirect("Registrazione.jsp");
		}else if(Funzioni.ControlloRegistrazione(email,name,surname,password,conf_email,conf_password)==-3){
			session.setAttribute("error", "Le e-mail non corrispondono!");
			response.sendRedirect("Registrazione.jsp");
		}else if(Funzioni.ControlloRegistrazione(email,name,surname,password,conf_email,conf_password)==-4){
			session.setAttribute("error", "Le password non corrispondono!");
			response.sendRedirect("Registrazione.jsp");
		}else {
			User user=Funzioni.Registrazione(email,name,surname,password);
			List<Product> carrello=new ArrayList<Product>();
			List<Integer> qta=new ArrayList<Integer>();
			session.setAttribute("authUser", user);
			session.setAttribute("carrello", carrello);
			session.setAttribute("qta", qta);
			response.sendRedirect("Catalogo.jsp");
		}
	}

}
