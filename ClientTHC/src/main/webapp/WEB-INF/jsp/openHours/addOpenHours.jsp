<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/11/22
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Add Hours</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <form action="/open-hours/add" method="post">
        <h1>Add Hours</h1>
        <div class="mb-3">
            <label for="day" class="form-label">Day</label>
            <select class="form-control" aria-label=".form-select-lg example" id="day" name="day">
                <option value="Sunday" >Sunday</option>
                <option value="Monday" >Monday</option>
                <option value="Tuesday">Tuesday</option>
                <option value="Wednesday">Wednesday</option>
                <option value="Thursday">Thursday</option>
                <option value="Friday">Friday</option>
                <option value="Saturday">Saturday</option>
            </select>
        </div>
        <div class="md-3">
            <label for="startTime" class="form-label">Start Time</label>
            <input type="time" class="form-control" id="startTime" name="startTime">
        </div>
        <br>
        <div class="md-3">
            <label for="endTime" class="form-label">End Time</label>
            <input type="time" class="form-control" id="endTime" name="endTime">
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
        <br>
        <button type="submit" class="btn btn-primary" style="width: 15%">Add</button>
    </form>
</div>
</body>
</html>
