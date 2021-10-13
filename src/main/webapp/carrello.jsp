<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="it.beije.herse.bean.User"%>
<%@page import="it.beije.herse.bean.Product"%>
<%@page import="it.beije.herse.bean.ShopEntityManager"%>
<%@page import="it.beije.herse.web.Carrello"%>


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
<title>Carrello</title>
</head>
<body>
	<h2>
		Il carrello di
		<%=((User) session.getAttribute("user")).getName()%></h2>

	<ul>
		<%
		EntityManager entityManager = ShopEntityManager.newEntityManager();
		Carrello carrello = (Carrello) session.getAttribute("carrello");

		Set<Integer> set = carrello.getMappa().keySet();
		Iterator<Integer> indice = set.iterator();
		double total = 0.0;

		while (indice.hasNext()) {
			
			Integer i = indice.next();
			Integer quantita = carrello.getMappa().get(i);
			
            Product p = entityManager.find(Product.class,i);
            total += (p.getPrice() * quantita);
            %> <li> <%= p.getName() %>, <%= p.getPrice() %> € (quantità: <%= quantita %>, totale parziale: <%= p.getPrice()*quantita %>) </li> <% 
            
		}
		entityManager.close();
		%>
	</ul>
	<br><p><strong>Il totale è <%= total %> €</strong></p>
	<form action='pay' method='post'>
	  <br>
	   <a href='catalogo.jsp'><input type='button' value='Torna al catalogo'/></a>
	   <input type='submit' value="Completa l'acquisto"/>
	</form>

</body>
</html>