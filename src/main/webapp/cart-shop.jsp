<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="it.beije.herse.web.MetodiJPA"%>
 <%@page import="it.beije.herse.web.ShopEntityManager"%>
  <%@page import="it.beije.herse.web.Product"%>
    <%@page import="it.beije.herse.web.Cart"%>
  <%@page import="java.util.ArrayList"%>
  <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Il tuo carrello</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<jsp:useBean id="user" class="it.beije.herse.web.User" scope="session"></jsp:useBean>
<jsp:useBean id="carts" class="it.beije.herse.web.Cart" scope="session"></jsp:useBean>
<% 
if (user.getEmail() == null) {
	%>
	<span style="color:red">UTENTE NON AUTENTICATO!!!</span>
	<a href="login.jsp">Torna a login</a>
	<%
	
} else {
	
	
%>
<nav id="primary_nav_wrap">
    <br>
    <ul>
        <li class="current-menu-item"><a href="Prodotti">Lista Prodotti</a></li>
        <li class="current-menu-item"><a href="Profilo">Profilo</a></li>
        <li class="current-menu-item"><a href="Storico">Storico Ordini</a></li>
        <li class="current-menu-item"><a href="cart-shop.jsp">Carrello</a></li>
        <li class="current-menu-item"><a href="Logout">Logout</a></li>

    </ul>
</nav>


<br>
<h1 align="center">Carrello di: <%=user.getName().toUpperCase() %></h1>
<table width="100%" border="1"  style="border-collapse:collapse;" align="center">
<tbody>
			<tr>
				<td bgcolor="#a9a9a9" align="center">Nome</td>
				<td bgcolor="#a9a9a9" align="center">Descrizione</td>
				<td bgcolor="#a9a9a9" align="center">Prezzo</td>
				<td bgcolor="#a9a9a9" align="center">Quantità</td>
				
			</tr>
			
<%
		double tot=0;
		int idUser=user.getId();
		List<Cart> list=MetodiJPA.printCartFromID(idUser);
		for(Cart c: list){
			
			Product p=MetodiJPA.printProductFromID(c.getIdProduct());
			tot+=p.getPrice()*c.getqProduct();
		double q=c.getqProduct();
		%><tr>
			
			<td><%=p.getName() %></td>
			<td><%=p.getDescription() %></td>
			<td><%=p.getPrice() %></td>
			<td><%=c.getqProduct() %></td>
			<td  align="center">	
				<form action="Rimuovi" method="post">
				<input type="hidden"  name="cartId" value="<%=c.getIdCarrello() %>"></input>
				<input type="submit"  value="Rimuovi"></td>
				</form>
			
			
	</tr> 
	
			<% 
			
		}	
	
	
%>
<table width="24%" border="1"  style="border-collapse:collapse;" align="right">
<tr>
<td bgcolor="#a9a9a9" align="center">Totale:</td>
</tr>
<tr><td><%=tot%></td></tr>
<table>
	
	
	
		</tbody>
		</table>
		<table>
  <tr>
    <th>
    	<form action="prodotti.jsp" style="align-content: centre">
    		 <input type="submit" value="Torna al catalogo">
    		 </form>
    </th>
    <th>
    	<form action="Ordine" method="post">
    	<input type="hidden"  name="userId" value="<%=user.getId()   %>"></input>
    	 <input type="submit" value="Procedi con l'acquisto"></form></th>
    	  
  </tr>
	
		</table>





<%
}
%>
</body>
</html>