<%--
  Created by IntelliJ IDEA.
  User: Duy Le Vu
  Date: 12/2/2021
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
</head>
<body>
<h1>Create new product</h1>
<p>
    <c:if test='${requestScope["message"]!=null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/">Back to products
        <list></list>
    </a>
</p>
<form action="/saveProduct" method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <%--            <tr>--%>
            <%--                <td>ID:</td>--%>
            <%--                <td><input type="text" name="id" id="id"></td>--%>
            <%--            </tr>--%>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" id="price"></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="text" name="quantity" id="quantity"></td>
            </tr>
            <tr>
                <td>Color:</td>
                <td><input type="text" name="color" id="color"></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" id="description"></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select name="categoryId">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.id}. ${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create Product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
