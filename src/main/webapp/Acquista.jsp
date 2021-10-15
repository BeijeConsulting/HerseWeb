<%@page import="it.beije.bean.Products"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.model.ProductsModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>HerseShop</h1>
<form action="AcquistaController" method="post">

<%
	ProductsModel productsModel = new ProductsModel(); 
	List<Products> products = productsModel.getProducts();
	for(Products p : products){
%>

<input type="number" min="0" max="<%if(p.getQuantity()>=10){out.print(10);}else{out.print(p.getQuantity());} %>" value="0" style="width: 40px" name="<%=productsModel.getProductId(p)%>"> &emsp;       
<a href="SchedaProdotto.jsp"> ID: <%= productsModel.getProductId(p)%> </a>
<img alt="<%=p.getName() %>" src="<%=p.getImagePath() %>" height="30px" width="30px"/>  
<label for="<%=productsModel.getProductId(p)%>"><%= productsModel.printProducts(p) %></label>
<br>
<%
	}
%>
<br><input type="submit" value="Aggiungi">
</form><br>

<%
if(session.getAttribute("errorCar")!=null){
	%>
	<span style="color: red"> C'e stato un problema nell'aggiunta dei prodotti prova
	 a rifare la log in o registrarti</span><br><br>
	<%
	session.removeAttribute("errorCar");
}
%>
<%
if(session.getAttribute("errorAuth")!=null){
	%>
	<span style="color: red"> Prima di aggiungere prodotti al carrello effettua 
	la Log in o registrati</span><br><br>
	<%
	session.removeAttribute("errorAuth");
}
%>
<%
if(session.getAttribute("authUser")==null){
	%>
	<a href="LogIn.jsp"> Log In </a><br>
	<a href="Registrazione.jsp"> Registrati </a><br><br>
	<%
}
%>
<a href="Home.jsp"> Home </a><br>
</body>
</html>