<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">
    
    <%
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    
    if(email!=null && password!=null)
    	if(!email.equalsIgnoreCase("mrossi@gmail.com") || !password.equalsIgnoreCase("mrossi"))
    		response.sendRedirect("failedLogin.jsp");
    %>
    
    <h1>HERSE SHOP</h1>
    
    <h2>WELCOME <%= email %> </h2>
    
    <form action="" method="post">
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