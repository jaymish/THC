<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/13/22
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>THC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<%@include file="header.html" %>
<h1>Welcome to THC!</h1>
<c:choose><c:when test="${message!=''}">
    <div class="alert alert-danger hide-child" role="alert">
            ${message}
    </div>
</c:when></c:choose>
</body>
</html>
