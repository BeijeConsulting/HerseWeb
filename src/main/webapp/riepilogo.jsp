<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo ordini</title>

<h2> Riepilogo ordini </h2>

<div>

<% String s = (String) session.getAttribute("lista");
%>

<%= s %>

</div>
</head>
<body>

</body>
</html>