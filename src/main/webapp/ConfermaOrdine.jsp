<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conferma Ordine</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<jsp:useBean id="user" class="it.beije.herse.web.User" scope="session"></jsp:useBean>
<jsp:useBean id="carts" class="it.beije.herse.web.Cart" scope="session"></jsp:useBean>
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
<h1 align="center">Ordine confermato, <%=user.getName().toUpperCase() %>! Grazie per l'acquisto.</h1>
<form action="Item" method="post">
<input type="hidden"  name="userId" value="<%=user.getId()%>"></input>
 <input type="submit" value="Torna al catalogo">
</form>



<%
}%>
</body>
</html>