<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="it.beije.herse.web.entity.Product"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Carrello</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<!--<jsp:useBean id="productCart" class="it.beije.herse.web.entity.Product" scope="session"></jsp:useBean>-->
	<jsp:useBean id="carrello" class="it.beije.herse.web.entity.Carrello" scope="session"></jsp:useBean>
	<!--<jsp:useBean id="orderItem" class="it.beije.herse.web.entity.OrderItem" scope="session"></jsp:useBean>-->
	
	<h1>Carrello</h1>
	<p>
		<% 	
		Double total = new Double(0);
		for ( Map.Entry<Product, Integer> entry : carrello.getCarrello().entrySet()) {
		    Product product = entry.getKey();
		    Integer quantity = entry.getValue();
		    
		    out.print("Id Prodotto: "+product.getId() + " ");
		    out.print("Tipo: " + product.getName() + " ");
			out.print("Quantità: " + quantity  + " "); 
			out.print("Prezzo: " + product.getPrice()  + " ");
			%>
			<br>
			<% 
			total += product.getPrice() * quantity;		
		}%>
		<br>
		<% out.print("Totale: "+total);
		session.setAttribute("total", total);
		%>
		
	</p>
	<strong><a href = "ConfermaOrdine" style="text-decoration: none; color:red;">Paga</a></strong>
	<br>
	<a href="loginUser.jsp" style="text-decoration: none; color:blue;">Log out</a>
</html>