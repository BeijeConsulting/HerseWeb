<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="it.beije.herse.shop.classes.*"%>
<%@page import="it.beije.herse.shop.manager.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">
    
    <%User u = (User) session.getAttribute("loggedUser");%>
    <jsp:useBean id="loggedUser" class="it.beije.herse.shop.classes.User" scope="session"></jsp:useBean>
    <jsp:setProperty property="email" name="loggedUser" value="<%= u.getEmail() %>"/>
    <jsp:setProperty property="password" name="loggedUser" value="<%= u.getPassword() %>"/>
    <jsp:setProperty property="name" name="loggedUser" value="<%= u.getName() %>"/>
    <jsp:setProperty property="surname" name="loggedUser" value="<%= u.getSurname() %>"/>
    <jsp:setProperty property="id" name="loggedUser" value="<%= u.getId() %>"/>
    
    <h1>HERSE SHOP</h1>
    
    <h2>WELCOME <jsp:getProperty property="email" name="loggedUser"/> </h2>
    
    <form action="UserChoiceServlet" method="post">
        CHOOSE AN OPTON:<br>
        <input type="radio" name="userAction" value="showProfile">SHOW MY PROFILE<br>
        <input type="radio" name="userAction" value="showOrderHistory">SHOW MY ORDER HISTORY<br>
        <input type="radio" name="userAction" value="newOrder">NEW ORDER<br>
        <input type="radio" name="userAction" value="updateProfile">UPDATE PROFILE<br>
        <br>
        <input type=submit name="submitUserAction" value="SUBMIT">
    </form>
     
</body>
    
</html>