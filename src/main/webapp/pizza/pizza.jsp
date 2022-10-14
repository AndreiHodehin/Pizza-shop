<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <th>Amount</th>
            <th>Unit</th>
            <th>Cooking time</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allPizza}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td><c:forEach items="${p.productList}" var="pr">
                    <p>${pr.prodName}</p>
                </c:forEach></td>
                <td><c:forEach items="${p.productList}" var="pr">
                    <p>${pr.amount}</p>
                </c:forEach>
                </td>
                <td><c:forEach items="${p.productList}" var="pr">
                    <p>${pr.unit}</p>
                </c:forEach>
                </td>
                <td>${p.cookingTimeInMin} min</td>

                <td><a href="/pizza/remove/${p.id}">Remove</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<hr/>
<br>



<h2 align="center">Create new pizza.</h2>
    <h4 align="center">Fill amount fields according to the selected product</h4>
<form align="center" action="/pizza/createPizza" method="post">
    <p>Title:<input type="text" name="name"></p>
    <p>Cooking time in min <input type="number" min="0" name="cookingTimeInMin"></p>
    Products:
    <p><c:forEach items="${allProduct}" var="product" varStatus="status">
        <tr>
    <td><input type="checkbox" name="prod" value="${product.nameOfProd}"/>${product.nameOfProd}. </td>
    <td>Amount: <input type="number" step="0.01" min="0" name="amount" id="amount"> </td>
            <c:if test="${status.count %3 == 0}">
                <br>
            </c:if>
        </tr>
    </c:forEach>
    </p>
    <p><input type="submit" value="Submit"></p>
</form>


<h2 align="center">Change any pizza by id.</h2>
<h4 align="center">Name of pizza will be saved.</h4>
    <h4 align="center">Fill amount fields according to the selected product</h4>
<form align="center" action="/pizza/update" method="post"  border="1">

    <label>Id:
        <input type="number" name="id">
    </label>
    <label>Cooking time:
    <input type="number" min="1" name="cookingTimeInMin">
        <br>
    </label>
    <c:forEach items="${allProduct}" var="product" varStatus="status">
        <input type="checkbox" name="prod" value="${product.nameOfProd}"/>${product.nameOfProd}
        Amount: <input type="number" step="0.01" min="0" name="amount">
        <c:if test="${status.count %3 == 0}">
            <br>
        </c:if>
    </c:forEach>
    <p><input type="submit" value="Update"></p>
</form>
<hr/>
<br/>


<p align="center"><a href="/main.jsp">Back</a></p>
<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
