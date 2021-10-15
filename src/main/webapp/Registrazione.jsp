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

<form action="RegistrazioneController" method="post">

<label for="nome"> Nome </label><br>
<input type="text" name="nome"><br><br>

<label for="cognome"> Cognome </label><br>
<input type="text" name="cognome"><br><br>

<label for="email"> Email </label><br>
<input type="text" name="email"><br><br>

<label for="password"> Password </label><br>
<input type="password" name="password"><br><br>

<label for="passwordCheck"> Conferma Password </label><br>
<input type="password" name="passwordCheck"><br><br>

<input type="submit" value="Registrati"><br><br>
</form>

<%
if(session.getAttribute("errorEmptyText")!=null){
	%>
	<span style="color: red"> Campi nome e cognome obbligatori</span><br>
	<%
	session.removeAttribute("errorEmptyText");
}
%>
<%
if(session.getAttribute("errorEmail")!=null){
	%>
	<span style="color: red"> Controlla di aver inserito una mail corretta</span><br>
	<%
	session.removeAttribute("errorEmail");
}
%>
<%
if(session.getAttribute("errorPassword")!=null){
	%>
	<span style="color: red"> Le password inserite non coincidono</span><br>
	<%
	session.removeAttribute("errorPassword");
}
%>
<%
if(session.getAttribute("errorUser")!=null){
	%>
	<span style="color: red"> La mail e gia stata utilizzata</span><br>
	<%
	session.removeAttribute("errorUser");
}
%>

<br>Torna alla <a href="Home.jsp"> Home </a><br>
Sei gia registrato? effettua il <a href="LogIn.jsp"> Log In </a>
</body>
</html>