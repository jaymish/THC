<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/11/22
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Add Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <form action="/reservation/add" method="post">
        <h1>Add Reservation</h1>
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"  required>
        </div>
        <div class="mb-3">
            <label for="emailId" class="form-label">Email Id</label>
            <input type="text" class="form-control" id="emailId" name="emailId" required>
        </div>
        <div class="mb-3">
            <label for="noOfPeople" class="form-label">No of People</label>
            <input type="text" class="form-control" id="noOfPeople" name="noOfPeople"  required>
        </div>

        <div class="mb-3">
            <label for="date" class="form-label">Date</label>
            <input type="date" class="form-control" id="date" name="date" required>
        </div>

        <div class="md-3">
            <label for="time" class="form-label">Time</label>
            <input type="time" class="form-control" id="time" name="time" >

        </div>
        <br>
        <input type="hidden" name="location" id="location" value="${LocationId}">
        <button type="submit" class="btn btn-primary" style="width: 15%">Add</button>
    </form>
</div>
</body>
<script>
    var datemin = new Date();
    var mindd = datemin.getDate()+1;
    var minmm = datemin.getMonth()+1; //January is 0!
    var minyyyy = datemin.getFullYear();

    var datemax = new Date();
    var maxdd = datemax.getDate()+8;
    var maxmm = datemax.getMonth()+1; //January is 0!
    var maxyyyy = datemax.getFullYear();
    if(mindd<10){
        mindd='0'+mindd
    }
    if(minmm<10){
        minmm='0'+minmm
    }

    if(maxdd<10){
        maxdd='0'+maxdd
    }
    if(maxmm<10){
        maxmm='0'+maxmm
    }

    datemin = minyyyy+'-'+minmm+'-'+mindd;
    datemax = maxyyyy+'-'+maxmm+'-'+maxdd;
    document.getElementById("date").setAttribute("min", datemin);
    document.getElementById("date").setAttribute("max", datemax);
</script>
</html>
