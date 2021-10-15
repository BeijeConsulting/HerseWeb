<%@page import="it.beije.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Benvenuto su HerseShop: 
<%
	Users user = (Users)session.getAttribute("authUser"); 
if(user!= null){
	String name = user.getName();
	String surname = user.getSurname();
%>
<%=name%>	<%=surname %>

<%} %>
</h1><br><br>
<h4>Ecco cosa puoi fare da noi: </h4><br>
<a href="Prodotti.jsp"> Vedi i nostri prodotti </a><br>
<a href="CarrelloTris.jsp"> Vedi il tuo carrello </a><br>
<%if(user != null){ %>
<a href="LogOut.jsp"> Log Out </a><br>
<%}else{ %>
<a href="login.jsp"> LogIn </a><br>
<a href="Registrazione.jsp"> Registrati </a><br>
<%} %>

</body>
</html>