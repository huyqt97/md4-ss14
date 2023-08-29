<%--
  Created by IntelliJ IDEA.
  User: shmily
  Date: 08/08/2023
  Time: 08:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>Edit Product</h1>
<form action="<%=request.getContextPath()%>/ProductController" method="post">
  <label for="id">ID</label>
  <input type="text" id="id" name="id" readonly value="${product.id}">

  <label for="name">Name</label>
  <input type="text" id="name" name="name" value="${product.name}">
  <label for="des">Description</label>
  <textarea type="text" id="des" name="des" placeholder="${product.descriptions}"></textarea>
  <br>
  <label for="price">Price</label>
  <input type="text" id="price" name="price" value="${product.price}">
  <label for="stock">Stock</label>
  <input type="number" id="stock" name="stock" value="${product.stock}" >
  <br>
  <label >imageUrl</label>
  <textarea name="imageUrl" cols="30" rows="10" placeholder="${product.imageUrl}"></textarea>
  <input type="submit" value="UPDATE" name="action"/>
</form>
</body>
</html>

