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
    <table style="width:100%" class="table table-bordered">
        <thead>
        <tr>
            <th scope="col" style="width: 3%" >#</th>
            <th scope="col" style="width: 15%" >name</th>
            <th scope="col" style="width: 25%">addressline</th>
            <th scope="col" style="width: 7%">city</th>
            <th scope="col" style="width: 3%">state</th>
            <th scope="col" style="width: 7%">zip</th>
            <th scope="col" style="width: 10%">Status</th>
            <th scope="col" style="width: 15%">Update</th>
            <th scope="col" style="width: 15%">Activate/Deactivate</th>
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
                <form name="delete" action="/location/editLocation" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
                        <%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-success" style="width: 75%">Edit</button>
                </form>
            </td>
            <td>
            <c:choose>
            <c:when test="${var.status=='Active'}">

                <form name="delete" action="/location/cancelLocationById" method="post">
                    <input type="hidden" name="id" id="id" value="${var.id}">
<%--                    <input id="delete" type="submit" name="delete" value="Deactivate" />--%>
                    <button id="deactivate" name="deactivate" type="submit" class="btn btn-danger" style="width: 75%">DeActivate</button>
                </form>

            </c:when>
            <c:otherwise>

                    <form name="delete" action="/location/activeLocationById" method="post">
                        <input type="hidden" name="id" id="id" value="${var.id}">
<%--                        <input id="activate" type="submit" name="activate" value="Activate" />--%>
                        <button id="activate" name="activate" type="submit" class="btn btn-primary" style="width: 75%" >Activate</button>
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
</body>

</html>