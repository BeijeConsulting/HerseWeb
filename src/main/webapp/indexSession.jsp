<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome - Index</title>
</head>
<body>

<h1 align="center">Welcome</h1>

<hr>

<%

String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<strong><span style="color:red"><%=error%></span></strong><br><br>
	<%
	session.removeAttribute("error");
}
%>

<form action="ServletShop" method="post">
  <label for="userId">Id:</label><br>
  <input type="text" name="userId" placeholder="your id" required><br>
  
<input type="submit" value="Submit">
</form> 

</body>
</html>