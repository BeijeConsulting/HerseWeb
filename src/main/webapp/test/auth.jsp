<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authorization check</title>
</head>
<body>

<jsp:useBean id="authUser" class="it.beije.herse.web.User" scope="session"></jsp:useBean>

<jsp:setProperty property="username" name="authUser" param="user_name"/>
<jsp:setProperty property="password" name="authUser"/>

<%
System.out.println("username : " + authUser.getUsername());
System.out.println("password : " + authUser.getPassword());
%>

<strong>BENVENUTO <jsp:getProperty name="authUser" property="username"/>!</strong>

</body>
</html>