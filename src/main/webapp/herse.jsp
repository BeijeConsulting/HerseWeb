<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Herse JSP</title>
</head>
<body>
<%

String fname = (String) session.getAttribute("fname");
String lname = (String) session.getAttribute("lname");

%>
<p>BENVENUTO <%= fname + " " + lname %></p>

</body>
</html>