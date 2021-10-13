<%@page import="java.util.List"%>
<%@page import="it.beije.herse.shop.manager.ProductManager"%>
<%@page import="it.beije.herse.shop.classes.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">

	<% List<Product> products = ProductManager.selectProducts();%>
    
    <h1>HERSE SHOP</h1>
        
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
                <td><input type="checkbox" name="check<%= p.getId()%>"></td>
                <td>"<%= p.getName()%>"</td>
                <td><input type="text" name="price<%= p.getId()%>" style="width: 5em; background-color: black; color: white; border: none" 
                	value="<%= p.getPrice() %> $" readonly></td>
                <td><input type="number" name="quantity<%= p.getId()%>" min="0" style="width: 4em; background-color: black; 
                	color: white; border: none" value="0"></td>
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
    </form>
       
</body>
    
</html>