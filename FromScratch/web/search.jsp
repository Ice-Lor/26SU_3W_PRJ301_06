<%-- 
    Document   : search
    Created on : Aug 19, 2026, 1:48:57 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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

    <table>
        <c:if test="${not empty LIST}">
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
                <c:forEach var="i" items="${LIST}">
                    <tr>
                        <td>${i.productID}</td>
                        <td>${i.productName}</td>
                        <td>${i.description}</td>
                        <td>${i.price}</td>
                        <td>${i.quantity}</td>
                        <td>${i.image}</td>
                        <td>${i.status}</td>
                        <td>${i.createdAt}</td>
                        <td><a href="UpdateController?productID=${i.productID}">Update</a></td>
                        <td><a href="DeleteController?productID=${i.productID}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>

        <c:if test="${empty LIST}">
            <tr>
                <td colspan="10">
                    <c:out value="${ERROR_MESSAGE}"/>
                </td>
            </tr>
        </c:if>
    </table>
</body>
</html>
