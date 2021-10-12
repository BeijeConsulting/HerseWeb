<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>

	<a href="ViewProduct?id=<%= request.getParameter("id") %>"><input type="submit" value="Prodotti"></a>
	<input type="submit" value="Ordini">
	<input type="submit" value="Profilo">

</body>
</html>