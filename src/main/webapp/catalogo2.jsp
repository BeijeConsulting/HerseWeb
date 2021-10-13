<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ShopSession.*, Ecommerce.Product, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>

<h1 align="center">Catalogo Prodotti</h1>

<hr>

<p>Benvenuto Utente: <strong><%= session.getAttribute("userID") %></strong></p>

<hr>

<div align="center">
<h3> I nostri Prodotti</h3>

<%

//int userId = (int) session.getAttribute("userID");

//System.out.println("userId: " + userId);


List<Product> products = Shop.getProducts();
%>
<table Style="border:1px solid"  >
<tr><th>Id</th><th>Prodotto</th><th>Prezzo</th><th>Quantità</th></tr>
<%
for(Product p: products){
	out.print("<tr>");
	out.print("<td>" + p.getId() + "</td>");
	out.print("<td>" + p.getName() + "</td>");
	out.print("<td>" + p.getPrice() + "</td>");
	out.print("<td>" + p.getQuantity()+ "</td>");
	out.print("</tr>");
}
%>
</table>

</div>



<h3>Cosa vuoi comprare?</h3>

	<form action='CarrelloServlet' method='post'>

		<p>
			Product Id : <br>
			<input type="text" name="productId" placeholder= "Product Id" required>
		</p>

		<p>
			Quantità : <br>
			<input type="text" name="quantity" placeholder= "Quantity" required>
		</p>
		<p>
			Vuoi comprare ancora?: <input type="checkbox" id="yes" name="yes">
				<label for="yes">Si</label>
		</p>
		<p>
			<input type="submit" value="Conferma">
		</p>
	</form>
	
	<form action='riepilogo.jsp' method='post'>
	
		<p>
			<input type="submit" value="Riepilogo Carrello">
		</p>
	
	</form>
</body>
</html>