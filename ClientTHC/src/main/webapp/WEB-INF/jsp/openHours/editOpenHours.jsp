<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/11/22
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Edit Hours</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <form action="/open-hours/update" method="post">
        <h1>Edit Hours</h1>
        <%--<c: var="var" items="${OpenHours}" varStatus="counter">--%>
        <input type="hidden" name="id" id="id" value="${OpenHours.id}">
        <div class="mb-3">
            <label for="day" class="form-label">Day</label>
            <select class="form-control" aria-label=".form-select-lg example" id="day" name="day">

                <option value="Sunday" <c:choose><c:when test="${OpenHours.day=='Sunday'}">selected </c:when></c:choose>>Sunday</option>
                <option value="Monday" <c:choose><c:when test="${OpenHours.day=='Monday'}">selected </c:when></c:choose>>Monday</option>
                <option value="Tuesday" <c:choose><c:when test="${OpenHours.day=='Tuesday'}">selected </c:when></c:choose>>Tuesday</option>
                <option value="Wednesday" <c:choose><c:when test="${OpenHours.day=='Wednesday'}">selected </c:when></c:choose>>Wednesday</option>
                <option value="Thursday" <c:choose><c:when test="${OpenHours.day=='Thursday'}">selected </c:when></c:choose>>Thursday</option>
                <option value="Friday" <c:choose><c:when test="${OpenHours.day=='Friday'}">selected </c:when></c:choose>>Friday</option>
                <option value="Saturday" <c:choose><c:when test="${OpenHours.day=='Saturday'}">selected </c:when></c:choose>>Saturday</option>
            </select>
        </div>
        <c:set var="startminutes" value="${OpenHours.startTime.minutes}"/>
        <c:set var="starthours" value="${OpenHours.startTime.hours}"/>
        <c:set var="endminutes" value="${OpenHours.endTime.minutes}"/>
        <c:set var="endhours" value="${OpenHours.endTime.hours}"/>

        <div class="md-3">
            <label for="startTime" class="form-label">Start Time</label>
            <c:choose>
                <c:when test="${OpenHours.startTime.amPm=='PM'}">

                    <c:choose>
                        <c:when test="${OpenHours.startTime.hours=='12'}">
                            <input type="time" class="form-control" id="startTime" name="startTime" value="<% out.print("12:"+String.format("%02d",pageContext.getAttribute("startminutes"))); %>">
                        </c:when>
                        <c:otherwise>
                            <input type="time" class="form-control" id="startTime" name="startTime" value="<% out.print(String.format("%02d",(Integer)pageContext.getAttribute("starthours")+12)+":"+String.format("%02d",pageContext.getAttribute("startminutes"))); %>">
                        </c:otherwise>
                    </c:choose>

                </c:when>
                <c:otherwise>

                    <c:choose>
                        <c:when test="${OpenHours.startTime.hours=='12'}">
                            <input type="time" class="form-control" id="startTime" name="startTime" value="<% out.print("00:"+String.format("%02d",pageContext.getAttribute("startminutes"))); %>">
                        </c:when>
                        <c:otherwise>
                            <input type="time" class="form-control" id="startTime" name="startTime" value="<% out.print(String.format("%02d",pageContext.getAttribute("starthours"))+":"+String.format("%02d",pageContext.getAttribute("startminutes"))); %>">
                        </c:otherwise>
                    </c:choose>


                </c:otherwise>

            </c:choose>
        </div>
        <br>
        <div class="md-3">
        <label for="endTime" class="form-label">End Time</label>
        <c:choose>
            <c:when test="${OpenHours.endTime.amPm=='PM'}">

                <c:choose>
                    <c:when test="${OpenHours.endTime.hours=='12'}">
                        <input type="time" class="form-control" id="endTime" name="endTime" value="<% out.print("12:"+String.format("%02d",pageContext.getAttribute("endminutes"))); %>">
                    </c:when>
                    <c:otherwise>
                        <input type="time" class="form-control" id="endTime" name="endTime" value="<% out.print(String.format("%02d",(Integer)pageContext.getAttribute("endhours")+12)+":"+String.format("%02d",pageContext.getAttribute("endminutes"))); %>">
                    </c:otherwise>
                </c:choose>


            </c:when>
            <c:otherwise>

                <c:choose>
                    <c:when test="${OpenHours.endTime.hours=='12'}">
                        <input type="time" class="form-control" id="endTime" name="endTime" value="<% out.print("00:"+String.format("%02d",pageContext.getAttribute("endminutes"))); %>">
                    </c:when>
                    <c:otherwise>
                        <input type="time" class="form-control" id="endTime" name="endTime" value="<% out.print(String.format("%02d",pageContext.getAttribute("endhours"))+":"+String.format("%02d",pageContext.getAttribute("endminutes"))); %>">
                    </c:otherwise>
                </c:choose>


            </c:otherwise>

        </c:choose>
        </div>
        <input type="hidden" name="location" id="location" value="${OpenHours.location.id}">
        <br>
        <button type="submit" class="btn btn-primary">Update</button>
        <%--</c:>>--%>
    </form>
</div>
</body>
</html>
