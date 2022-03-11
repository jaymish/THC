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
    <form name="active" action="/menu/addMenu" method="get">
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
            <th scope="col" style="width: 3%" >#</th>
            <th scope="col" style="width: 15%" >Item Name</th>
            <th scope="col" style="width: 25%">Description</th>
            <th scope="col" style="width: 7%">Price</th>
            <th scope="col" style="width: 3%">Category</th>
            <th scope="col" style="width: 7%">SubCategory</th>
            <th scope="col" >Status</th>
            <th scope="col" style="width: 10%">Update</th>
            <th scope="col" style="width: 10%">Activate/Deactivate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="var" items="${LocationMenu}" varStatus="counter">

            <tr>
                <td>${counter.count}</td>
                <td><c:out value="${var.itemName}"/></td>
                <td><c:out value="${var.description}"/></td>
                <td><c:out value="${var.price}"/></td>
                <td><c:out value="${var.category}"/></td>
                <td><c:out value="${var.subCategory}"/></td>
                <td><c:out value="${var.status}"/></td>
                <td>
                    <form name="edit" action="/menu/editMenu" method="post">
                        <input type="hidden" name="id" id="id" value="${var.id}">
                            <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                        <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 75%">Edit</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${var.status=='Active'}">

                            <form name="delete" action="/menu/cancelMenuById" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                    <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                                <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 75%">DeActivate</button>
                            </form>

                        </c:when>
                        <c:otherwise>

                            <form name="active" action="/menu/activeMenuById" method="post">
                                <input type="hidden" name="id" id="id" value="${var.id}">
                                    <%--                        <input id="activate" type="submit" name="activate" value="Activate" />--%>
                                <button id="activate" name="activate" type="submit" class="btn btn-success" style="width: 75%" >Activate</button>
                            </form>

                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!--% for%-->
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
