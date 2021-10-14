<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HERSE SHOP</title>
</head>

<body bgcolor="black" text="white">
    
    <h1>HERSE SHOP</h1>
    
    <br>
      
    <form action="UpdateProfileServlet" method="post">
    INSERT A NEW VALUE IN THE FIELDS TO UPDATE:<br> 
    <table>
    	<tr><td>NAME: </td><td><input type="text" name="newName" ></td></tr>
    	<tr><td>SURNAME: </td><td><input type="text" name="newSurname"></td></tr>
    	<tr><td>EMAIL: </td><td><input type="email" name="newEmail" ></td></tr>
    	<tr><td>PASSWORD: </td><td><input type="password" name="newPassword"></td></tr>
    </table>
    <br>
    <input type=submit name="submitUpdate" value="UPDATE">
    </form>
     
</body>

</html>