<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="it.beije.herse.web.entity.Product"%>
<%@page import="it.beije.herse.web.entity.RequestDb"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Scheda prodotti</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body>
	<h1>Scheda prodotti</h1>
	<%	List<Product> products = new RequestDb().selectProducts();
		for(Product p : products) {
			%><span>Prodotto: </span><%=p.getName()%>
			<span>&emsp; Id: </span><%=p.getId()%>
			<span>&emsp; Quantit�: </span><%=p.getQuantity()%>
			<span>&emsp; Descrizione: </span><%=p.getDescription()%>
			<span>&emsp; Prezzo: </span><%=p.getPrice().toString()%>
			<br>
		<% }%>
	<br>
	<a href="nuovoOrdine.jsp" ><button type="button" class="btn btn-secondary">Torna al menu</button></a>
	
</body>
</html>