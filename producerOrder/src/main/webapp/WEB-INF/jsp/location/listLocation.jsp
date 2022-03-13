<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>THC Location</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <h1>Location List</h1>
    <form name="activate" action="/location/add" method="get">
        <button id="Add" type="submit" class="btn btn-primary" style="width: 20%" >Add Location</button>
    </form>

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
            <th scope="col" style="width: 25%">Addressline</th>
            <th scope="col" style="width: 7%">City</th>
            <th scope="col" style="width: 3%">State</th>
            <th scope="col" style="width: 5%">Zip</th>
            <th scope="col" style="width: 5%">Menu</th>
            <th scope="col" style="width: 8%">Open Hours</th>
            <th scope="col" style="width: 8%">Reservation</th>
            <th scope="col" style="width: 11%">Update</th>
            <th scope="col" style="width: 11%">Activate/Deactivate</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="var" items="${Locations}" varStatus="counter">

        <tr>
            <td>${counter.count}</td>
            <td><c:out value="${var.name}"/></td>
            <td><c:out value="${var.addressline1}"/> <c:out value="${var.addressline2}"/></td>
            <td><c:out value="${var.city}"/></td>
            <td><c:out value="${var.state}"/></td>
            <td><c:out value="${var.zip}"/></td>
            <td>
                <form name="edit" action="/menu/get-all" method="get">
                    <input type="hidden" name="locationid" id="locationid" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-dark" style="width: 100%">Menu</button>
                </form>
            </td>
            <td>
                <form name="hours" action="/open-hours/get-all" method="get">
                    <input type="hidden" name="locationid" id="locationid" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-info" style="width: 100%">Open Hours</button>
                </form>
            </td>
            <td>
                <form name="hours" action="/reservation/get-all" method="get">
                    <input type="hidden" name="locationid" id="locationid" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-secondary" style="width: 100%">Reservation</button>
                </form>
            </td>
            <td>
                <form name="edit" action="/location/edit" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Edit</button>
                </form>
            </td>
            <td>
            <c:choose>
            <c:when test="${var.status=='Active'}">

                <form name="delete" action="/location/deactivate-by-id" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
                    <input type="hidden" name="show" id="show" value="${show}">
<%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 100%">DeActivate</button>
                </form>

            </c:when>
            <c:otherwise>

                    <form name="activate" action="/location/activate-by-id" method="post">
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
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-end">
            <li class="page-item <c:choose><c:when test="${show=='all'}">active</c:when></c:choose>"><a class="page-link" href="/location/get-all?pageNo=0&pageSize=5&show=all">Show All</a></li>
            <li class="page-item <c:choose><c:when test="${show=='Active'}">active</c:when></c:choose>"><a class="page-link" href="/location/get-all?pageNo=0&pageSize=5&show=Active">Show Active</a></li>
            <li class="page-item <c:choose><c:when test="${show=='InActive'}">active</c:when></c:choose>"><a class="page-link" href="/location/get-all?pageNo=0&pageSize=5&show=InActive">Show Inactive</a></li>
        </ul>
    </nav>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <c:choose><c:when test="${currentpage=='0'}">disabled</c:when></c:choose>">
                <a class="page-link" href="/location/get-all?pageNo=${currentpage-1}&pageSize=5&show=${show}" >Previous</a>
            </li>
            <c:forEach begin="0" end="${pages}" varStatus="counter">
                <li class="page-item <c:choose><c:when test="${currentpage==counter.index}">active</c:when></c:choose>"><a class="page-link" href="/location/get-all?pageNo=${counter.index}&pageSize=5&show=${show}" >${counter.count}<br></a></li>
            </c:forEach>
            <li class="page-item <c:choose><c:when test="${currentpage==pages}">disabled </c:when></c:choose>">
                <a class="page-link" href="/location/get-all?pageNo=${currentpage+1}&pageSize=5&show=${show}" >Next</a>
            </li>
        </ul>
    </nav>
<!--% for%-->
</div>
<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("search");
        filter = input.value.toUpperCase();
        table = document.getElementById("LocationTable");
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