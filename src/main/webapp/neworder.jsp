<%@page import="java.util.Map"%>
<%@page import="it.beije.herse.shop.beans.Cart"%>
<%@page import="java.util.List"%>
<%@page import="it.beije.herse.shop.manager.ProductManager"%>
<%@page import="it.beije.herse.shop.beans.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">

	<% List<Product> products = (List<Product>) session.getAttribute("products");
	session.removeAttribute("products");
	Cart cart = (Cart) session.getAttribute("cart");
	Map<Integer,Integer> quantities = null;
	if(cart!=null) quantities = cart.getQuantities();%>
    
    <h1>HERSE SHOP</h1>
    
    <%
    Boolean totalEqualsZero = (Boolean) session.getAttribute("totalEqualsZero");
    if(totalEqualsZero!=null && totalEqualsZero){%>
    <h2><font color="red">PLEASE ENTER A VALID NUMBER OF ITEMS</font></h2>
    <%session.removeAttribute("totalEqualsZero");
    } %>
        
    <form action="CreateOrderServlet" method="post">
        <h3>ADD PRODUCTS TO YOUR ORDER:</h3>
        <table>
            <tr>
                <th>ADD</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>DETAILS</th>
            </tr>
            <%
            for(Product p : products){
            %>
            <tr>
                <td><input type="checkbox" name="check<%= p.getId()%>" <%if(quantities!=null && quantities.get(p.getId())!=null){ %>checked<%} %> ></td>
                <td>"<%= p.getName()%>"</td>
                <td><input type="text" name="price<%= p.getId()%>" style="width: 5em; background-color: black; color: white; border: none" 
                	value="<%= p.getPrice() %> $" readonly></td>
                <td><input type="number" name="quantity<%= p.getId()%>" min="0" style="width: 4em; background-color: black; 
                	color: white; border: none" <%if(quantities!=null && quantities.get(p.getId())!=null){ 
                	%>value="<%= quantities.get(p.getId()) %>"<%} else{%> value="0" <%}%>></td>
                <td>
                <input type=submit name="prodDetails<%= p.getId()%>" value="<%= p.getName()%>">
                </td>
            </tr>
            <%
            }
            %>
        </table>
        <br>
        <input type=submit name="submitOrder" value="ADD TO CART">
        <input type="submit" name="backToMenu" value="BACK TO MENU">
    </form>
       
</body>
    
</html>