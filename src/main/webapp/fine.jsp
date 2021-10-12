<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="ShopSession.*, Ecommerce.OrderItem, Ecommerce.Order, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Summary</title>
</head>
<body>

<h1>Summary</h1>

<%

int userId = (int) session.getAttribute("userId");

int orderId = (int) session.getAttribute("orderId");

Order order = Shop.changeOrder(orderId);

out.print("<h3>Your Order:</h3>");

out.print(order.toString());

out.print("<h3>Your Order Item:</h3>");

List<OrderItem> orders = Shop.findOrderItem(orderId);

for(OrderItem o: orders){
	out.print(o.toString());
	out.print("<br>");
}

%>






</body>
</html>