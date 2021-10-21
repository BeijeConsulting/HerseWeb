<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css"/>
<title>Login Page</title>
</head>
<body>
<%
String error = (String) session.getAttribute("error");
if (error != null) {
	%>
	<span style="color:red"><%=error%></span><br><br>
	<%
	session.removeAttribute("error");
}
%>

 </div>
        
        <br><br>
        <div class="content">
            <h1>Login</h1>

        </div>
        <div class="content">
           <form action="login" method="post">
               <input type="text" name="email" placeholder="Inserisci email"><br><br>
               <input type="text" name="password" placeholder="Inserisci password"><br><br>
               <input type="reset" value="Clear" />
	  		  <input type="submit" value="Login">
            </form>
            <br> <br>
           
        </div>

	<div>
	<h1> Registrazione</h1>
	<div class="content">
           <form action="registration" method="post">
            <input type="text" name="surname" placeholder="Inserisci il tuo cognome" required=""><br><br>
            <input type="text" name="name" placeholder="Inserisci il tuo nome" required=""><br><br>
               <input type="text" name="email" placeholder="Inserisci email" required=""><br><br>
               <input type="text" name="password" placeholder="Inserisci password" required=""><br><br>
               
               <input type="reset" value="Clear" />
	  		  <input type="submit" value="Registration">
            </form>
	
	</div>
        <div class="footer">
            <p> © Shop </p>
        </div>

	

</body>
</html>