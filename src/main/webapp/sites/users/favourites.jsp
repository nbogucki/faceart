<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/custom-functions.tld" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>Favourites</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico" />
    <link href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap-icons.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <style>
        .watermarked {
            position: relative;
        }

        .watermarked:after {
            content: "";
            display: block;
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0px;
            left: 0px;
            background-image: url("https://www.nicepng.com/png/full/137-1376842_png-free-download-how-to-add-a-an.png");
            background-size: 100px 100px;
            background-position: 30px 30px;
        }
    </style>
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
<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach items="${sessionScope.favourite.products}" var="product">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <a href="product?id=${product.id}" style="text-decoration: none; color: black">
                            <div class="watermarked">
                                <img class="card-img-top watermarked" src="${product.images[0]}" alt="..." />
                            </div>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${product.title}</h5>
                                    <!-- Product price-->
                                        ${product.price}
                                </div>
                            </div>
                        </a>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent row">
                            <input type="hidden" value="${product.id}" class="productsId">
                            <div class="col text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to Cart!</a></div>
                            <c:choose>
                                <c:when test="${fn:containsProductId( sessionScope.favourite.products, product.id )}">
                                    <div class="col text-center"><div class="btn btn-outline-danger mt-auto active" id="heart-${product.id}"><i class="bi bi-heart"></i></div></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col text-center"><div class="btn btn-outline-danger mt-auto" id="heart-${product.id}"><i class="bi bi-heart"></i></div></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <c:if test="${!sessionScope.user.equals(\"\") &&
                         sessionScope.user != null && sessionScope.user.hasRole(\"ROLE_ADMIN\")}">
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="edit-product?id=${product.id}">Edit!</a></div>
                            </div>
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="remove-product?id=${product.id}">Remove!</a></div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>