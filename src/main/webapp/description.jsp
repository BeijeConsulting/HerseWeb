<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="it.beije.herse.db.Product"%>
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
<%
Integer id = Integer.valueOf((String) request.getParameter("id"));
EntityManager entityManager = Singleton.createEntity("herse-shop");
Product prodotto = entityManager.find(Product.class, id);
%>
<title>Dettaglio <%= prodotto.getName() %></title>
</head>
<body>

<h3>Dettaglio prodotto:</h3>
<h4><%= prodotto.getName() %>, prezzo: <%= prodotto.getPrice() %> €</h4>
<p><%= prodotto.getDescription() %></p>

<%
Carrello carrello = null;

if(session.getAttribute("carrello")!=null){
	 carrello = (Carrello) session.getAttribute("carrello");
}
	
	int quantita=0;
	

if(carrello!=null && carrello.getMappa().containsKey(prodotto.getId())) {
  quantita = carrello.getMappa().get(prodotto.getId());
}

int tot = prodotto.getQuantity()- quantita;
%>

<form action='carrellos' method='post'>
				<input type='submit' value='aggiungi' /> <input type='hidden' name='idP' value='<%= prodotto.getId() %>'> 
				<input type='number' name='quantita' step='1' min='1' value='1'max='<%= tot %>'>
			</form>

</body>
</html>