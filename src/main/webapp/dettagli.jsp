<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ShopSession.*, Ecommerce.Product, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettagli</title>
</head>
<body>

<%

List<Product> products = Shop.getProducts();

List<Integer> ids = new ArrayList<Integer>();

for(Product p: products){
	String prodId = request.getParameter(String.valueOf(p.getId())); 
	if(prodId != null ){
		ids.add(p.getId());
	}
}
	

%>

<h1 align="center">Dettagli del Prodotto </h1>

<hr>


<table Style="border:1px solid"  >
<tr><th>Id</th><th>Nome</th><th>Dettagli</th><th>Prezzo</th><th>Quantità</th><th>Immagine</th></tr>
<%
for(int x: ids){
	List<Product> prod = Shop.getProductsById(x);
	for(Product p: prod){
		out.print("<tr>");
		out.print("<td>" + p.getId() + "</td>");
		out.print("<td>" + p.getDescription() + "</td>");
		out.print("<td>" + p.getName() + "</td>");
		out.print("<td>" + p.getPrice() + "</td>");
		out.print("<td>" + p.getQuantity()+ "</td>");
%>
		<td><img src=<%=p.getImg() %>></td>
 <% 
		out.print("</tr>");
	}
}
%>
</table>


</body>
</html>