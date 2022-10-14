<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.09.2022
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stock</title>
</head>
<body>
<h1 align="center">Stock of product</h1>
<hr/>
<br>

<h2 align="center">List of all products in stock</h2>
<table align="center" border="1">
    <thead>
    <tr>
        <td>№</td>
        <td>Name</td>
        <td>Total in stock</td>
        <td>Unit of product</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allStock}" var="prod">
        <tr>
            <td>${prod.id}</td>
            <td>${prod.nameOfProd}</td>
            <td>${prod.totalStock}</td>
            <td>${prod.unit}</td>
            <td><a href="/stock/remove/${prod.id}">Remove</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr/>
<br>

<h2 align="center">Add product in stock</h2>
<form align="center"  action="/stock/create" method="post" modelAttribute="product">
    <p>Product username: <input type="text" name="nameOfProd"></p>
    <p>Total amount: <input type="number" min="0" step="any" ame="totalStock"></p>
    <p>Unit name: <input type="text" name="unit"></p>
    <p><input type="submit" value="Add"></p>
</form>
<hr/>
<br>

<h2 align="center">Update product in stock</h2>
<form align="center" action="/stock/update" method="post" modelAttribute="product">
    <p>Product id: <input type="number" name="id"></p>
    <p>Name of product: <input type="text" name="nameOfProd"></p>
    <p>Amount: <input type="number"  step="0.01" name="totalStock"></p>
    <p>Unit of product: <input type="text" name="unit"></p>
    <p><input type="submit" value="Update"></p>
</form>
<hr/>
<br>

<p align="center"><a href="/main.jsp">Back</a></p>
<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
