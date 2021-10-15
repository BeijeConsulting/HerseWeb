<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Shop.Carrello,Shop.Product,javax.persistence.EntityManager,Shop.ShopEntityManager, java.util.*"%>
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
				<td>Prezzo</td>
				<td>Quantità Acquistate</td>
			</tr>
		</thead>
		<%
		
		EntityManager manager=ShopEntityManager.newEntityManager();
		ArrayList<Carrello> carrello=(ArrayList<Carrello>)session.getAttribute("carrello");
		for (Carrello c : carrello) {
		//	Product p=Funzioni.getProdotto(c.getId_product());
			Product p=manager.find(Product.class, c.getId_product());
		%>
		<tr>
			<td><%=c.getId_product()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getPrice()%></td>
			<td><%=c.getQty()%></td>
		</tr>
		<%
		}%>
	</table>
	<form action='TornaAcquistiServlet' method='post'>
		<button type='submit'>Torna agli acquisti</button>
	</form>
	<form action='EndServlet' method='post'>
		<button type='submit'>Termina acquisti</button>
	</form>
</body>
</html>