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
String user = (String) session.getAttribute("userID");
int userId = Integer.parseInt(user);


int orderId = 0;

if(user != null){
	orderId = Shop.insertOrder(userId);
}


HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");

for (Integer key : map.keySet()) {
	Object obj = map.get(key);
	Carrello carrello = (Carrello) obj;
	List<Product> products = Shop.getProductsById(carrello.getProductId());
	for (Product p : products) {
		Shop.insertOrderItem(orderId, p.getId(), carrello.getQuantity());
	}
}


Order order = Shop.changeOrder(orderId);
%>

<table Style="border:1px solid"  >
<caption>	
	<strong><p> Ordine numero : <%= order.getId() %></p></strong>
</caption>
<tr><th>IdProdotto</th><th>NomeProdotto</th><th>Prezzo</th><th>Quantità</th></tr>

<%
List<OrderItem> orders = Shop.findOrderItem(orderId);
for(OrderItem o: orders){
	List<Product> pro = Shop.getProductsById(o.getProductId());
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