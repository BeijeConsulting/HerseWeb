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
		if (session.getAttribute("errorQta") != null && !session.getAttribute("errorQta").toString().isEmpty()){
			out.print(session.getAttribute("errorQta").toString());
			session.removeAttribute("errorQta");
		} else if (session.getAttribute("orderConfirm") != null && !session.getAttribute("orderConfirm").toString().isEmpty()) {
			out.print(session.getAttribute("orderConfirm").toString());
			session.removeAttribute("orderConfirm");
		}
	%>

	<form method="post" action="RiepilogoServlet">

		<%= request.getSession().getAttribute("htmlEl") %>
		
		<input type="submit" value="BUY">

	</form>

</body>
</html>