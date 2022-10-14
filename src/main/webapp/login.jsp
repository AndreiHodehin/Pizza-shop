<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.10.2022
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2 align="center">Log in</h2>

<form align="center" action="/perform_login" method="post">
    <c:if test="${not empty error}">${error}</c:if>
    <p>Username</p>
    <p><input type="text" name="username"></p>
    <p>Password</p>
    <p><input type="password" name="password"></p>
    <input id="remember_me" name="remember-me" type="checkbox">
    <label for="remember_me">Remember me</label>
    <p><input type="submit" value="Sign in"></p>
</form>
<hr/>
<br/>
<h2 align="center">If you dont registered</h2>
<p align="center"><a href="/register.jsp">register</a></p>
</body>
</html>
