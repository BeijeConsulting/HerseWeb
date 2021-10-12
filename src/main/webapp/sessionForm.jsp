<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ShopSession.*, Ecommerce.Product, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buy</title>
</head>
<body>

<p>Benvenuto Utente: <strong><%= session.getAttribute("userId") %></strong></p>

<h3> Our Products: </h3>

<%

int userId = (int) session.getAttribute("userId");

System.out.println("userId: " + userId);


List<Product> products = Shop.getProducts();

for(Product p: products){
	out.print(p);
	out.print("<br>");
}

%>

<%

String quantityMsg = (String) session.getAttribute("wrongQuantity");
if (quantityMsg != null) {
	%>
	<strong><span style="color:red"><%=quantityMsg%></span></strong><br><br>
	<%
	session.removeAttribute("error");
}
%>

<h3 align="center">What do you want to buy</h3>

	<form action='Check' method='post'>

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
		
		<p>
			Buy more: <input type="checkbox" id="yes" name="yes">
				<label for="yes">Yes</label>
		</p>

	</form>

	<form action='index.html' method="post">

		<p>
			<input type="submit" value="Back">
		</p>

	</form>


</body>
</html>