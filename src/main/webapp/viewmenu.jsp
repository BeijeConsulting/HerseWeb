<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
</head>
<body>

	<jsp:useBean id="user" class="it.beije.herse.shop.User" scope="session"></jsp:useBean>

	<a href="viewproduct.jsp"><input type="submit" value="Prodotti"></a>
	<input type="submit" value="Ordini">
	<input type="submit" value="Profilo">

</body>
</html>