<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Shop.User ,Shop.Product,javax.persistence.EntityManager,Shop.ShopEntityManager, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo</title>
</head>
<body>
<body>
	<jsp:useBean id="authUser" class="Shop.User" scope="session"></jsp:useBean>
	<%
	String prodotto_aggiunto = (String) session.getAttribute("prodotto_aggiunto");
	if (prodotto_aggiunto != null) {
	%>
	<span style="color: red"><%=prodotto_aggiunto%></span>
	<br>
	<br>
	<%
	session.removeAttribute("error");
	}
	%>
	<%
	if (authUser.getEmail() == null) {
	%>
	<span style="color: red">UTENTE NON AUTENTICATO!!!</span>
	<%
	} else {
	%>
	Benvenuto,
	<jsp:getProperty name="authUser" property="name" />
	<jsp:getProperty name="authUser" property="surname" />
	, di seguito il nostro catalogo:
	<%
	}
	%>
	<br>
	<br>
	<table>
		<thead>
			<tr>
				<td>Id</td>
				<td>Nome</td>
				<td>Descrizione</td>
				<td>Prezzo</td>
				<td>Quantità Disponibili</td>
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
			<td><%=p.getQty()%></td>
		</tr>
		<%}%>
	</table>
	<form action='CatalogoServlet' method='post'>
		<label for='idProdotto'>Id:</label><br> 
		<input type='text'name='idProdotto'> <br> 
		<label for='qta'>Quantità:</label><br>
		<input type='text' name='qta'><br>
		<button type='submit'>Invio</button>
	</form>
	<form action='Riepilogo.jsp' method='post'>
		<button type='submit'>Vai al carrello</button>
	</form>

</body>
</html>