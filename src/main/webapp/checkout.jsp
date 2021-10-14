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
	
	<form action="CheckoutServlet" method="post">
	
	ORDER SUMMARY: <br>
	<table>
	
	<%
	List<OrderItem> items = (List<OrderItem>) session.getAttribute("items");
	Order order = (Order) session.getAttribute("order");
	Double total = 0.0;
	for(OrderItem i : items){
		Product p = ProductManager.selectProducts(i.getProductId()).get(0);
		total += i.getQuantity()*p.getPrice();
	%>
		<tr><td><%= p.getName() %></td><td>QUANTITY: <%= i.getQuantity() %></td><td>PRICE: <%= i.getQuantity()*p.getPrice() %>$</td>
		<td><input type="submit" name="deleteItem<%= p.getId()%>" value="DELETE <%= p.getName()%>"></td></tr>
	<% }
	session.setAttribute("total", total);%>
	</table>
	<br>
	TOTAL: <%= total %>$
	
	<br><br>
	<input type="submit" name="submitPayment" value="CONFIRM & PAY">
	<input type="submit" name="back" value="BACK">
	
	</form>
</body>
</html>