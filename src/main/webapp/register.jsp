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
    <label for="username">Username: </label><input type="text" name="username" id="username">
    <label for="password">Password: </label><input type="password" name="password" id="password">
    <input type="submit" value="register">
</form>
<hr/>
<br>

<p align="center"><a href="/main.jsp">Back</a></p>
</body>
</html>
