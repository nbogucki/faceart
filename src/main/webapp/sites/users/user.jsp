<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/custom-functions.tld" prefix="fn" %>

<html>
<head>
  <title>User</title>
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
    <form style="width: 450px" method="post">
      <strong>${userToShowOpinions.getFullName()}</strong><hr>
      <input type="hidden" id="userToShowOpinionsId" name="userToShowOpinionsId" value="${userToShowOpinions.getId()}">
      <div class="form-group">
        <label for="opinionInput">Add Opinion</label>
        <input type="number" min=0 max=6 class="form-control" id="opinionInput" name="opinionInput" placeholder="Set Opinion number 0-6">
      </div>
      <div class="form-group">
        <label for="commentInput">Add Comment</label>
        <input type="text" class="form-control" id="commentInput" name="commentInput" required placeholder="Comment">
      </div>
      <br>
      <button type="submit" class="btn btn-dark" id="opinionButton" name="opinionButton" value="true" style="width: 100%; text-align: center">Add Opinion</button>
    </form>
  </section>
  <section>
      <strong>Opinions Average: ${opinionsAverage}</strong>
  </section>
  <section class="row">
    <c:forEach items="${userToShowOpinions.getComments()}" var="comment">
      <div class="card col-md-6" style="width: 18rem;">
        <div class="card-body">
          <p class="card-text">${comment.value}</p>
          <span>Created At: ${comment.createdAt}</span>
        </div>
      </div>
    </c:forEach>
  </section>
</section>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
