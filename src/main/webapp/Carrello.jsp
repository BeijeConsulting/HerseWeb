<%@page import="it.beije.model.ProductsModel"%>
<%@page import="it.beije.bean.OrderItems"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.bean.CarrelloNew"%>
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
<p>Qui troverai il riepilogo dei prodotti presenti nel tuo carrello!</p><br><br>

<%
	CarrelloNew carrelloNew = (CarrelloNew)session.getAttribute("carrello");
	if(carrelloNew!=null){
		ProductsModel productsModel = new ProductsModel();
		%>
		<form action="AggiornaController" method="post">
		<%
		List<OrderItems> orderItems = carrelloNew.getOrderItems();
		for(OrderItems oi : orderItems){
%>

<label for="<%=oi.getProductId()%>"><%if(carrelloNew.printProducts(oi)!=null){
	%>
	<img alt="<%=oi.getProductId()%>" src="<%=productsModel.getImageFromOrderItems(oi)%>" height="40px" width="40px">
	<%
	out.print(carrelloNew.printProducts(oi));
} %></label>

<select name="<%=oi.getProductId()%>">
<% 
if(carrelloNew.productAvaible(oi.getProductId(), 10)){
for(int i = 0; i < 11; i++){
%>

 <option value="<%=i %>" <%if(i == oi.getQuantity()){ %>selected="selected"<%} %>><%=i %></option>

 <% 
}}else{
	for(int i = 0; i < carrelloNew.getAvaibleQuantity(oi.getProductId()) + 1; i++){
		%>

		 <option value="<%=i %>" <%if(i == oi.getQuantity()){ %>selected="selected"<%} %>><%=i %></option>

		 <% 
	}
}
 %>
  
</select>
<br>
<%
	}
%>
<br>
Totale prodotti nel carrello: <%=carrelloNew.getNumProduct()%><br>
Totale spesa: <%=carrelloNew.getTotPrice()%><br><br>

		<br><input type="submit" value="aggiorna">
		</form><br>
		<form action="ConfermaController" method="post">
		<input type="submit" value="Acquista">
		</form><br>
<%	
	}
%>

<!-- da qui gestisco i possibili errori -->
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
if(session.getAttribute("errorCarEmpty")!=null){
	%>
	<span style="color: red"> Il tuo carrello al momento e vuoto! </span><br><br>
	<%
	session.removeAttribute("errorCarEmpty");
}
%>
<%
if(session.getAttribute("authUser")==null){
	%>
	<a href="LogIn.jsp"> Log In </a><br>
	<a href="Registrazione.jsp"> Registrati </a><br><br>
	<%
}else{
%>
<a href="Acquista.jsp"> Prodotti </a><br>
<%} %>
<a href="Home.jsp"> Home </a><br>
</body>
</html>