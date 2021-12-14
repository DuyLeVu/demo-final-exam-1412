<%--
  Created by IntelliJ IDEA.
  User: Duy Le Vu
  Date: 12/2/2021
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Product Management Application</title>
</head>
<body>
<center>
  <h1>Product Management</h1>
  <h2>
    <a href="/create">Add New Product</a>
  </h2>
</center>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of Products</h2></caption>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Quantity</th>
      <th>Color</th>
      <th>Description</th>
      <th>CategoryId</th>
    </tr>
    <c:forEach var="i" begin="0" end="${products.size()-1}">
      <tr>
        <td><c:out value="${products.get(i).id}"/></td>
        <td><c:out value="${products.get(i).name}"/></td>
        <td><c:out value="${products.get(i).price}"/></td>
        <td><c:out value="${products.get(i).quantity}"/></td>
        <td><c:out value="${products.get(i).color}"/></td>
        <td><c:out value="${products.get(i).description}"/></td>
        <td><c:out value="${categories.get(i).name}"/></td>
        <td>
          <a href="/edit&id=${products.get(i).id}">Edit</a>
          <a href="/products?action=delete&id=${products.get(i).id}">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <br>
  <form action="/">
    <input type="text" placeholder="Search" name="Search">
    <input type="submit" value="Search">
  </form>
  <p>
   ${backToProducts}
  </p>
</div>
</body>
</html>
