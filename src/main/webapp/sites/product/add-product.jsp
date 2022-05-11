<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Add Product</title>
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
<section class="addProductForm container" style="padding: 50px">
  <section class="row justify-content-md-center">
    <form style="width: 450px" method="post" enctype="multipart/form-data">
      <strong>Add Product: </strong><hr>
      ${productInfo}
      <div class="form-group">
        <label for="nameInput"> Name</label>
        <input type="text" class="form-control" id="nameInput"
               name="nameInput"
               placeholder="Enter Product Name" required
        >
      </div>
      <div class="form-group">
        <label for="priceInput"> Price</label>
        <input type="number" min="1" step="any" class="form-control"
               id="priceInput" name="priceInput"
               placeholder="Enter Product Price" required
        >
      </div>
      <div class="form-group">
        <label for="titleInput"> Title</label>
        <input type="text" class="form-control" id="titleInput"
               name="titleInput" placeholder="Enter Product Title"
               required
        >
      </div>
      <div class="form-group">
        <label for="descriptionInput"> Description</label>
        <textarea class="form-control" id="descriptionInput"
               name="descriptionInput" placeholder="Enter Product Description..."
        ></textarea>
      </div>
      <div class="form-group">
        <label for="imagesInput"> Choose an images</label>
        <input type="file" class="form-control" id="imagesInput"
                  name="imagesInput" multiple  required>
      </div>
      <br>
      <button type="submit" class="btn btn-dark" id="addProductButton" name="addProductButton" value="true" style="width: 100%; text-align: center">Add Product!</button>
    </form>
  </section>
</section>
<jsp:include page="../footer.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
