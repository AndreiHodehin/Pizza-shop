<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.10.2022
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h1 align="center">List of all register user and admin</h1>
<table align="center" border="2">
    <thead>
    <tr>
        <td>Email</td>
        <td>Username</td>
        <td>First name</td>
        <td>Last name</td>
        <td>Address</td>
        <td>Phone number</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
    <tr>
        <td>${user.email}</td>
        <td>${user.username}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.address}</td>
        <td>${user.phoneNumber}</td>
        <td><a href="/user/remove/${user.id}">Remove</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<hr/>
<br/>
<p align="center"><a href="/main.jsp">Back</a></p>
<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
