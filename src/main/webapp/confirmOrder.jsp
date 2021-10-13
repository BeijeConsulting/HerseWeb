<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.herse.shop.beans.*"%>
<%@page import="it.beije.herse.shop.manager.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">
	<h1>HERSE SHOP</h1>
	
	<h1>Thank you for your purchase!</h1>
	
	ORDER SUMMARY: <br>
	<table>
	<%
	List<OrderItem> items = (List<OrderItem>) session.getAttribute("items");
	Order order = (Order) session.getAttribute("order");
	
	for(OrderItem i : items){
		Product p = ProductManager.selectProducts(i.getProductId()).get(0);
	%>
		<tr><td><%= p.getName() %></td><td>QUANTITY: <%= i.getQuantity() %></td><td>PRICE: <%= i.getQuantity()*p.getPrice() %>$</td></tr>
	<% }%>
	</table>
	<br>
	TOTAL: <%= order.getAmount() %>$
</body>
</html>