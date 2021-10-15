<%@page import="it.beije.herse.shop.manager.OrderManager"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.herse.shop.manager.UserManager"%>
<%@page import="it.beije.herse.shop.beans.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">

	<jsp:useBean id="loggedUser" class="it.beije.herse.shop.beans.User" scope="session"></jsp:useBean>
	
	<jsp:useBean id="userManager" class="it.beije.herse.shop.manager.UserManager" scope="page"></jsp:useBean>
	<jsp:useBean id="orderManager" class="it.beije.herse.shop.manager.OrderManager" scope="page"></jsp:useBean>

	<h1>HERSE SHOP</h1>
	
	MY ORDER HISTORY: <br>
	
	
	<%
	//User u = (User) session.getAttribute("loggedUser");
	Integer userId = loggedUser.getId();
	
	// UserManager userManager = (UserManager) session.getAttribute("userManager");
	// OrderManager orderManager = (OrderManager) session.getAttribute("orderManager");
	
	List<Order> orders = userManager.getOrders(userId);
	for(Order o : orders){
	%>
	<table>
		<tr>
			<th>ORDER ID: <%= o.getId() %></th>
			<th>AMOUNT: <%= o.getAmount() %>$</th>
			<th>DATE: <%= DateTimeFormatter.ISO_DATE.format(o.getDateTime()) %></th>
			<th>TIME: <%= DateTimeFormatter.ISO_TIME.format(o.getDateTime()) %></th>
		</tr>
		<%
		List<OrderItem> items = orderManager.getOrderItems(o.getId());
		for(OrderItem i : items){ %>
		<tr>
			<td></td>
			<td>PRODUCT ID: <%= i.getProductId() %></td>
			<td>PRICE: <%= i.getSellPrice() %>$</td>
			<td>QUANTITY: <%= i.getQuantity() %></td>
		</tr>
		<%} %>
	</table><br>
	<%} %>
	
	<form action="ReturnToMenuServlet" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>