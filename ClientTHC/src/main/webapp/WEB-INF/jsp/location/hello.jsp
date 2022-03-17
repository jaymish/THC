<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/14/22
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html xmlns = "http://www.w3.org/1999/xhtml" xmlns:th = "http://www.thymeleaf.org"
      xmlns:sec = "http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
  <title>Hello World!</title>
</head>
<body>
<h1 th:inline = "text">Hello [[${#httpServletRequest.remoteUser}]]!</h1>
<form th:action = "@{/logout}" method = "post">
  <input type = "submit" value = "Sign Out"/>
</form>
</body>

</html>
