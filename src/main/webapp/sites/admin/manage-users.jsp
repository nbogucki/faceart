<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Manage Users</title>
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
<section class="loginForm container" style="padding: 50px">
    <section class="row justify-content-md-center">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">id</th>
                <th scope="col">Email</th>
                <th scope="col">Products</th>
                <th scope="col">Activate or Deactivate User</th>
                <th scope="col">Remove User</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <form method="post">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.email}</td>
                        <td>
                            <div class="text-center">
                                <button class="btn btn-outline-dark mt-auto" name="showProducts" value="${user.id}">
                                    Show Products!
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="text-center">
                                <button class="btn btn-outline-success mt-auto" name="activate" value="${user.id}">
                                    Users is - ${user.active}
                                </button>
                            </div>
                        </td>
                        <td>
                            <div class="text-center">
                                <button class="btn btn-outline-danger mt-auto" name="remove" value="${user.id}">
                                    Remove User!
                                </button>
                            </div>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>

    </section>
</section>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
