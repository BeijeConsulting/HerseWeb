
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Shop</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">

	<jsp:useBean id="authUser" class="it.beije.herse.web.entity.User" scope="session"></jsp:useBean>
	<% 
	/*String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		System.out.println("email : " + email + " password " + password);*/
		if (authUser.getEmail() == null) {
			%>
			<h2 style="color:red">Utente non autenticato!</h2>
			<br>
			<a href="loginUser.jsp" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Login</button></a>
			<%
		} else {
			%>
			<h1>Benvenuto <jsp:getProperty name="authUser" property="name"/>!</h1>
			
		<ul class="list-group list-group-flush">
			<!--  <li class="list-group-item"><a href="ordini_user" style="text-decoration: none; color:black;">Visualizza tutti i tuoi ordini</a></li>-->
			<li class="list-group-item"><a href="nuovo_ordine" style="text-decoration: none; color:black;">Effettua un nuovo ordine</a></li>
			<!--  <li class="list-group-item"><a href="infoOrdine.jsp" style="text-decoration: none; color:black;">Ottieni informazioni riguardo un ordine</a></li>-->
			<li class="list-group-item"><a href="catalogo" style="text-decoration: none; color:black;">Catalogo prodotti</a></li>
		</ul>
		
		<br>
		<a href="logout" style="text-decoration: none; color:blue;"><button type="button" class="btn btn-primary">Log out</button></a>
		<%} %>

</body>
</html>