<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="it.beije.herse.db.User"%>
<%@page import="it.beije.herse.web.Carrello"%>
<%@page import="it.beije.herse.db.Product"%>
<%@page import="it.beije.herse.db.Singleton"%>


<%@page import="java.util.*"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FraGese</title>
</head>
<body>

	<h1>Catalogo FraGese</h1>
	<% 
User u = (User) session.getAttribute("user");

%>
	<marquee> Per eventuali problemi con le JPA o con le JSP
		rivolgersi al servizio clienti 80099990099 </marquee>
	<h3>
		Welcome <span style='color: green'><%= u.getName() %></span>
	</h3>

	<ul>
		<% 

EntityManager entityManager = Singleton.createEntity("herse-shop");
Query q = entityManager.createQuery("SELECT product FROM Product as product");
List<Product> prodotti = q.getResultList();

Carrello carrello = null;

if(session.getAttribute("carrello")!=null){
	 carrello = (Carrello) session.getAttribute("carrello");
}

for(int i=0;i<prodotti.size();i++){
	
	int quantita=0;
	Product prodotto = prodotti.get(i);
	
if(carrello!=null && carrello.getMappa().containsKey(prodotto.getId())) {
  quantita = carrello.getMappa().get(prodotto.getId());
}

int tot = prodotto.getQuantity()- quantita;
	

   %><li>
			<form action='carrellos' method='post'>
				<label for='<%= prodotto.getName() %>'><%= prodotto.getName() %></label>
				<input type='submit' value='aggiungi' /> <input type='hidden' name='idP' value='<%= prodotto.getId() %>'> 
				    <a href='description.jsp?id=<%= prodotto.getId() %>'><input type='button' value='Dettaglio prodotto'></a>
					<input type='number' name='quantita' step='1' min='1' value='1'max='<%= tot %>'>
			</form>
		</li>
		<%
}
%>
	</ul>

	<a href='carrello.jsp'><input type='submit' value='Vai al carrello' /></a>

</body>
</html>