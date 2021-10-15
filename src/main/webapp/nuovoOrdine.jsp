
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="it.beije.herse.web.my.ListaProdotti"%>
  <%@page import="java.util.List"%>
 <%@page import="it.beije.herse.web.entity.Product"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Nuovo ordine</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">


	<%
	String error = (String) session.getAttribute("error");
	if (error != null) {
		%>
		<span style="color:red"><%=error%></span><br><br>
		<%
		session.removeAttribute("error");
	}
	%>
	<% 
	String errorInput = (String) session.getAttribute("quantity_input_error");
	if (errorInput != null) {
		%>
		<span style="color:red"><%=error%></span><br><br>
		<%
		session.removeAttribute("quantity_input_error");
	}
	%>
	
	<% 
	String errorQuantity = (String) session.getAttribute("quantita");
	if (errorInput != null) {
		%>
		<span style="color:red"><%=error%></span><br><br>
		<%
		session.removeAttribute("quantita");
	}
	%>
	
	<h1>Nuovo ordine</h1>
	<br>
	<strong>Inserisci id prodotto e quantità del prodotto che vuoi aggiungere al carrello</strong>
	<br>
	<form action="carrello" method="post">
		<label for="id">Id prodotto: </label>
		<input type="text" name ="id">	
		<br>
		<label for="quantita">Quantità prodotto: </label>
		<input type="text" name ="quantita">
		<br>
		<br>
		<button type ="submit" type="button" class="btn btn-success" name="btn_submit">Vai al carrello</button>
	</form>
	<br>
	<table class="table table-hover">
			<thead>
				<tr>
					<th>Prodotti disponibili</th>
					<th>Quantità disponibile</th>
					<th>Id prodotto</th>
					<th>Prezzo</th>
				</tr>
			</thead>
			<tbody>
				<%List<Product> products = ListaProdotti.selectProducts(); 
						for(Product p : products) {
							%>
				<tr>
					<td>
						<span><%  out.print(p.getName()); %></span>
						<a href="schedaProdotti">Dettaglio prodotti</a>
					</td>
					<td><%out.print(p.getQuantity()); %></td>
					<td><%out.print(p.getId()); %></td>
					<td name = "prezzo" value = "<%p.getPrice(); %>"><%out.print(p.getPrice()); %></td>
				</tr>
	
						<%}%>

			</tbody>
		</table>
	
	<br><br>
	<a href="menuUser.jsp" style="text-decoration: none; color:black;">-> Torna al menu</a>
	<br>
	<a href="logout" style="text-decoration: none; color:blue;">Log out</a>
</body>

</html>