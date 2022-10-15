<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.10.2022
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register form</title>
</head>
<body>

<form align="center" action="/user/register" method="post" modelAttribute="user">
    <p><label for="username">Username: </label><input type="text" name="username" id="username"></p>
    <p><label for="password">Password: </label><input type="password" name="password" id="password"></p>
    <p><label for="email">Email: </label><input type="text" name="email" id="email"></p>
    <p><label for="first_name">First name: </label><input type="text" name="firstName" id="first_name"></p>
    <p><label for="last_name">Last name: </label><input type="text" name="lastName" id="last_name"></p>
    <p><label for="address">Address: </label><input type="text" name="address" id="address"></p>
    <p><label for="phone">Phone number: </label><input type="text" name="phoneNumber" id="phone"></p>
    <p><input type="submit" value="register"></p>
</form>
<hr/>
<br>

<p align="center"><a href="/main.jsp">Back</a></p>
</body>
</html>
