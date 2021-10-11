package it.beije.herse.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletForm() {
        super();
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //Comunico al client il MIME type text/html
		 response.setContentType("text/html; charset=UTF-8");

		 //Richiedo un PrintWriter all'oggetto response per inviare i dati
		 PrintWriter out = response.getWriter();

		 // Creo la pagina DINAMICA HTML con i dati passati dalla Form
		 try {
		 out.println("<!DOCTYPE html>");
		 out.println("<html><head>");
		 out.println("<meta http-equiv='Content-Type' content='text/htmlcharset=UTF-8'>");
		 out.println("<title>Servlet Form</title></head>");
		 out.println("<body><h2>Hai digitato:</h2>");
		 // Recupero lo "email" dal campo email (text)
		 String email = request.getParameter("email");
		 out.println("<p>Email: " + email + "</p>");
		// Recupero la "password" dal campo password
		 String password = request.getParameter("password");
		 out.println("<p>Password: " + password + "</p>");

	
		 /* controllo  campi NULL
		 if (password == null) {
		 out.println("<p>Password: MANCANTE</p>");
		 } else if (genere.equals("m")) {
		 out.println("<p>: </p>");
		 } else {
		 out.println("<p></p>");
		 }
		 */
		 // link "BACK" verso la pagina index.html
		 out.println("<a href='index.html'>BACK</a>");
		 out.println("</body></html>");
		 } finally {
		 out.close(); // close dell' output writer
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}