<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Ordine</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<jsp:useBean id="authUser" class="it.beije.herse.web.entity.User" scope="session"></jsp:useBean>
	<%
	String error = (String) session.getAttribute("error");
	if (error != null) {
		%>
		<span style="color:red"><%=error%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	
	<% if (authUser.getEmail() == null) {
			%>
			<h2 style="color:red">Utente non autenticato!</h2>
			<br>
			<a href="loginUser.jsp" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Login</button></a>
			<%
		} else {
			%>
			<h1>Ordine confermato</h1>

		<br>
		<a href="menuUser.jsp" style="text-decoration: none; color:blue;">-> Torna al menu</a>
		<%} %>
</body>
</html>