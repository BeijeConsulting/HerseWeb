<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>HERSE SHOP</title>
</head>

    
<body bgcolor="black" text="white">
    
    <h1>HERSE SHOP</h1>
        
    <form action="checkout.jsp" method="post">
        <h3>ADD PRODUCTS TO YOUR ORDER:</h3>
        <table>
            <tr>
                <th></th>
                <th>NAME</th>
                <th>DESC</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
            </tr>
            <tr>
                <td><input type="checkbox" name="prod1" id="1"></td>
                <td>"Dune"</td>
                <td>BOOK</td>
                <td><input type="text" name="price1" style="width: 5em; background-color: black; color: white; border: none" value="9.99 $" readonly></td>
                <td><input type="number" name="quantity1" min="0" style="width: 4em; background-color: black; color: white; border: none" value="0"></td>
            </tr>
            <tr>
                <td><input type="checkbox" name="prod2" id="2"></td>
                <td>"Lenovo"</td>
                <td>LAPTOP</td>
                <td><input type="text" name="price2" style="width: 5em; background-color: black; color: white; border: none" value="699.99 $" readonly></td>
                <td><input type="number" name="quantity2" min="0" style="width: 4em; background-color: black; color: white; border: none" value="0"></td>
            </tr>
        </table>
        <br>
        <input type=submit name="submitOrder" value="SUBMIT">
    </form>
       
</body>
    
</html>