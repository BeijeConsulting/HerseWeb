<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo</title>
</head>
<body>

<%@page import="it.beije.herse.shop.*"%>
<%@page import="static it.beije.herse.shop.MyShop.*"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<jsp:useBean id="carrello" class="it.beije.herse.shop.Carrello" scope="session"></jsp:useBean>
<jsp:useBean id="user" class="it.beije.herse.shop.User" scope="session"></jsp:useBean>

<%
if(user.getId() == null)
	response.sendRedirect("index.html");		
if(carrello.getItems().isEmpty()){
List<Product> list = getProducts();
StringBuilder error = new StringBuilder();

for(Product p : list){

	String qta = request.getParameter("prodQta" + p.getId());
	String id = request.getParameter(p.getId().toString());
	
	if(id != null) {
		
		
		if(Integer.valueOf(qta) <= p.getQuantity()){
			
			OrderItem item = setOrderItem(Integer.valueOf(id), Integer.valueOf(qta), p.getPrice());
			carrello.addItem(item);
			
		}else{
			
			if(p.getQuantity() > 1)
				error.append("Sono disponibili " + p.getQuantity() + " di " + p.getName());
			else
				error.append("E' disponibile " + p.getQuantity() + " di " + p.getName());
			
		}
		
	}
	
}
if(!error.isEmpty()){
	session.setAttribute("errorQta", error);
	response.sendRedirect("viewproduct.jsp");
}

}


for(OrderItem item : carrello.getItems()){
	Product p = getProduct(item.getProductId());
	String htmlEl = "<html><body><input type=\"text\" value=\"" + p.getName() + "\" readonly>\n"
					+ "<input type=\"text\" value=\"" + p.getDescription() + "\" readonly>\n"
					+ "<input type=\"text\" value=\"" + item.getSellPrice() + "\" readonly>\n"
					+ "<input type=\"text\" value=\"" + item.getQuantity() + "\" readonly><br>\n";
	out.print(htmlEl);
}
%>

</body>
</html>