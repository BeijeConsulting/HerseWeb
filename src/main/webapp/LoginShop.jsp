<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<h1 align="center">Benvenuto</h1>

<p>Accedi per fare i tuoi acquisti</p>

<hr>

<%
String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("error");
}

String faiLogin = (String) session.getAttribute("faiLogin");
if (faiLogin != null) {
	%>
	<span style="color:red"><%=faiLogin%></span><br><br>
	<%
	session.removeAttribute("faiLogin");
}
%>
<div align="center">
<form action="LoginServletBean" method="post">
	  Email: <br>
	  <input type="text" name="email" placeholder= "Your Email" required><br>
	  Password:<br>
	  <input type="password" name="password" placeholder="**************" required><br><br>
	  <input type="submit" value="Conferma">
	</form> 
</div>

</body>
</html>