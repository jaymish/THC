<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/11/22
  Time: 5:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>List Reservation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <h1>Reservation List</h1>
    <form name="activate" action="/reservation/add" method="get">
        <input type="hidden" name="location" id="location" value="${locationid}">
        <button id="Add" type="submit" class="btn btn-primary" style="width: 20%" >Add Reservation</button>
    </form>
    <%--    <input type="search" id="form1" class="form-control" />--%>

    <%--    <input type="search" id="search" onkeyup="myFunction()" placeholder="Search for Reservation" title="Type in a name" style="width:100%">--%>

    <div class="form-outline mb-4">
        <input type="search" class="form-control" id="search" onkeyup="myFunction()" placeholder="Search for Reservation Item" title="Type in a name" style="width:100%">
    </div>
    <div id="datatable">
    </div>

    <table style="width:100%" class="table table-bordered" id="ReservationTable">
        <thead>
        <tr>
            <th scope="col" style="width: 5%" >#</th>
            <th scope="col" style="width: 20%" >Name</th>
            <th scope="col" style="width: 10%">Phone Number</th>
            <th scope="col" style="width: 15%">Email Id</th>
            <th scope="col" style="width: 9%">No of People</th>
            <th scope="col" style="width: 10%">Date</th>
            <th scope="col" style="width: 7%">Time</th>
            <th scope="col" style="width: 12%">Update</th>
            <th scope="col" style="width: 12%">Activate/Deactivate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="var" items="${LocationReservation}" varStatus="counter">

            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${var.firstName}"/> <c:out value="${var.lastName}"/></td>
                <td><c:out value="${var.phoneNumber}"/></td>
                <td><c:out value="${var.emailId}"/></td>
                <td><c:out value="${var.noOfPeople}"/></td>
                <td><c:out value="${var.date}"/></td>
                <td><fmt:formatNumber type = "number" maxIntegerDigits = "2" minIntegerDigits="2" value = "${var.time.hours}" />:<fmt:formatNumber type = "number" maxIntegerDigits = "2" minIntegerDigits="2" value = "${var.time.minutes}" /> <c:out value="${fn:toUpperCase(var.time.amPm)}"/></td>
                <td>
                    <form name="edit" action="/reservation/edit" method="post">
                        <input type="hidden" name="id" id="id" value="${var.id}">
                            <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                        <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Edit</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${var.status=='Active'}">

                            <form name="delete" action="/reservation/deactivate-by-id" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                    <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                                <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 100%">DeActivate</button>
                            </form>

                        </c:when>
                        <c:otherwise>

                            <form name="activate" action="/reservation/activate-by-id" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                    <%--                        <input id="activate" type="submit" name="activate" value="Activate" />--%>
                                <button id="activate" name="activate" type="submit" class="btn btn-success" style="width: 100%" >Activate</button>
                            </form>

                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!--% for%-->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:choose><c:when test="${currentpage=='0'}">disabled</c:when></c:choose>">
                <a class="page-link" href="/reservation/get-all?locationid=${locationid}&pageNo=${currentpage-1}&pageSize=5" >Previous</a>
            </li>
            <c:forEach begin="0" end="${pages}" varStatus="counter">
                <li class="page-item <c:choose><c:when test="${currentpage==counter.index}">active</c:when></c:choose>"><a class="page-link" href="/reservation/get-all?locationid=${locationid}&pageNo=${counter.index}&pageSize=5" >${counter.count}<br></a></li>
            </c:forEach>
            <li class="page-item <c:choose><c:when test="${currentpage==pages}">disabled </c:when></c:choose>">
                <a class="page-link" href="/reservation/get-all?locationid=${locationid}&pageNo=${currentpage+1}&pageSize=5" >Next</a>
            </li>
        </ul>
    </nav>
</div>
<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("search");
        filter = input.value.toUpperCase();
        table = document.getElementById("ReservationTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>
</html>
