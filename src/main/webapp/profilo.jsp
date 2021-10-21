<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.herse.web.MetodiJPA"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo</title>
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
<br>
<br>
<br>
<table width="50%" border="1"   align="left">
<tbody>
			<tr><td bgcolor="#a9a9a9" align="center">Id assegnato:</td><td><%=user.getId() %></td></tr>
			<tr><td bgcolor="#a9a9a9" align="center">Nome:</td><td><%=user.getName() %></td></tr>
			<tr><td bgcolor="#a9a9a9" align="center">Cognome:</td><td><%=user.getSurname() %></td></tr>
			<tr><td bgcolor="#a9a9a9" align="center">Email: </td><td><%=user.getEmail()%></td></tr>
			<tr><td bgcolor="#a9a9a9" align="center">Password:</td><td><%=user.getPassword() %></td></tr>
<table>

<%} %>
</body>
</html>