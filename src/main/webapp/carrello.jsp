<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
String username = (String) session.getAttribute("username");
%>
<body>
<h1>Benvenuto in carrello <%= username %></h1>
</body>
</html>