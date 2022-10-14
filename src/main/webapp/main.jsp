<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02.10.2022
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<sec:authorize access="hasRole('ROLE_USER')">
    <h3 align="center"> Welcome, <sec:authentication property="principal.username"/></h3>

    <p align="center">You can choose pizza from menu</p>
    <p align="center"><a href="/user/">Menu</a></p>
    <hr/>
    <br/>

<%--    <p align="center">Or you can create your own pizza</p>--%>
<%--    <p align="center"><a href="/user/pizzaCreator">Create</a> </p>--%>
<%--    <hr/>--%>
<%--    <br/>--%>

    <p align="center">Fill in information about yourself</p>
    <p align="center"><a href="/user/infoUser">Fill info</a></p>
    <hr/>
    <br/>

</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<hr/>
    <br/>
    <h3 align="center">Hello administrator,  <sec:authentication property="principal.username"/></h3>
      <hr/>
    <br/>

    <p align="center"> Create or another operation with pizza</p>
    <p align="center"><a href="pizza/">Pizza menu</a> </p>
    <hr/>
    <br/>

    <p align="center">Create or another operation with stock of product</p>
    <p align="center"><a href="stock/">Stock menu</a> </p>
    <hr/>
    <br/>

    <p align="center">List of all User </p>
    <p align="center"><a href="user/users">All user</a></p>
    <hr/>
    <br/>

    <p align="center">List of each product in each pizza </p>
    <p align="center"><a href="product/">Products</a></p>
    <hr/>
    <br/>

</sec:authorize>

<p align="right"><a align="right" href="/logout">Logout</a></p>
</body>
</html>
