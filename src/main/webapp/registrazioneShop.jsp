<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<h1 align="center">Benvenuto</h1>

<p>Registrati per fare i tuoi acquisti</p>

<hr>


<div align="center">
<form action="RegistrazioneServlet" method="post">
		Nome: <br>
	  	<input type="text" name="nome" placeholder= "Tuo Nome" required><br>
	  	Cognome: <br>
	  	<input type="text" name="cognome" placeholder= "Tuo Cognome" required><br>
	  	Email: <br>
	  	<input type="text" name="email" placeholder= "Tua Email" required><br>
	  	Password:<br>
	  	<input type="text" name="password" placeholder="**************" required><br><br>
	  	<input type="submit" value="Conferma">
	</form> 
</div>

</body>
</html>