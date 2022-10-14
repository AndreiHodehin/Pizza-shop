<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.10.2022
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<hr/>
<br>
<h2 align="center">List of all pizzaList in database</h2>

<c:if test="${not empty allPizza}">
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
                ${pr.prodName} ;
            </c:forEach></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<hr/>
<br/>

<form align="center" action="/user/order" method="post" border="1">

    <c:forEach items="${allPizza}" var="pizza" varStatus="status">
        <input type="checkbox" name="pizzaName" value="${pizza.name}">${pizza.name}
        <c:if test="${status.count%2 == 0}">
            <br>
        </c:if>
    </c:forEach>
    <p><input type="submit" value="order"></p>
</form>
<hr/>
<br/>

<c:if test="${not empty orderInProcess}">
<h2 align="center">Order in process </h2>

        <table align="center" border="1">
            <thead>
            <tr>
                <td>#</td>
                <td>Order time</td>
                <td>User</td>
                <td>Pizza</td>
                <td>Status</td>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${orderInProcess}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.createdTime}</td>
                    <td>${order.user.username}</td>
                    <td><c:forEach items="${order.orderedPizza}" var="pizza">
                        <p>${pizza.name}</p>
                    </c:forEach> </td>
                    <td>It will be ready for a while</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    <hr/>
    <br/>
    </c:if>

<c:if test="${not empty orderUsers}">
<h2 align="center">Your orders</h2>

    <table align="center" border="1">
        <thead>
        <tr>
            <td>#</td>
            <td>Order time</td>
            <td>User</td>
            <td>Pizza</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>

            <c:forEach items="${orderUsers}" var="order">
        <tr>
                <td>${order.id}</td>
                <td>${order.createdTime}</td>
                <td>${order.user.username}</td>
                <td><c:forEach items="${order.orderedPizza}" var="pizza">
                    <p>${pizza.name}</p>
                </c:forEach> </td>
                <td><c:if test="${order.prepared}">Take it</c:if>
                    <c:if test="${!order.prepared}">Cooking</c:if></td>
        </tr>
            </c:forEach>

        </tbody>
    </table>

</c:if>
<p align="center"><a href="/main.jsp">Back</a></p>
<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
