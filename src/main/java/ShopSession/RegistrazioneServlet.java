package ShopSession;

import java.io.IOException;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int user = Shop.checkCredential(email, password);
		
		if(!email.contains("@")) {
			session.setAttribute("emailSbagliata", "Email Sbagliata");
			response.sendRedirect("registrazioneShop.jsp");
		} else if(user != 0) {
			session.setAttribute("utenteRegistrato", "Utente già registrato");
			response.sendRedirect("registrazioneShop.jsp");
		} else if(user == 0) {
			Shop.insertUser(nome, cognome, email, password);
			session.setAttribute("faiLogin", "Effettua il Login, dopo la registrazione");
			response.sendRedirect("LoginShop.jsp");
		}
		
	}

}
