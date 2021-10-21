<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="it.beije.herse.web.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storico</title>
<link rel="stylesheet" href="style.css"/>
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
<%
List<Order> list=(List<Order>)session.getAttribute("orders");
for(Order o: list){   %>
<p><%=o.getDateTime() %></p>


<%} %>
<%} %>
</body>
</html>