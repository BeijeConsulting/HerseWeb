<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Shop.Product,Shop.Funzioni, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo Ordine</title>
</head>
<body>
	Carrello
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Descrizione</td>
				<td>Prezzo</td>
				<td>Quantità Acquistate</td>
			</tr>
		</thead>
		<%
		ArrayList<Product> carrello=(ArrayList<Product>)session.getAttribute("carrello");
		ArrayList<Integer> qta=(ArrayList<Integer>)session.getAttribute("qta");
		int i=0;
		for (Product p : carrello) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getDesc()%></td>
			<td><%=p.getPrice()%></td>
			<td><%=qta.get(i)%></td>
		</tr>
		<%i++;
		}%>
	</table>
	<form action='Catalogo.jsp' method='post'>
		<button type='submit'>Torna agli acquisti</button>
	</form>
	<form action='EndServlet' method='post'>
		<button type='submit'>Termina acquisti</button>
	</form>
</body>
</html>