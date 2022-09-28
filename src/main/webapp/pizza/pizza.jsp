<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.09.2022
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pizza</title>
</head>
<body>
<h1 align="center">Admin info about Pizza</h1>

<c:if test="${not empty exempl}">
    <hr/>
    <br>
    <h2 align="center">Searched pizzaList is :</h2>
    <p align="center">${exempl}</p>
</c:if>

<c:if test="${not empty allPizza}">
    <hr/>
    <br>
    <h2 align="center">List of all pizzaList in database</h2>

    <table align="center" border="1">
        <thead>
        <tr>
            <th>№</th>
            <th>Name</th>
            <th>Products</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allPizza}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td><c:forEach items="${p.productList}" var="pr">
                    <p>${pr.prodName}</p>
                </c:forEach>
                </td>

                <td><a href="/pizza/remove/${p.id}">Remove</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


<h2 align="center">Create new pizzaList</h2>
<form align="center" action="/pizza/createPizza" method="post" modelAttribute="pizzaList">
    <p>Title:<input type="text" name="name"> </p>
    Products:
    <p><c:forEach items="${allProduct}" var="product">
    <input type="checkbox" name="prod" value="${product.prodName}"/>${product.prodName}
        <input type="number" name="">
    </c:forEach>
    </p>
    <p><input type="submit" value="Submit"></p>
</form>

<h2 align="center">Change any pizza by id</h2>
<form align="center" action="/pizza/update" method="post" modelAttribute="pizza" border="1">

    <label>Id:
        <input type="number" name="id">
    </label>
    <c:forEach items="${allProduct}" var="product">
    <input type="checkbox" name="prod" value="${product.prodName}"/>${product.prodName}
</c:forEach>
    <p><input type="submit" value="Update"></p>
</form>
</body>
</html>
