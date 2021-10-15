<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>HerseShop</h1>

<form action="LogInController" method="post">
<label for="email"> Email </label><br>
<input type="text" name="email"><br><br>

<label for="password"> Password </label><br>
<input type="password" name="password"><br><br>

<input type="submit" value="Log in"><br><br>
</form>

<%
if(session.getAttribute("errorUserNotReg")!=null){
	%>
	<span style="color: red"> La mail non e presente nei nostri registri: </span>
	<a href="Registrazione.jsp"> Registrati </a><br>
	
	<%
	session.removeAttribute("errorUserNotReg");
}
%>
<%
if(session.getAttribute("errorPasswordLogIn")!=null){
	%>
	<span style="color: red"> La Password inserita non e corretta</span><br>
	<%
	session.removeAttribute("errorPasswordLogIn");
}
%>

Torna alla <a href="Home.jsp"> Home </a>
</body>
</html>