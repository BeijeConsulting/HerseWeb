<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.bean.Products"%>
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
<h1>Herse Shop</h1>
<h4>carrello</h4><br>

<%
Carrello carrello = (Carrello)session.getAttribute("Carrello");

if(carrello!=null){
	for (Products p : carrello.getProdotti()) {
		
		
	%>
	<form action="EliminaProdotti" method="post">
	<input type="checkbox" name="<%=p.getId()%>">
	<label for="<%=p.getId()%>"> ID: <%=p.getId() %> | Nome: <%=p.getName() %> | Descrizione: <%=p.getDescription() %> | Prezzo: <%=p.getPrice() %>$ | Quantita: <%=p.getQuantity() %> </label>	<br>
	<%
	}
%>
<p>Totatle prodotti nel carrello: <%=(int)carrello.getQuantita() %></p><br>
<p>Spesa totale: <%=carrello.getTotale() %></p>
<br><input type="submit" value="Elimina">
</form>
<%
if(carrello.getProdotti().size()>0){
%>
<br><form action="Acquista" method="post">
<input type="submit" value="Acquista">
</form>
<%
}
	%>
	<br><br><a href="Prodotti.jsp"> Prodotti </a><br>

<%
}else{
%>
	<%out.print("Carrello vuoto");%>
<%
}
%>
<br>
<a href="Home.jsp"> Home </a><br>

</body>
</html>