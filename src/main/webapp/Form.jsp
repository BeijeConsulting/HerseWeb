<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Ecommerce.Shop, Ecommerce.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get Products</title>
</head>
<body>
	
<p>Benvenuto Utente: <strong>${userId}</strong></p>

<h3> Our Products: </h3>
<%

String userId = request.getParameter("userId");


List<Product> products = Shop.getProducts();

for(Product p: products){
	out.print(p);
	out.print("<br>");
}

//request.setAttribute("userId", userId);
//request.getRequestDispatcher("calcolo.jsp").forward(request, response);

%>

<hr>

<h3 align="center">What do you want to buy</h3>

	<form action='calcolo.jsp' method='post'>

		<p>
			Product Id : <input type="text" name="productId" placeholder= "Product Id" required>
		</p>

		<p>
			Quantity : <input type="text" name="quantity" placeholder= "Quantity" required>
		</p>

		<p>
			<input type="submit" value="Confirm">
		</p>
		<p>
			<input type="hidden" name="userId" id="userId"value=<%= userId %> />
		</p>

	</form>

	<form action='index.html' method="post">

		<p>
			<input type="submit" value="Back">
		</p>

	</form>

</body>
</html>
