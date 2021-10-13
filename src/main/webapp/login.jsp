<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<%
String passwordError = "Password errata";
if(session.getAttribute("passwordError") != null){
%>
<span style="color: red"><%=passwordError%></span>
<br>
<br>
<%
session.removeAttribute("passwordError");
}

%>

	<form action="LogIn" method="post">
	  <label for="email">Email:</label><br>
	  <input type="text" name="email"><br>
	  <label for="password">password:</label><br>
	  <input type="text" name="password"><br><br>
	  <input type="submit" value="Submit">
	</form> 

</body>
</html>