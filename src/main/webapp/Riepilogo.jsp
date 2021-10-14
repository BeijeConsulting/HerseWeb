<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo</title>
</head>
<body>

<%@page import="it.beije.herse.shop.*"%>
<%@page import="static it.beije.herse.shop.MyShop.*"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<jsp:useBean id="carrello" class="it.beije.herse.shop.Carrello" scope="session"></jsp:useBean>
<jsp:useBean id="user" class="it.beije.herse.shop.User" scope="session"></jsp:useBean>

<h1>Riepilogo</h1>

<%
for(OrderItem item : carrello.getItems()){
	Product p = getProduct(item.getProductId(), (ManagerCRUD)request.getSession().getAttribute("managerCRUD"));
	String htmlEl = "<html><body><input style=\"width:100px\" type=\"text\" value=\"" + p.getName() + "\" readonly>\n"
					+ "<input style=\"width:100px\" type=\"text\" value=\"" + p.getDescription() + "\" readonly>\n"
					+ "<input style=\"width:70px\" type=\"text\" value=\"" + item.getSellPrice() + "\" readonly>\n"
					+ "<input style=\"width:40px\" type=\"text\" value=\"" + item.getQuantity() + "\" readonly><br>\n";
	out.print(htmlEl);
}
%>

<br><a href="OrderConfirm"><button>Conferma Ordine</button></a>

</body>
</html>