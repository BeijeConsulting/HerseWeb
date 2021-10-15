<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="it.beije.herse.web.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AREA PRIVATA</title>
</head>
<body>

<jsp:useBean id="authUser" class="it.beije.herse.web.User" scope="session"></jsp:useBean>

<%
/*User authUser = (User) session.getAttribute("authUser");
if (authUser == null) {
	authUser = new User();
	session.setAttribute("authUser", authUser);	
}*/

if (authUser.getUsername() == null) {
	%>
	<span style="color:red">UTENTE NON AUTENTICATO!!!</span>
	<%
} else {
	%>
	<!-- strong>BENVENUTO < % = authUser.getFirstName() %> < % = authUser.getLastName() %>!</strong -->
	<strong>BENVENUTO <jsp:getProperty name="authUser" property="firstName"/> <jsp:getProperty name="authUser" property="lastName"/>!</strong>
	<%
}
%>
</body>
</html>