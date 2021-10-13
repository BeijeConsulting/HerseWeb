<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="it.beije.bean.Users"%>
<%@page import="javax.persistence.EntityTransaction"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="it.beije.bean.SingletonEntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Registrazione" method="post">
		<label for="nome">Name</label><br> <input type="text" name="nome"><br>
		<label for="cognome">Surname</label><br> <input type="text"
			name="cognome"><br> <label for="email">Email</label><br>
		<input type="text" name="email"><br> <label
			for="password">Password</label><br> <input type="text"
			name="password"><br> <label for="passwordCheck">Password
			Confirmation</label><br> <input type="text" name="passwordCheck"><br>
		<br> <input type="submit" value="Registrati">
	</form>

	<%
	String userError = "Utente gia esistente";
	if(session.getAttribute("userError") != null){
	%>
	<span style="color: red"><%=userError%></span>
	<br>
	<br>
	<%
	session.removeAttribute("userError");
	}
	
	String passwordError = "Password errata";
	if(session.getAttribute("passwordError") != null){
	%>
	<span style="color: red"><%=passwordError%></span>
	<br>
	<br>
	<%
	session.removeAttribute("passwordError");
	}
	
	String emailError = "Email errata";
	if(session.getAttribute("emailError") != null){
	%>
	<span style="color: red"><%=emailError%></span>
	<br>
	<br>
	<%
	session.removeAttribute("emailError");
	}
	%>
	
	<br>
	<h6>Sei gia registrato? </h6>
	<a href="login.jsp"> Log In </a><br>
</body>
</html>