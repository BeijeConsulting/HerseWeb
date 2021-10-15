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
<h1> Benvenuto su HerseShop</h1>
<p>Ecco alcune cose che puoi fare: </p>

<a href="Acquista.jsp"> Acquista prodotti </a><br>
<a href="Carrello.jsp"> Vedi il tuo carrello </a><br>

<%
Users user = (Users)session.getAttribute("authUser");
if(user!=null){
%>
	<a href="LogOut.jsp"> Log Out </a><br><br>
	<p>Attualmente loggato come: <%=user.getName() %> <%=user.getSurname() %></p>
<%
}else{
%>	
	<a href="LogIn.jsp"> Log In </a><br>
	<a href="Registrazione.jsp"> Registrati </a><br>
	
<%	
}
%>

</body>
</html>