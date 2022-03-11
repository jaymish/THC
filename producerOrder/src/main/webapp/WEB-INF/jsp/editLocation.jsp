<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/9/22
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Edit Location</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
<form action="/location/updateLocation" method="post">
    <h1>Edit Location</h1>
<%--<c: var="var" items="${Locations}" varStatus="counter">--%>
    <input type="hidden" name="id" id="id" value="${Locations.id}">
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name" value="${Locations.name}" required>
    </div>
    <div class="mb-3">
        <label for="addressline1" class="form-label">Address Line 1</label>
        <input type="text" class="form-control" id="addressline1" name="addressline1" value="${Locations.addressline1}" required>
    </div>
    <div class="mb-3">
        <label for="addressline2" class="form-label">Address Line 2</label>
        <input type="text" class="form-control" id="addressline2" name="addressline2" value="${Locations.addressline2}">
    </div>
    <div class="mb-3">
        <label for="city" class="form-label">City</label>
        <input type="text" class="form-control" id="city" name="city" value="${Locations.city}" required>
    </div>
    <div class="mb-3">
        <label for="state" class="form-label">State</label>
        <input type="text" class="form-control" id="state" name="state" value="${Locations.state}" required>
    </div>
    <div class="mb-3">
        <label for="zip" class="form-label">Zip</label>
        <input type="text" class="form-control" id="zip" name="zip" value="${Locations.zip}" required>
    </div>
    <input type="hidden" name="status" id="status" value="${Locations.status}">
    <button type="submit" class="btn btn-primary">Update</button>
<%--</c:>>--%>
</form>
</div>
</body>
</html>
