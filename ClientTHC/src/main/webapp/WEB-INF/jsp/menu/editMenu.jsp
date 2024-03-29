<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/10/22
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <form action="/menu/update" method="post">
        <h1>Edit Menu</h1>
        <%--<c: var="var" items="${Menus}" varStatus="counter">--%>
        <input type="hidden" name="id" id="id" value="${Menus.id}">
        <div class="mb-3">
            <label for="itemName" class="form-label">itemName</label>
            <input type="text" class="form-control" id="itemName" name="itemName" value="${Menus.itemName}" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">description</label>
            <input type="text" class="form-control" id="description" name="description" value="${Menus.description}" required>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">price</label>
            <input type="text" class="form-control" id="price" name="price" value="${Menus.price}">
        </div>

        <input type="hidden" name="status" id="status" value="${Menus.status}">
        <input type="hidden" name="location" id="location" value="${Menus.location.id}">
        <button type="submit" class="btn btn-primary">Update</button>
        <%--</c:>>--%>
    </form>
</div>
</body>
</html>
