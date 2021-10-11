<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
</head>

    
<body bgcolor="black" text="white">
    
    <h1>HERSE SHOP</h1>
    
    <br>
    
    <%
    
    %>
    
    <form action="" method="post">
        EMAIL AND PASSWORD NOT FOUND<br>
        <input type="radio" name="failedLoginAction" value="retry">RETRY<br>
        <input type="radio" name="failedLoginAction" value="signIn">SIGN IN AS <%= request.getParameter("email") %><br>
        <br>
        <input type=submit name="submitFailed" value="SUBMIT">
        <input type="hidden" name="email" id="email"> 
    </form>
    
</body>
    
</html>