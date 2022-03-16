<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/15/22
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>API Execution Time List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <%@include file="../Loginheader.html" %>
    <h1>Location List</h1>

    <c:choose><c:when test="${message!=''}">
        <div class="alert alert-danger hide-child" role="alert">
                ${message}
        </div>
    </c:when></c:choose>

    <%--<form name="activate" action="/api/add" method="get">
        <button id="Add" type="submit" class="btn btn-primary" style="width: 20%" >Add Location</button>
    </form>--%>

    <%--    <input type="search" id="form1" class="form-control" />--%>

    <%--    <input type="search" id="search" onkeyup="myFunction()" placeholder="Search for Location" title="Type in a name" style="width:100%">--%>

    <div class="form-outline mb-4">
        <input type="search" class="form-control" id="search" onkeyup="myFunction()" placeholder="Search for Location" title="Type in a name" style="width:100%">
    </div>
    <div id="datatable">
    </div>

    <table style="width:100%" class="table table-bordered" id="LocationTable">
        <thead>
        <tr>
            <th scope="col" style="width: 2%" >#</th>
            <th scope="col" style="width: 15%" >Name</th>
            <th scope="col" style="width: 3%">executionDate</th>
            <th scope="col" style="width: 5%">executionTime</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="var" items="${apis}" varStatus="counter">

            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${var.apiName.name}"/></td>
                <td><c:out value="${var.executionDate}"/> </td>
                <td><c:out value="${var.executionTime}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%--        <ul class="pagination justify-content-end">--%>
<%--            <li class="page-item <c:choose><c:when test="${showbyname=='all'}">active</c:when></c:choose>"><a class="page-link" href="/api/get-all?pageNo=0&pageSize=5&showbyname=all">Show All</a></li>--%>
<%--            <li class="page-item <c:choose><c:when test="${showbyname=='getAllLocation'}">active</c:when></c:choose>"><a class="page-link" href="/api/get-all?pageNo=0&pageSize=5&showbyname=getAllLocation">Show Active</a></li>--%>
<%--        </ul>--%>
    <form action="/api/get-all" method="get" >
        <input type="hidden" name="pageNo" id="pageNo" value="0">
        <input type="hidden" name="pageSize" id="pageSize" value="20">
        <div class="row justify-content-end">
            <div class="col-md-2">
                <label for="showbyname" class="form-label">Filter by Name</label>
            </div>
            <div class="col-md-2">
                <label for="showbydate" class="form-label">Filter by Date</label>
            </div>
        </div>
        <div class="row justify-content-end">
        <div class="col-md-2">
        <select class="form-select" aria-label="Default select example" onchange="this.form.submit()" id="showbyname" name="showbyname">
            <option value="all" <c:choose><c:when test="${showbyname=='all'}">selected</c:when></c:choose>>Show All</option>
            <c:forEach var="apiname" items="${apiNameList}" varStatus="counter">
            <option value="${apiname.name}" <c:choose><c:when test="${showbyname==apiname.name}">selected</c:when></c:choose>>${apiname.name}</option>
            </c:forEach>
        </select>
        </div>
        <div class="col-md-2">
        <input type="date" id="showbydate" name="showbydate" value="${showbydate}" onchange="this.form.submit()" class="form-control">
        </div>
        </div>
    </form>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:choose><c:when test="${currentpage=='0'}">disabled</c:when></c:choose>">
                <a class="page-link" href="/api/get-all?pageNo=${currentpage-1}&pageSize=5&showbyname=${showbyname}" >Previous</a>
            </li>
            <c:forEach begin="0" end="${pages}" varStatus="counter">
                <li class="page-item <c:choose><c:when test="${currentpage==counter.index}">active</c:when></c:choose>"><a class="page-link" href="/api/get-all?pageNo=${counter.index}&pageSize=5&showbyname=${showbyname}" >${counter.count}<br></a></li>
            </c:forEach>
            <li class="page-item <c:choose><c:when test="${currentpage==pages}">disabled </c:when></c:choose>">
                <a class="page-link" href="/api/get-all?pageNo=${currentpage+1}&pageSize=5&showbyname=${showbyname}" >Next</a>
            </li>
        </ul>
    </nav>
    <!--% for%-->
</div>
</body>
<script>
    var datemin = new Date();
    var mindd = datemin.getDate()-7;
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
    document.getElementById("showbydate").setAttribute("min", datemin);
    document.getElementById("showbydate").setAttribute("max", datemax);
</script>
</html>
