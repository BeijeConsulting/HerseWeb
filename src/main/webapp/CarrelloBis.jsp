<%@page import="java.util.Collection"%>
<%@page import="it.beije.bean.Products"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.bean.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if((Carrello)session.getAttribute("Carrello")!=null){
Carrello carrello = (Carrello)session.getAttribute("Carrello");
List<Products> prodotti = carrello.getProdotti();
if(prodotti != null){
for(Products p : prodotti){
	int count = 0;
	count = Collection.frequency(prodotti, p);
	

%>
<form action="EliminaProdotti" method="post">
<label for="quantity">ID: <%=p.getId() %></label>
	<select name="quantity" >
		<%for(int i = 0; i <= 10; i++){ %>
		<option value="<%=i %>"> <%=i %> </option>
		<>	
<% } %>
	</select>

<% }}}else{
	out.print("Il tuo carrello e vuoto");
	%>
	
	<br><br> fai qui i tuoi acquisti: <a href="Prodotti.jsp"> Prodotti </a><br>
	oppure torna alla <a href="Home.jsp"> Home </a><br>
	<%
} %>
</form>

</body>
</html>