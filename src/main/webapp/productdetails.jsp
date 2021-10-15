<%@page import="it.beije.herse.shop.manager.ProductManager"%>
<%@page import="it.beije.herse.shop.beans.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>
<body bgcolor="black" text="white">

	<jsp:useBean id="prodManager" class="it.beije.herse.shop.manager.ProductManager" scope="session"></jsp:useBean>

	<h1>HERSE SHOP</h1>
	
	<%
	Integer prodId = (Integer) session.getAttribute("prodId");
	session.removeAttribute("prodId");
	// ProductManager prodManager = (ProductManager) session.getAttribute("prodManager");
	Product p = prodManager.selectProducts(prodId).get(0);
	%>
	
	PRODUCT DETAILS: <br>
	<table>
		<tr><td>ID: </td><td><%= p.getId() %></td></tr>
		<tr><td>NAME: </td><td>"<%= p.getName() %>"</td></tr>
		<tr><td>DESCRIPTION: </td><td><%= p.getDescription() %></td></tr>
		<tr><td>PRICE: </td><td><%= p.getPrice() %>$</td></tr>
		<tr><td>IN STOCK: </td><td><%= p.getQuantity() %></td></tr>
	</table>
	<br>
	<form action="ReturnToOrderServlet" method="post">
	<br><br>
	<input type="submit" name="back" value="BACK">
	</form>
</body>
</html>