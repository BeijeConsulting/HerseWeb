<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Prodotti</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<h1>Catalogo</h1>

	<form action="FinalizeOrder" method="get">

		<jsp:useBean id="user" class="it.beije.herse.shop.User"
			scope="session"></jsp:useBean>

		<%@ page import="it.beije.herse.shop.*"%>
		<%@ page import="static it.beije.herse.shop.MyShop.*"%>
		<%@ page import="java.util.List"%>

		<%!public String newHTMLProd(Product p) {
				return "<input type=\"text\" name=\"" + p.getId() + "\" value=\"" + p.getName() + "\" readonly>"
					+ "<input type=\"text\" name=\"prodDescription\" value=\"" + p.getDescription() + "\" readonly>"
					+ "<input type=\"text\" name=\"prodQta" + p.getId() + "\" placeholder=\"0\">"
					+ "<input type=\"button\" value=\"+\" onClick=\"\"><br>";
		}%>

		<%
		List<Product> products = getProducts();

		StringBuilder s = new StringBuilder();

		for (Product p : products)
			s.append(newHTMLProd(p));

		out.print(s);
		%>

		<input type="submit" value="BUY">

	</form>


</body>
</html>