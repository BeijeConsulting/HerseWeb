<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="it.beije.bean.Products"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.bean.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if((Carrello)session.getAttribute("Carrello")!=null){
Carrello carrello = (Carrello)session.getAttribute("Carrello");
List<Products> prodotti = carrello.getProdotti();
if(prodotti != null){
//Clono la lista 
List<Products> prodottiClone = new ArrayList<Products>();
for(Products p : prodotti){
	prodottiClone.add(p);
}
System.out.println(prodottiClone.toString());
%><form action="EliminaProdotti" method="post"><%
for(int i = 0; i < prodottiClone.size(); i++){
	int count = 1;
	Products precedente = prodottiClone.get(0);
	prodottiClone.remove(0);
	for(int j = 0; j < prodottiClone.size(); j++){
		if(prodottiClone.get(j).equals(precedente)){
			count++;
			prodottiClone.remove(j);
			
			if(prodottiClone.size()!=0){
				j--;
			}else{
				j=prodottiClone.size();
			}
		}
	}
%>


	<label for="<%="p"+precedente.getId()%>"> ID: <%=precedente.getId() %> | Nome: <%=precedente.getName() %> | Descrizione: <%=precedente.getDescription() %> | Prezzo: <%=precedente.getPrice() %>$ | Quantita: <%=precedente.getQuantity() %> </label>
	<select name="<%="p"+precedente.getId()%>" >
		<%for(int x = 0; x <= 10; x++){ %>
		<option value="<%=x %>" <%if(x == count){ %>selected="selected"<%} %>> <%=x %></option>
		<%} 
		if(prodottiClone.size() == 0){
			i = prodottiClone.size();
		}	
		%>
		</select><br>
<%
}
%>
<input type="submit" value="Aggiorna">
</form><br>
Totatle prodotti nel carrello: <%=(int)carrello.getQuantita() %><br>
Spesa totale: <%=carrello.getTotale() %><br><br>
<%
}else{  
	String authError = "Effettua prima il log in o registrati:";%>

<span style="color: red"><%=authError%></span>
<br>
<br>
<a href="login.jsp"> Log in </a><br>
<a href="Registrazione.jsp"> Registrati </a><br><br>


<%}} %>

<a href="Prodotti.jsp"> Prodotti </a><br>
<a href="Home.jsp"> Home </a><br>
</body>
</html>