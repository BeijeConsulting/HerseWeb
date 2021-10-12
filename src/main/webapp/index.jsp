<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Benvenuti Herse</title>
</head>
<body>
<h1>Hello Herse!!</h1>

<%
//String error = request.getParameter("error");
String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("error");
}
%>
<form action="hello" method="post">
  <label for="user">Username:</label><br>
  <input type="text" name="username"><br>
  <label for="pass">Password:</label><br>
  <input type="password" name="password"><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>