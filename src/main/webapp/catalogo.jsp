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
List<Product> products = Shop.getProducts();
%>
<table Style="border:1px solid">
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

<form Style="border:1px solid" action="dettagli.jsp" method="post">
<p>Di quale prodotto vuoi vedere i dettagli? (scegli in base all'id)</p>
	<%
	for(Product p: products){
	%>
		<input type="checkbox" id=<%=p.getId() %> name=<%=p.getId() %>><label for="yes"><%=p.getId() %> </label>
		
	<%
	}
	%>
	<p>
		<input type="submit" value="vedi">
	</p>
</form>

<%
String error = (String) session.getAttribute("wrongQuantity");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("wrongQuantity");
}

%>

</div>


<h3>Cosa vuoi comprare?</h3>

	<form action='CarrelloServlet' method='post'>

		<p>
			Id Prodotto : <br>
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