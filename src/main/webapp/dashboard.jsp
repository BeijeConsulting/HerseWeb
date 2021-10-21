<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="it.beije.herse.web.User"%>

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
/*User authUser = (User) session.getAttribute("authUser");
if (authUser == null) {
	authUser = new User();
	session.setAttribute("authUser", authUser);	
}*/

if (user.getEmail() == null) {
	%>
	<span style="color:red">UTENTE NON AUTENTICATO!!!</span>
	<%
	
} else {
	String name=user.getName().toUpperCase();
	String surname=user.getSurname().toUpperCase();
	%>
	<nav id="primary_nav_wrap">
    <br>
    <ul>
        <li class="current-menu-item" ><a href="Prodotti">Lista Prodotti</a></li>
        <li class="current-menu-item"><a href="Profilo">Profilo</a></li>
        <li class="current-menu-item"><a href="Storico">Storico Ordini</a></li>
        <li class="current-menu-item"><a href="cart-shop.jsp">Carello</a></li>
        <li class="current-menu-item"><a href="Logout">Logout</a></li>

    </ul>
</nav>
<br>
<br>
<div>
	<strong>BENVENUTO <%=name %> <%=surname %>!</strong>
	</div>
	<%
}
%>
</body>
</html>