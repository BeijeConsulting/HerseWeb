
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
	<!--  Object email = request.getAttribute("email");
		Object password = request.getAttribute("password");-->
	<% 
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		System.out.println("email : " + email + " password " + password);
		%>
	<h1>Benvenuto utente</h1>
	<ul class="list-group list-group-flush">
		<li class="list-group-item"><a href="OrdiniUser" style="text-decoration: none; color:black;">Visualizza tutti i tuoi ordini</a></li>
		<li class="list-group-item"><a href="nuovoOrdine.html" style="text-decoration: none; color:black;">Effettua un nuovo ordine</a></li>
		<li class="list-group-item"><a href="infoOrdine.html" style="text-decoration: none; color:black;">Ottieni informazioni riguardo un ordine</a></li>
		<li class="list-group-item"><a href="listaProdotti" style="text-decoration: none; color:black;">Visualizza tutti i prodotti dello Shop</a></li>
	</ul>
	
	<!--  <a href="login.html" style="text-decoration: none; color:blue;">Login</a>-->
</body>
</html>