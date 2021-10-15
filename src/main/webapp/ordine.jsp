<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="ShopSession.*, Ecommerce.Product, Ecommerce.OrderItem, Ecommerce.Order, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordine</title>
</head>
<body>

<h1 align ="center"> Grazie per aver effettuato l'ordine </h1>

<hr>

<div align="center">

<h3> Ecco il riepilogo del tuo ordine</h3>

<%
Order order = (Order) session.getAttribute("order");
%>

<table Style="border:1px solid"  >
<caption>	
	<strong> Ordine numero : <%= order.getId() %></strong>
</caption>
<tr><th>IdProdotto</th><th>NomeProdotto</th><th>Prezzo</th><th>Quantità</th></tr>

<%
List<OrderItem> orders = (List<OrderItem>) session.getAttribute("orders");
List<Product> pro = (List<Product>) session.getAttribute("productOrder");
for(OrderItem o: orders){
	for(Product p: pro){
		out.print("<tr>");
		out.print("<td>" + p.getId() + "</td>");
		out.print("<td>" + p.getName() + "</td>");
		out.print("<td>" + p.getPrice() + "</td>");
		out.print("<td>" + o.getQuantity()+ "</td>");
		out.print("</tr>");
	}
}

%>

</table>

</div>

<p> <strong>Costo Totale</strong> <%= order.getAmount() %></p>

<p><strong>Data dell'ordine</strong> <%=order.getDateTime() %></p>

<form action="logout" method="post">
	<input type="submit" value="Logout">
</form>


</body>
</html>