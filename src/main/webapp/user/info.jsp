<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 03.10.2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info</title>
</head>
<body>

<h1 align="center">Actual information</h1>
<table align="center" border="1">
    <thead>
    <tr>
        <td>Email</td>
        <td>Username</td>
        <td>firstName</td>
        <td>lastName</td>
        <td>Address</td>
        <td>Phone number</td>
    </tr>
    </thead>
    <tbody>
    <tr>
            <td>${userInfo.email}</td>
            <td>${userInfo.username}</td>
            <td>${userInfo.firstName}</td>
            <td>${userInfo.lastName}</td>
            <td>${userInfo.address}</td>
            <td>${userInfo.phoneNumber}</td>
    </tr>
    </tbody>
</table>
<h1 align="center">Enter information your credential</h1>
<form align="center" action="/user/info" method="post" modelAttribute="user">
    <label for="email">Email: </label><input type="text" name="email" id="email" >
    <label for="firstName">First name: </label><input type="text" name="firstName" id="firstName" >
    <label for="lastName">Last name: </label><input type="text" name="lastName" id="lastName" >
    <label for="address">Address : </label><input type="text" name="address" id="address" >
    <label for="number">Phone number: </label><input type="text" name="phoneNumber" id="number" >
    <input type="submit" value="Fill">
</form>
<hr/>
<br/>
<p align="center"><a href="/main.jsp">Back</a></p>
<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
