<%@page import="it.beije.herse.shop.beans.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">
	<h1>HERSE SHOP</h1>
	
	<jsp:useBean id="loggedUser" class="it.beije.herse.shop.beans.User" scope="session"></jsp:useBean>
	<%
	//User u = (User) session.getAttribute("loggedUser");
	%>
	
	MY INFO: <br>
	<table>
		<tr><td>ID: </td><td><%= loggedUser.getId() %></td></tr>
		<tr><td>NAME: </td><td><%= loggedUser.getName() %></td></tr>
		<tr><td>SURNAME: </td><td><%= loggedUser.getSurname() %></td></tr>
		<tr><td>EMAIL: </td><td><%= loggedUser.getEmail() %></td></tr>
		<tr><td>PASSWORD: </td><td><%= loggedUser.getPassword() %></td></tr>
	</table>
	<br>
	<form action="ReturnToMenuServlet" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>