<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
	
</head>
<body style="margin:1%">
<h1>Inserisci le tue credenziali</h1>

<%

String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("error");
}
%>
	<form action="LoginUser" method="post">
		<div>
			
			<label for="email">Email: </label>
			<input type="text" name="email">
			<br>
			<br>
			
			<label for="password">Passwod: </label>
			<input type="text" required name ="password">
			<br>
			<br>
			
			<button type="submit" type="button" class="btn btn-primary">Login</button>
		</div>
	</form>

</body>
</html>