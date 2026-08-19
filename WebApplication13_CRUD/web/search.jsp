<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
    </head>
    <body>
    <!-- Cach 1 -->
    <jsp:include page="welcome.jsp"/>
    <hr/>
    
    <form action="MainController" method="POST">
        <input type="text"
               name="txtKeywords"
               value="${param.txtKeywords}"/>

        <input type="submit" name="action" value="Search"/>
    </form>

    <table border="1">
        <c:if test="${not empty List}">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>CreatedAt</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>

                <tbody>
                    <c:forEach var="p" items="${productList}">
                        <tr>
                            <td>${p.productID}</td>
                            <td>${p.productName}</td>
                            <td>${p.description}</td>
                            <td>${p.price}</td>
                            <td>${p.quantity}</td>
                            <td>${p.image}</td>
                            <td>${p.status}</td>
                            <td>${p.createdAt}</td>
                            <td><a href="UpdateProductController?productId=${p.productID}&txtKeywords=${txtKeywords}">Update</a></td>
                            <td><a href="DeleteProductController?productId=${p.productID}&txtKeywords=${txtKeywords}" onclick="return confirm('Are you sure you want to delete this Product?')">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </c:if>

        <c:if test="${empty List}">
            <tr>
                <td colspan="10">
                    No products found matching your search!
                </td>
            </tr>
        </c:if>
    </table>
</body>
</html>
