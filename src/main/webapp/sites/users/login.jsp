<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-icons.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="../navigation.jsp" />
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Show and sell your art!</h1>
            <p class="lead fw-normal text-white-50 mb-0">Do what you love and make money!</p>
        </div>
    </div>
</header>
<section class="loginForm container" style="padding: 50px">
    <section class="row justify-content-md-center">
        <form style="width: 450px" method="post">
            <strong>Log In to your account</strong><hr>
            <div class="form-group">
                <label for="emailInput">Email address</label>
                <input type="email" class="form-control" id="emailInput" name="emailInput" aria-describedby="emailHelp" required placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input type="password" class="form-control" id="passwordInput" name="passwordInput" required placeholder="Password">
            </div>
            <div class="form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <br>
            <button type="submit" class="btn btn-dark" id="loginButton" name="loginButton" value="true" style="width: 100%; text-align: center">Log In</button>
            <section style="text-align: center">
                <strong>or </strong><a href="#">Forgot Password</a>
            </section>
            <br><br><strong>Don't have an account? </strong><a href="register">Sign up</a>
        </form>
    </section>
</section>
<jsp:include page="../footer.jsp" />
<script type="text/javascript" href="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" href="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
