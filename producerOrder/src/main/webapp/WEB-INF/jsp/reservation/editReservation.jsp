<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/11/22
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Edit Reservation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <form action="/reservation/updateReservation" method="post">
        <h1>Edit Location</h1>
        <%--<c: var="var" items="${Reservation}" varStatus="counter">--%>
        <input type="hidden" name="id" id="id" value="${Reservations.id}">


        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${Reservations.firstName}" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${Reservations.lastName}" required>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${Reservations.phoneNumber}" required>
        </div>
        <div class="mb-3">
            <label for="emailId" class="form-label">Email Id</label>
            <input type="text" class="form-control" id="emailId" name="emailId" value="${Reservations.emailId}" required>
        </div>
        <div class="mb-3">
            <label for="noOfPeople" class="form-label">No of People</label>
            <input type="text" class="form-control" id="noOfPeople" name="noOfPeople" value="${Reservations.noOfPeople}" required>
        </div>

        <div class="mb-3">
            <label for="date" class="form-label">Date</label>
            <input type="date" class="form-control" id="date" name="date" value="${Reservations.date}" required>
        </div>

        <c:set var="minutes" value="${Reservations.time.minutes}"/>
        <c:set var="hours" value="${Reservations.time.hours}"/>


        <div class="md-3">
            <label for="time" class="form-label">Time</label>

            <c:choose>
                <c:when test="${Reservations.time.amPm=='PM'}">
                    <c:choose>
                        <c:when test="${Reservations.time.hours=='12'}">
                            <input type="time" class="form-control" id="time" name="time" value="<% out.print("12:"+String.format("%02d",pageContext.getAttribute("minutes"))); %>">
                        </c:when>
                        <c:otherwise>
                            <input type="time" class="form-control" id="time" name="time" value="<% out.print(String.format("%02d",(Integer)pageContext.getAttribute("hours")+12)+":"+String.format("%02d",pageContext.getAttribute("minutes"))); %>">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${Reservations.time.hours=='12'}">
                            <input type="time" class="form-control" id="time" name="time" value="<% out.print("00:"+String.format("%02d",pageContext.getAttribute("minutes"))); %>">
                        </c:when>
                        <c:otherwise>
                            <input type="time" class="form-control" id="time" name="time" value="<% out.print(String.format("%02d",pageContext.getAttribute("hours"))+":"+String.format("%02d",pageContext.getAttribute("minutes"))); %>">
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>

            </c:choose>
        </div>
        <br>
        <input type="hidden" name="location" id="location" value="${Reservations.location.id}">
        <button type="submit" class="btn btn-primary">Update</button>
        <%--</c:>>--%>
    </form>
</div>
</body>
</html>
