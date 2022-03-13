<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/10/22
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Menu List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <h1>Menu List</h1>
    <form name="activate" action="/menu/add" method="get">
        <input type="hidden" name="location" id="location" value="${locationid}">
        <button id="Add" type="submit" class="btn btn-primary" style="width: 20%" >Add Menu</button>
    </form>
    <%--    <input type="search" id="form1" class="form-control" />--%>

    <%--    <input type="search" id="search" onkeyup="myFunction()" placeholder="Search for Menu" title="Type in a name" style="width:100%">--%>

    <div class="form-outline mb-4">
        <input type="search" class="form-control" id="search" onkeyup="myFunction()" placeholder="Search for Menu Item" title="Type in a name" style="width:100%">
    </div>
    <div id="datatable">
    </div>

    <table style="width:100%" class="table table-bordered" id="MenuTable">
        <thead>
        <tr>
            <th scope="col" style="width: 5%" >#</th>
            <th scope="col" style="width: 17%" >Item Name</th>
            <th scope="col" style="width: 38%">Description</th>
            <th scope="col" style="width: 10%">Price</th>
            <th scope="col" style="width: 15%">Update</th>
            <th scope="col" style="width: 15%">Activate/Deactivate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="var" items="${LocationMenu}" varStatus="counter">

            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${var.itemName}"/></td>
                <td><c:out value="${var.description}"/></td>
                <td><c:out value="${var.price}"/></td>
                <td>
                    <form name="edit" action="/menu/edit" method="post">
                        <input type="hidden" name="id" id="id" value="${var.id}">
                            <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                        <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Edit</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${var.status=='Active'}">

                            <form name="delete" action="/menu/deactivate-by-id" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                <input type="hidden" name="show" id="show" value="${show}">
                                    <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                                <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 100%">DeActivate</button>
                            </form>

                        </c:when>
                        <c:otherwise>

                            <form name="activate" action="/menu/activate-by-id" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                <input type="hidden" name="show" id="show" value="${show}">
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
        <ul class="pagination justify-content-end">
            <li class="page-item <c:choose><c:when test="${show=='all'}">active</c:when></c:choose>"><a class="page-link" href="/menu/get-all?pageNo=0&pageSize=5&show=all&locationid=${locationid}">Show All</a></li>
            <li class="page-item <c:choose><c:when test="${show=='Active'}">active</c:when></c:choose>"><a class="page-link" href="/menu/get-all?pageNo=0&pageSize=5&show=Active&locationid=${locationid}">Show Active</a></li>
            <li class="page-item <c:choose><c:when test="${show=='InActive'}">active</c:when></c:choose>"><a class="page-link" href="/menu/get-all?pageNo=0&pageSize=5&show=InActive&locationid=${locationid}">Show Inactive</a></li>
        </ul>
    </nav>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:choose><c:when test="${currentpage=='0'}">disabled</c:when></c:choose>">
                <a class="page-link" href="/menu/get-all?locationid=${locationid}&pageNo=${currentpage-1}&pageSize=5&show=${show}" >Previous</a>
            </li>
            <c:forEach begin="0" end="${pages}" varStatus="counter">
                <li class="page-item <c:choose><c:when test="${currentpage==counter.index}">active</c:when></c:choose>"><a class="page-link" href="/menu/get-all?locationid=${locationid}&pageNo=${counter.index}&pageSize=5&show=${show}" >${counter.count}<br></a></li>
            </c:forEach>
            <li class="page-item <c:choose><c:when test="${currentpage==pages}">disabled </c:when></c:choose>">
                <a class="page-link" href="/menu/get-all?locationid=${locationid}&pageNo=${currentpage+1}&pageSize=5&show=${show}" >Next</a>
            </li>
        </ul>
    </nav>
</div>
<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("search");
        filter = input.value.toUpperCase();
        table = document.getElementById("MenuTable");
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
