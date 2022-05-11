<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Account</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
    <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
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
<section class="changeForm container" style="padding: 50px">
    <section class="row justify-content-md-center">
        <form style="width: 450px" method="post">
            <strong>Change your details</strong><hr>
            ${showChangeInfo}
            <div class="form-group">
                <label for="emailInput">Change Email address</label>
                <input type="email" class="form-control" id="emailInput"
                       name="emailInput" aria-describedby="emailHelp"
                       placeholder="Enter email"
                       value="${sessionScope.user.getEmail()}"
                >
            </div>
            <div class="form-group">
                <label for="passwordInput">Change Password</label>
                <input type="password" class="form-control"
                       id="passwordInput" name="passwordInput"
                       placeholder="Password"
                >
            </div>
            <div class="form-group">
                <label for="addressInput">Change User Address</label>
                <input type="text" class="form-control" id="addressInput"
                       name="addressInput" placeholder="Address"
                       value="${sessionScope.user.getAddress()}"
                >
            </div>
            <div class="form-group">
                <label for="firstNameInput">Change First Name</label>
                <input type="text" class="form-control" id="firstNameInput"
                       name="firstNameInput" placeholder="First Name"
                        value="${sessionScope.user.getFirstName()}"
                >
            </div>
            <div class="form-group">
                <label for="secondNameInput">Change Second Name</label>
                <input type="text" class="form-control" id="secondNameInput"
                       name="secondNameInput" placeholder="Second Name"
                       value="${sessionScope.user.getSecondName()}"
                >
            </div>
            <br>
            <button type="submit" class="btn btn-dark" id="changeButton" name="changeButton" value="true" style="width: 100%; text-align: center">Change</button>
        </form>
    </section>
</section>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
