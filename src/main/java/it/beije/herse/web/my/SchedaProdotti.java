package it.beije.herse.web.my;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.herse.web.entity.Product;

@WebServlet("/schedaProdotti")
public class SchedaProdotti extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String openHtml = "<html>";
	String openBody = "<body style=\"margin:1%\">";
	String closeHtml = "</html>";
	String closeBody = "</body>";
	String br = "<br>";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
List<Product> products = ListaProdotti.selectProducts();
		
		response.getWriter().append(openHtml)
		.append("<head><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\"></head>")
		.append(openBody)
		.append("<h1>Scheda prodotti</h1>");
		
		for(Product p : products) {
			response.getWriter()
			.append("Prodotto: ").append(p.getName())
			.append("&emsp;  Descrizione: ").append(p.getDescription())
			.append("&emsp;  Prezzo: ").append(p.getPrice().toString())
			.append(br);
		}
		response.getWriter().append(br).append("<a href=\"nuovoOrdine.jsp\" >-> Torna all'ordine</a>").append(closeBody).append(closeHtml);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
