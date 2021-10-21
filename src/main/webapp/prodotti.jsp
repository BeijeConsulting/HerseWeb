<%@page import="it.beije.herse.web.MetodiJPA"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@page import="it.beije.herse.web.Product"%>
<%@page import="it.beije.herse.web.MetodiJPA"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.herse.web.ShopEntityManager"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css"/>
<title>AREA PRIVATA</title>
</head>
<body>

<jsp:useBean id="user" class="it.beije.herse.web.User" scope="session"></jsp:useBean>
<% 
if (user.getEmail() == null) {
	%>
	<span style="color:red">UTENTE NON AUTENTICATO!!!</span>
	<a href="login.jsp">Torna a login</a>
	<%
	
} else {
	
%>

	<nav id="primary_nav_wrap">
    <br>
    <ul>
        <li class="current-menu-item"><a href="Prodotti">Lista Prodotti</a></li>
        <li class="current-menu-item"><a href="Profilo">Profilo</a></li>
        <li class="current-menu-item"><a href="Storico">Storico Ordini</a></li>
        <li class="current-menu-item"><a href="cart-shop.jsp">Carrello</a></li>
        <li class="current-menu-item"><a href="Logout">Logout</a></li>

    </ul>
</nav>
<br>
<br>
<h1> Catalogo Prodotti</h1>
<table width="" border="1"  style="border-collapse:collapse;" align="center">
<tbody>
			<tr>
				<td bgcolor="#a9a9a9" align="center">Nome</td>
				<td bgcolor="#a9a9a9" align="center">Descrizione</td>
				<td bgcolor="#a9a9a9" align="center">Prezzo</td>
			
				
				
			</tr>
<%	List<Product> products =MetodiJPA.printProducts();

for(Product p : products) {
	%>

	<tr>
			
			<td><%=p.getName() %></td>
			<td><%=p.getDescription() %></td>
			<td><%=p.getPrice()%></td>
			<td><form action="Carrello" method="get">
			<input type="number" name="quantita" step="1" min="1" value="1" max="<%=p.getQuantity()%>">
			<input type="hidden"  name="id" value="<%=p.getId()%>"></input>
			<input type="hidden"  name="userId" value="<%=user.getId()%>"></input>
			<input type="submit"  value="Acquista">
		
           
           </form>
				
			</td>
	</tr> 
	
		</tbody>
<% }%>



	


</table>

<%  
}

%>
	
		

</body>
</html>