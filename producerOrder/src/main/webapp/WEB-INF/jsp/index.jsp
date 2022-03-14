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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<h1>Welcome to THC!</h1>
<c:choose><c:when test="${message!=''}">
    <div class="alert alert-danger hide-child" role="alert">
            ${message}
    </div>
</c:when></c:choose>
</body>
</html>
