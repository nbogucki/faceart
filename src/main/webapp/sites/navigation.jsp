<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">FeartArt</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}">Home</a></li>
                <li class="nav-item">
                    <a class="nav-link" id="shopSite" href="shop" role="button">Shop</a>
                </li>
            </ul>
            <a class="btn btn-outline-danger" href="favourites" role="button">
                <i class="bi bi-heart"></i>
                <span>Favourites</span>
                <span class="badge bg-dark text-white ms-1 rounded-pill" id="favouriteCounter">${sessionScope.favourite.products.size()}</span>
            </a>
            <form class="d-flex" style="margin: 0">
                <button class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    Cart
                    <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                </button>
            </form>
            <c:choose>
                <c:when test="${sessionScope.user == '' || sessionScope.user == null}">
                    <a href="login" class="btn btn-outline-dark" role="button">
                        <i class="bi-person-fill me-1"></i>
                        Log In
                    </a>
                </c:when>
                <c:otherwise>
                    <div class="dropdown">
                        <button class="btn btn-outline-dark dropdown-toggle" id="navbarDropdownLogin"
                            role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <i class="bi-person-fill me-1"></i>
                                ${sessionScope.user.getFullName()}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownLogin">
                            <li><a class="dropdown-item" href="account">my account</a></li>
                            <li><a class="dropdown-item" href="orders">my orders</a></li>
                            <li><a class="dropdown-item" href="my-products">my products</a></li>
                            <c:if test="${sessionScope.user.hasRole(\"ROLE_ADMIN\")}">
                                <li><a class="dropdown-item" href="manage-users">manage users</a></li>
                            </c:if>
                        <li><hr class="dropdown-divider" /></li>
                            <li><a class="dropdown-item" href="logout">log out</a></li>
                        </ul>
                    </div>
                    <a class="btn btn-outline-dark" href="add-product" role="button">
                        <i class="bi bi-plus-square"></i>
                        <span>Add Product</span>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>