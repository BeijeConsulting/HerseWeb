<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">
    
    <h1>HERSE SHOP</h1>
    
    <form action="LoginFailServlet" method="post">
       <h2><font color="red">EMAIL AND PASSWORD NOT FOUND</font></h2>
        <input type="radio" name="failedLoginAction" value="retry">RETRY<br>
        <input type="radio" name="failedLoginAction" value="signIn">SIGN IN AS <%= session.getAttribute("email") %><br>
        <br>
        <input type=submit name="submitFailed" value="SUBMIT">
    </form>
    
</body>
    
</html>