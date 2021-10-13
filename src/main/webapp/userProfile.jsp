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
	
	<%
	User u = (User) session.getAttribute("loggedUser");
	%>
	
	MY INFO: <br>
	<table>
		<tr><td>ID: </td><td><%= u.getId() %></td></tr>
		<tr><td>NAME: </td><td><%= u.getName() %></td></tr>
		<tr><td>SURNAME: </td><td><%= u.getSurname() %></td></tr>
		<tr><td>EMAIL: </td><td><%= u.getEmail() %></td></tr>
		<tr><td>PASSWORD: </td><td><%= u.getPassword() %></td></tr>
	</table>
	<br>
	<form action="userMenu.jsp" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>