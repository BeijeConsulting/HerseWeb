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

	<jsp:useBean id="prodManager" class="it.beije.herse.shop.manager.ProductManager" scope="session"></jsp:useBean>
	
	<jsp:useBean id="cart" class="it.beije.herse.shop.beans.Cart" scope="session"></jsp:useBean>

	<h1>HERSE SHOP</h1>
	
	<form action="CheckoutServlet" method="post">
	
	ORDER SUMMARY: <br>
	
	<table>
	
	<%
	// Cart cart = (Cart) session.getAttribute("cart");
	List<OrderItem> items = cart.getItems();
	Order order = cart.getOrder();
	// ProductManager prodManager = (ProductManager) session.getAttribute("prodManager");
	// List<Product> products = (List<Product>) session.getAttribute("products");
	for(OrderItem i : items){
		//Product p = products.get(products.indexOf(new Product().setId(i.getProductId());
		Product p = prodManager.selectProducts(i.getProductId()).get(0);
	%>
		<tr><td><%= p.getName() %></td><td>QUANTITY: <%= i.getQuantity() %></td><td>PRICE: <%= i.getQuantity()*p.getPrice() %>$</td>
		<td><input type="submit" name="deleteItem<%= p.getId()%>" value="DELETE <%= p.getName()%>"></td></tr>
	<% }
	//session.setAttribute("total", total);%>
	</table>
	<br>
	TOTAL: <%= cart.getTotal() %>$
	
	<br><br>
	<input type="submit" name="submitPayment" value="CONFIRM & PAY">
	<input type="submit" name="back" value="BACK">
	
	</form>
</body>
</html>