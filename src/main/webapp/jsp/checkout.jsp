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
	
	ORDER ADDED: <br>
	
	<%
	Double p1 = 0.0;
	Integer q1 = 0;
	
	Double p2 = 0.0;
	Integer q2 = 0;
	
	Double total = 0.0;
	
	String submit = (String) request.getParameter("submitOrder");
	if(submit!=null && submit.equals("SUBMIT")) {
		
		
		String checkboxProd1 = (String) request.getParameter("prod1");
		if(checkboxProd1!=null && checkboxProd1.equals("on")) {
			String valuePrice1 = (String) request.getParameter("price1");
			p1 = Double.valueOf(valuePrice1.substring(0, valuePrice1.length()-1)); 
			
			String valueQuantity1 = (String) request.getParameter("quantity1");
			q1 = Integer.valueOf(valueQuantity1); 	
		}
		
		
		String checkboxProd2 = (String) request.getParameter("prod2");
		if(checkboxProd2!=null && checkboxProd2.equals("on")) {
			String valuePrice2 = (String) request.getParameter("price2");
			p2 = Double.valueOf(valuePrice2.substring(0, valuePrice2.length()-1));
			
			String valueQuantity2 = (String) request.getParameter("quantity2");
			q2 = Integer.valueOf(valueQuantity2); 	
		}
		
	}
	%>
	
	<table>
		<tr><td>"Dune"</td><td>QUANTITY: <%= q1 %></td><td>PRICE: <%= q1*p1 %>$</td></tr>
		<tr><td>"Lenovo"</td><td>QUANTITY: <%= q2 %></td><td>PRICE: <%= q2*p2 %>$</td></tr>
	</table>
	<br>
	TOTAL: <%= total=p1*q1 + p2*q2 %>$
</body>
</html>