<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>!LOGIN!</title>
</head>
<body>
	<%
	String error = (String) session.getAttribute("error");
	if (error != null) {
	%>
	<span style="color: red"><%=error%></span>
	<br>
	<br>
	<%
	session.removeAttribute("error");
	}
	%>
	<form action='LoginServlet' method='post' >
		<label for="username">Username:</label><br>
		<input type='text' name='username'><br>
		<label for="password">Password:</label><br>
		<input type='text' name='password'><br>
		<button type='submit'>Login</button>
	</form>
	<form action='Registrazione.jsp'>
	<button type='submit'>Registrati</button>
	</form>
</body>
</html>