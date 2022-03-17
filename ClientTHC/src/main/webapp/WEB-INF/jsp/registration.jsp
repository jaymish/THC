<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/14/22
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <br>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-primary" type="button" onclick="location.href='/';">Home</button>
    </div>
    <form action="/register" method="post" class="row g-3 needs-validation" novalidate>
        <div class="col-md-12">
            <label for="validationCustomUsername" class="form-label">Username</label>
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend">@</span>
                <input type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" required name="name">
                <div class="invalid-feedback">
                    Please choose a username.
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <label for="validationCustom04" class="form-label">Password</label>
            <input type="password" class="form-control" id="validationCustom04" required name="password">
            <div class="invalid-feedback">
                Please provide a valid password.
            </div>
        </div>
        <div class="col-md-12">
            <label for="validationCustom05" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="validationCustom05" required>
            <div class="invalid-feedback">
                Please provide a valid password.
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="width: 15%">Register</button>
        <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="/login" class="fw-bold text-body"><u>Login here</u></a></p>
    </form>
</div>
</body>
<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</html>
