<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Ecommerce.Shop, Ecommerce.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calcolo</title>
</head>
<body>

<%
String user = request.getParameter("userId");
String product = request.getParameter("productId");
String quantityS = request.getParameter("quantity");

int userId = Integer.parseInt(user);
int productId = Integer.parseInt(product);
int quantity = Integer.parseInt(quantityS);

int orderId = 0;

orderId = Shop.checkQuantity(productId, quantity, userId);
if (orderId == 0) {
	response.sendRedirect("index.html");
} else {
	Order order = Shop.changeOrder(orderId);
	out.print("<br>" + "Your Order: " + order.toString());
}
%>

</body>
</html>