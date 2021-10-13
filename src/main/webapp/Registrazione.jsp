<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>
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
<form method='post' action='RegistrazioneServlet'>
	<label for="username">Nome:</label><br>
	<input type='text' name='name' required><br>
	<label for="password">Cognome:</label><br>
	<input type='text' name='surname' required><br>
	<label for="password">E-mail:</label><br>
	<input type='text' name='email' required><br>
	<label for="password">Conferma E-mail:</label><br>
	<input type='text' name='conf_email' required><br>
	<label for="password">Password:</label><br>
	<input type='text' name='password' required><br>
	<label for="password">Conferma Password:</label><br>
	<input type='text' name='conf_password' required><br>
	<button type='submit'>Registrati</button>
</form>
</body>
</html>