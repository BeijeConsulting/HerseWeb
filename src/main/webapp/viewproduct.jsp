<%@page
	import="javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction"%>
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

	<%
	session = request.getSession();
	if (session.getAttribute("orderConfirm") == null) {

		if (user.getId() == null)
			response.sendRedirect("index.html");
		else if (session.getAttribute("errorQta") != null)
			out.print(session.getAttribute("errorQta"));
	%>

	<%@ page import="it.beije.herse.shop.Product" %>
	<%@ page import="it.beije.herse.shop.ManagerCRUD" %>
	<%@ page import="static it.beije.herse.shop.MyShop.*" %>
	<%@ page import="java.util.List" %>

	<%!public String newHTMLProd(Product p) {
		return "<input type=\"checkbox\" name=\"" + p.getId() + "\" value=\"" + p.getId() + "\">"
				+ "<input type=\"text\" name=\"prodName" + p.getId() + "\" value=\"" + p.getName() + "\" readonly>\n"
				+ "<input type=\"text\" name=\"prodDescription" + p.getId() + "\" value=\"" + p.getDescription()
				+ "\" readonly>\n" + "<input style=\"width:30px\" type=\"number\" name=\"prodQta" + p.getId()
				+ "\" placeholder=\"0\"><br>\n";
	}%>

	<form method="post" action="RiepilogoServlet">

		<%
		ManagerCRUD m = (ManagerCRUD)session.getAttribute("managerCRUD");
		List<Product> products = getProducts(m);

		StringBuilder s = new StringBuilder();

		for (Product p : products)
			s.append(newHTMLProd(p));

		out.print(s);
		%>
		<input type="submit" value="BUY">

	</form>
	<%
	} else {
		String confirm = (String) request.getSession().getAttribute("orderConfirm");
		request.getSession().removeAttribute("orderConfirm");
		out.print(confirm);
	%>
	<br><a href="viewproduct.jsp"><input type="submit"
		value="Continua ad acquistare"></a>
	<%
	}
	%>

</body>
</html>