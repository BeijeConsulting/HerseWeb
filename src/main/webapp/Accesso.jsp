<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accesso</title>
</head>
<body>

<%
String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("error");
}
%>

<form action = "shop" method = "post">
<label for="username">Username</label><br>
<input type="text" name = "username"><br>
<label for="password">Password</label><br>
<input type="text" name = "password"><br>
<input type="submit" value="Submit">
</form>

</body>
</html>