<%@page import="javax.persistence.Query"%>
<%@page import="it.beije.bean.SingletonEntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.bean.OrderItems"%>
<%@page import="it.beije.bean.CarrelloNew"%>
<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.util.Collections"%>
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
CarrelloNew carrelloNew = (CarrelloNew) session.getAttribute("CarrelloNew");
if(carrelloNew != null){
	if(carrelloNew.getOrderItems().size()!=0){
		List<OrderItems> orderItems = carrelloNew.getOrderItems();
		EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();
		String jpqlSelect = "SELECT x FROM Products as x";
		Query query = entityManager.createQuery(jpqlSelect);

		List<Products> products = query.getResultList();
		
		
		%><form action="EliminaProdotti" method="post"><%
				
		for(OrderItems oi : orderItems){
			Products product = null;
			for(Products p : products){
				if(p.getId() == oi.getProductId()){
					product = p;
				}	 
			}
			if(product != null){
			%>
			<label for="<%="p"+oi.getProductId()%>"> ID: <%=product.getId() %> | Nome: <%=product.getName() %> | Descrizione: <%=product.getDescription() %> | Prezzo: <%=product.getPrice() %>$ | Quantita: <%=product.getQuantity() %> </label>
			<select name="<%="p"+oi.getProductId()%>" ></select>
				<%for(int x = 0; x <= 10; x++){ %>
				<option value="<%=x %>" <%if(x == oi.getQuantity()){ %>selected="selected"<%} %>> <%=x %></option>
				<%} 
			
			
			}}
%>


<% }} %>

<a href="Prodotti.jsp"> Prodotti </a><br>
<a href="Home.jsp"> Home </a><br>
</body>
</html>