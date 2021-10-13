<%@page import="it.beije.bean.Products"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="it.beije.bean.SingletonEntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
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
EntityManager entityManager = SingletonEntityManagerFactory.newEntityManager();

String jpqlSelect = "SELECT x FROM Products as x";
Query query = entityManager.createQuery(jpqlSelect);

List<Products> result = query.getResultList();
for (Products p : result) {
	%>
	<form action="Shop" method="post">
	<input type="number" style="width: 40px" min="1" max="10" name="<%=p.getId()%>">
	<label for="<%=p.getId()%>"> ID: <%=p.getId() %> | Nome: <%=p.getName() %> | Descrizione: <%=p.getDescription() %> | Prezzo: <%=p.getPrice() %>$ | Quantita: <%=p.getQuantity() %> </label>	<br>
	<%
	}
%>
<input type="submit">
</form>

<br><br>
<a href="Home.jsp"> Home </a><br>
</body>
</html>