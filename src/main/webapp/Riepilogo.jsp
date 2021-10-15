<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo</title>
</head>
<body>

	<jsp:useBean id="user" class="it.beije.herse.shop.User" scope="session"></jsp:useBean>

	<%
	if(user == null) {
		response.sendRedirect("index.html");
	}
	%>

	<h1>Riepilogo</h1>

	<%= request.getSession().getAttribute("htmlEl").toString() %>

	<br>
	<a href="OrderConfirm"><button>Conferma Ordine</button></a>

</body>
</html>