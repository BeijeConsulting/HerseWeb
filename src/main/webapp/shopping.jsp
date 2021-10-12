<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Ecommerce.Shop, Ecommerce.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shop</title>
</head>
<body>

<h1 align='center'>Our Products</h1>

<%

String user = request.getParameter("userId");
	
int userId = Integer.parseInt(user);

Boolean exists = Shop.existUser(userId);

if(exists == false){
	response.sendRedirect("index.html");
} else {	
	request.setAttribute("userId", user);
	request.getRequestDispatcher("Form.jsp").forward(request, response);
}

%>

</body>
</html>

