<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Shop.Product,Shop.Funzioni, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acquisto completato</title>
</head>
<body>
	Acquisto terminato!
	<table>
		<thead>
			<tr>
				<td>Totale ordine:</td>
			</tr>
		</thead>
		<tr>
			<td><%=session.getAttribute("tot")%></td>
		</tr>
	</table>
	<form action='ResetCarrello' method='post'>
		<button type='submit'>Torna agli acquisti</button>
	</form>
	<form action='ResetAll' method='post'>
		<button type='submit'>Conferma e torna alla Hompage</button>
	</form>

</body>
</html>