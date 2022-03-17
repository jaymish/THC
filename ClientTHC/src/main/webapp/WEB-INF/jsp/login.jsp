<%--
  Created by IntelliJ IDEA.
  User: jaymish
  Date: 3/15/22
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <br>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-primary" type="button" onclick="location.href='/';">Home</button>
    </div>
    <form action="/login" method="post" class="row g-3 needs-validation" novalidate>
        <div class="col-md-12">
            <label for="username" class="form-label">Username</label>
            <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend">@</span>
                <input type="text" class="form-control" id="username" aria-describedby="inputGroupPrepend" required name="username">
                <div class="invalid-feedback">
                    Please choose a username.
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" required name="password">
            <div class="invalid-feedback">
                Please provide a valid password.
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="width: 15%">Sign In</button>
        <p class="text-center text-muted mt-5 mb-0">Want to Register? <a href="/registration" class="fw-bold text-body"><u>Register here</u></a></p>
    </form>

</div>
</body>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</html>
