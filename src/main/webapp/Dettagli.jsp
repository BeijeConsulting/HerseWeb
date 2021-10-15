<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Shop.User ,Shop.Product,javax.persistence.EntityManager,Shop.ShopEntityManager, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettagli prodotti</title>
</head>
<body>
<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Descrizione</td>
				<td>Prezzo</td>
				<td>Immagine</td>
			</tr>
		</thead>
		<%
		EntityManager manager=ShopEntityManager.newEntityManager();
		List<Product> lista = manager.createQuery("SELECT p FROM Product as p").getResultList();
		for (Product p : lista) {
		%>
		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getDesc()%></td>
			<td><%=p.getPrice()%></td>
			<td><img src="<%=p.getPath()%>" alt="Image not found"></td>
		</tr>
		<%}%>
	</table>
	<form action='TornaAcquistiServlet' method='post'>
		<button type='submit'>Torna agli acquisti</button>
	</form>

</body>
</html>