<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Carrello</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
	<jsp:useBean id="productCart" class="it.beije.herse.web.entity.Product" scope="session"></jsp:useBean>
	<jsp:useBean id="carrello" class="it.beije.herse.web.entity.Carrello" scope="session"></jsp:useBean>
	
	<h1>Carrello</h1>
	<p>
		<% out.print("Id Prodotto: "+productCart.getId() + " "); 
		out.print("Tipo: " + productCart.getName() + " ");
		 out.print("Quantità: " + productCart.getQuantity()  + " "); 
		 out.print("Totale: " + productCart.getPrice()  + " "); 
		 %>
	</p>
	<button type = "submit" type="button" class="btn btn-success" ><a href = "ConfermaOrdine" style="text-decoration: none; color:white;">Paga</a></button>
	<!--  <button type="button" class="btn btn-primary" ><a href = "" style="text-decoration: none; color:white;">Aggiungi un altro prodotto</a></button>-->
</body>
</html>