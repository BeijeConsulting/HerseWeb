<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Home page</h1>
	<Form action="HomePage" method="post">
		<label for="user">User</label><br> <input type="text" name="user"><br>

		<label for="password">Password</label><br> <input type="text"
			name="password"><br><br> <input type="submit" value="submit"><br><br>

		<% 
		String error = (String) session.getAttribute("error");
        if(error != null){
        %>
		<span style="color: red; font: bold;"><%=error%></span><br>
		<br>
		<%
		
        } %>
	</Form>
	
	<dir>
	<p>Non sei ancora regitsrato?
	<a href="Registrazione.jsp"> Registrati </a></p><br>
	</dir>
</body>
</html>