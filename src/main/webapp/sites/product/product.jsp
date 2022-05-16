<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/custom-functions.tld" prefix="fn" %>

<html>
<head>
    <title>Product</title>
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

        /*****************globals*************/
        body {
            font-family: 'open sans';
            overflow-x: hidden; }

        img {
            max-width: 100%; }

        .preview {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column; }
        @media screen and (max-width: 996px) {
            .preview {
                margin-bottom: 20px; } }

        .preview-pic {
            -webkit-box-flex: 1;
            -webkit-flex-grow: 1;
            -ms-flex-positive: 1;
            flex-grow: 1; }

        .preview-thumbnail.nav-tabs {
            border: none;
            margin-top: 15px; }
        .preview-thumbnail.nav-tabs li {
            width: 18%;
            margin-right: 2.5%; }
        .preview-thumbnail.nav-tabs li img {
            max-width: 100%;
            display: block; }
        .preview-thumbnail.nav-tabs li a {
            padding: 0;
            margin: 0; }
        .preview-thumbnail.nav-tabs li:last-of-type {
            margin-right: 0; }

        .tab-content {
            overflow: hidden; }
        .tab-content img {
            width: 100%;
            -webkit-animation-name: opacity;
            animation-name: opacity;
            -webkit-animation-duration: .3s;
            animation-duration: .3s; }

        .card {
            margin-top: 50px;
            background: #eee;
            padding: 3em;
            line-height: 1.5em; }

        @media screen and (min-width: 997px) {
            .wrapper {
                display: -webkit-box;
                display: -webkit-flex;
                display: -ms-flexbox;
                display: flex; } }

        .details {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column; }

        .colors {
            -webkit-box-flex: 1;
            -webkit-flex-grow: 1;
            -ms-flex-positive: 1;
            flex-grow: 1; }

        .product-title, .price, .sizes, .colors {
            text-transform: UPPERCASE;
            font-weight: bold; }

        .checked, .price span {
            color: #ff9f1a; }

        .product-title, .rating, .product-description, .price, .vote, .sizes {
            margin-bottom: 15px; }

        .product-title {
            margin-top: 0; }

        .size {
            margin-right: 10px; }
        .size:first-of-type {
            margin-left: 40px; }

        .color {
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
            height: 2em;
            width: 2em;
            border-radius: 2px; }
        .color:first-of-type {
            margin-left: 20px; }

        .add-to-cart, .like {
            background: #ff9f1a;
            padding: 1.2em 1.5em;
            border: none;
            text-transform: UPPERCASE;
            font-weight: bold;
            color: #fff;
            -webkit-transition: background .3s ease;
            transition: background .3s ease; }
        .add-to-cart:hover, .like:hover {
            background: #b36800;
            color: #fff; }

        .not-available {
            text-align: center;
            line-height: 2em; }
        .not-available:before {
            font-family: fontawesome;
            content: "\f00d";
            color: #fff; }

        .orange {
            background: #ff9f1a; }

        .green {
            background: #85ad00; }

        .blue {
            background: #0076ad; }

        .tooltip-inner {
            padding: 1.3em; }

        @-webkit-keyframes opacity {
            0% {
                opacity: 0;
                -webkit-transform: scale(3);
                transform: scale(3); }
            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                transform: scale(1); } }

        @keyframes opacity {
            0% {
                opacity: 0;
                -webkit-transform: scale(3);
                transform: scale(3); }
            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                transform: scale(1); } }
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
<div class="container">
    <div class="card">
        <div class="container-fluid">
            <div class="wrapper row">
                <div class="preview col-md-6">
                    <c:set var="count" value="0" scope="page" />
                    <div class="preview-pic tab-content ">
                        <c:forEach items="${product.images}" var="image">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <c:choose>
                                <c:when test="${count=='1'}">
                                    <div class="tab-pane active watermarked" id="pic-${count}"><img src="${image}" /></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="tab-pane watermarked" id="pic-${count}"><img src="${image}" /></div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>

                    <c:set var="count" value="0" scope="page" />
                    <ul class="preview-thumbnail nav nav-tabs">
                        <c:forEach items="${product.images}" var="image">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <li class=""><a data-bs-target="#pic-${count}" data-bs-toggle="tab"><img src="${image}" /></a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="details col-md-6">
                    <a href="user?id=${product.user.getId()}">${product.user.getFullName()}</a>
                    <h3 class="product-title">${product.title}</h3>
                    <p class="product-description">${product.description}</p>
                    <h4 class="price">current price: <span>${product.price}</span></h4>
                    <div class="action">
                        <input type="hidden" value="${product.id}" class="productsId">
                        <c:choose>
                            <c:when test="${fn:containsProductId( sessionScope.favourite.products, product.id )}">
                                <div class="col text-center"><div class="btn btn-outline-danger mt-auto active" id="heart-${product.id}"><i class="bi bi-heart"></i></div></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col text-center"><div class="btn btn-outline-danger mt-auto" id="heart-${product.id}"><i class="bi bi-heart"></i></div></div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${!sessionScope.user.equals(\"\") &&
                            sessionScope.user != null}">
                            <c:choose>
                                <c:when test="${fn:containsProductId( sessionScope.user.cart.products, product.id )}">
                                    <div class="col text-center"><div class="btn btn-outline-dark mt-auto active" id="cart-${product.id}">Remove From Cart</div></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col text-center"><div class="btn btn-outline-dark mt-auto" id="cart-${product.id}">Add to Cart</div></div>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
