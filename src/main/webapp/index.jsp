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
      
      <%
      String email = (String) session.getAttribute("email");
      String password = (String) session.getAttribute("password");
      %>
      
    <form action="IndexServlet" method="post">
    	INSERT USER AND PASSWORD<br> 
    	EMAIL: <input type="email" name="email" <%if(email!=null && password!=null){ %>value="<%=email %>" <%} %> ><br>
    	PASSWORD: <input type="password" name="password" <%if(email!=null && password!=null){ %>value="<%=password %>" <%} %> ><br><br>
    	<input type=submit name="submitLogin" value="LOGIN">
    </form>
     
</body>

</html>