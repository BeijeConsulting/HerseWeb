<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Shop.Product,Shop.Order, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquisto completato</title>
</head>
<body>
	Acquisto terminato!
	<table>
		<thead>
			<tr>
				<td>Totale ordine:</td>
			</tr>
		</thead>
		<tr>
			<td><%
			Order order=(Order)session.getAttribute("order");
			Double tot=order.getAmount();
			%>
			<%=tot%></td>
		</tr>
	</table>
	<form action='ResetCarrello' method='post'>
		<button type='submit'>Nuovo carrello</button>
	</form>
	<form action='ResetAll' method='post'>
		<button type='submit'>Logout</button>
	</form>

</body>
</html>