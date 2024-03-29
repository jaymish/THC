<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/10/22
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Add Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <form action="/menu/add" method="post">
        <h1>Add Menu</h1>
        <div class="mb-3">
            <label for="itemName" class="form-label">Name</label>
            <input type="text" class="form-control" id="itemName" name="itemName" >
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">description</label>
            <input type="text" class="form-control" id="description" name="description" >
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">price2</label>
            <input type="text" class="form-control" id="price" name="price" >
        </div>

        <input type="hidden" name="location" id="location" value="${LocationId}">
        <%--<div class="mb-3">
            <label for="location" class="form-label">Select Location</label>
        <select class="form-control" aria-label=".form-select-lg example" id="location" name="location">
&lt;%&ndash;            <option selected>Open this select menu</option>&ndash;%&gt;
            <c:forEach var="var" items="${Locations}" varStatus="counter">
            <option value="${var.id}">${var.name}</option>
            </c:forEach>
        </select>
        </div>--%>
        <button type="submit" class="btn btn-primary" style="width: 15%">Add</button>
    </form>
</div>
</body>
</html>
