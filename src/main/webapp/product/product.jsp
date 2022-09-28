<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.09.2022
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>

<h1 align="center">Admin info about Product</h1>

<c:if test="${not empty exempl}">
    <hr/>
    <br>
    <h2 align="center">Searched product is :</h2>
    <p align="center">${exempl}</p>
</c:if>

<c:if test="${not empty allProduct}">
    <hr/>
    <br>
    <h2 align="center">List of all product in database</h2>

    <table align="center" border="1">
        <thead>
        <tr>
            <th>№</th>
            <th>Name</th>
            <th>MinAmount</th>
            <th>Unit</th>
            <th>TotalStock</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allProduct}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.prodName}</td>
                <td>${p.amountInUnit}</td>
                <td>${p.unit}</td>
                <td>${p.totalStock}</td>
                <td><a href="/product/remove/${p.id}">Remove</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<hr/>
<br>
<h2 align="center">Create new product</h2>
<form align="center" action="/product/create" method="post" modelAttribute="product">
    <p>Product name:<input type="text" name="prodName"> </p>
    <p>amountInUnit :<input type="number" name="amountInUnit"> </p>
    <p>Unit name:<input type="text" name="unit"> </p>
    <p>Total stock:<input type="number" name="totalStock"> </p>
    <p><input type="submit" value="Submit"></p>
</form>

<hr/>
<br>
<h2 align="center">Update product</h2>
<form align="center" action="/product/update" method="post" modelAttribute="product">
    <p>Product ID: <input type="number" name="id"> </p>
    <p>Product name: <input type="text" name="prodName"> </p>
    <p>amountInUnit : <input type="number" name="amountInUnit"> </p>
    <p>Unit name: <input type="text" name="unit"> </p>
    <p>Total stock: <input type="number" name="totalStock"> </p>
    <p><input type="submit" value="Submit"></p>
</form>
</body>
</html>
