package it.beije.herse.web.my;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ordini_user")
public class OrdiniUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public OrdiniUser() {
		super();
		System.out.println("ordini servlet");
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.getWriter().append("<html>")
		.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
		.append("<body style=\"margin:1%\">")
		.append("<h1>Ordini dell'utente</h1>")
		.append("<a href=\"menuUser.jsp\" style=\"text-decoration: none; color:black;\">-> Torna al menu</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
