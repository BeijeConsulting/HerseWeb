<%@page import="javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction"%>
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

		<jsp:useBean id="user" class="it.beije.herse.shop.User" scope="session"></jsp:useBean>			
		<jsp:useBean id="carrello" class="it.beije.herse.shop.Carrello" scope="session"></jsp:useBean>

		<%@ page import="it.beije.herse.shop.*"%>
		<%@ page import="static it.beije.herse.shop.MyShop.*"%>
		<%@ page import="java.util.List"%>
		
		<%session = request.getSession(); %>

		<%!public String newHTMLProd(Product p) {
			return "<input type=\"checkbox\" name=\"prodId\" value=\"" + p.getId() + "\">"
					+ "<input type=\"text\" name=\"prodName\" value=\"" + p.getName() + "\" readonly>\n"
					+ "<input type=\"text\" name=\"prodDescription\" value=\"" + p.getDescription() + "\" readonly>\n"
					+ "<input style=\"width:30px\" type=\"text\" name=\"prodQta\" placeholder=\"0\"><br>\n";
		}%>

		<%
		List<Product> products = getProducts();

		StringBuilder s = new StringBuilder();

		for (Product p : products)
			s.append(newHTMLProd(p));

		out.print(s);
		%>

		<a href="FinalizeOrder"><input type="button" value="BUY"></a>

</body>
</html>