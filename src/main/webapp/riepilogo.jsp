<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="ShopSession.*, Ecommerce.Product, Ecommerce.Order, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Carrello</title>
</head>
<body>

<h1 align="center">Riepilogo Carrello</h1>

<hr>

<div>
<%

String quantitylMsg = (String) session.getAttribute("wrongQuantityE");
if (quantitylMsg != null) {
	%>
	<strong><span style="color:red"><%=quantitylMsg%></span></strong><br><br>
	<%
	session.removeAttribute("wrongQuantityE");
}

%>

<h3> Prodotti acquistati fino ad ora </h3>

<table Style="border:1px solid"  >
<tr><th>Id</th><th>Prodotto</th><th>Prezzo</th><th>Quantità</th></tr>

<%
HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");

for (Integer key : map.keySet()) {
	Object obj = map.get(key);
	Carrello carrello = (Carrello) obj;
	out.print(" Prodotto: ");
	List<Product> products = new Shop().findProductsById(carrello.getProductId());
	for (Product p : products) {
		out.print("<tr>");
		out.print("<td>" + p.getId() + "</td>");
		out.print("<td>" + p.getName() + "</td>");
		out.print("<td>" + p.getPrice() + "</td>");
		out.print("<td>" + carrello.getQuantity() + "</td>");
		out.print("</tr>");
	}
}

%>

</table>

</div>

<form action='catalogo.jsp' method='post'>
	
		<p>
			Torna Agli acquisti: <input type="submit" value="Vai">
		</p>
	
</form>

<form action='EliminaProdotto' method='post'>

		<p style="color:red">Elimina un prodotto:</p>
		
		<p>
			Id Prodotto da eliminare: <br>
			<input type="text" name="productIdE" placeholder= "Product Id" required>
		</p>
		
		<p>
			Quantità da eliminare : <br>
			<input type="text" name="quantityE" placeholder= "Quantity" required>
		</p>
	
		<p>
			 <input type="submit" value="Elimina">
		</p>
	
</form>

<form action='ordineServlet' method='post'>
	
		<p style="color:green">
			Conferma il tuo ordine: <input type="submit" value="Conferma">
		</p>
	
</form>

</body>
</html>