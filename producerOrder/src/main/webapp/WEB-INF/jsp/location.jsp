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
    <form name="active" action="/location/addLocation" method="get">
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
            <th scope="col" style="width: 3%" >#</th>
            <th scope="col" style="width: 15%" >Name</th>
            <th scope="col" style="width: 25%">Addressline</th>
            <th scope="col" style="width: 7%">City</th>
            <th scope="col" style="width: 3%">State</th>
            <th scope="col" style="width: 7%">Zip</th>
            <th scope="col" >Status</th>
            <th scope="col">Menu</th>
            <th scope="col">Open Hours</th>
            <th scope="col" style="width: 10%">Update</th>
            <th scope="col" style="width: 10%">Activate/Deactivate</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="var" items="${Locations}" varStatus="counter">

        <tr>
            <td>${counter.count}</td>
            <td><c:out value="${var.name}"/></td>
            <td><c:out value="${var.addressline1}"/><c:out value="${var.addressline2}"/></td>
            <td><c:out value="${var.city}"/></td>
            <td><c:out value="${var.state}"/></td>
            <td><c:out value="${var.zip}"/></td>
            <td><c:out value="${var.status}"/></td>
            <td>
                <form name="edit" action="/menu/getMenus" method="get">
                    <input type="hidden" name="locationid" id="locationid" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Menu</button>
                </form>
            </td>
            <td>
                <form name="hours" action="/openHours/getOpenHours" method="get">
                    <input type="hidden" name="locationid" id="locationid" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Open Hours</button>
                </form>
            </td>
            <td>
                <form name="edit" action="/location/editLocation" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-warning" style="width: 100%">Edit</button>
                </form>
            </td>
            <td>
            <c:choose>
            <c:when test="${var.status=='Active'}">

                <form name="delete" action="/location/cancelLocationById" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
<%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 100%">DeActivate</button>
                </form>

            </c:when>
            <c:otherwise>

                    <form name="active" action="/location/activeLocationById" method="post">
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