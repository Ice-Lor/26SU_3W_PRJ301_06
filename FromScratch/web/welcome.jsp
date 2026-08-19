<%-- 
    Document   : welcome
    Created on : Aug 18, 2026, 10:16:32 PM
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
    <c:if test="${sessionScope.LOGIN_USER == null}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <c:if test="${sessionScope.LOGIN_USER.status == false}">
        <c:redirect url="unavailable.jsp"></c:redirect>
    </c:if>
    <h1>Welcome <c:out value="${sessionScope.LOGIN_USER.fullName}"/> !</h1>
    <c:url var="LogoutLink" value="MainController">
        <c:param name="action" value="Logout"></c:param>
    </c:url>
    <a href="${LogoutLink}">Logout</a>
    <c:url var="SearchLink" value="MainController">
        <c:param name="action" value="SearchPage"></c:param>
    </c:url>
    <a href="${SearchLink}">Search</a>
    <form action="MainController" method="POST">
        <input type="hidden" name="action" value="SearchPage">
        <input type="submit" value="Search Page">
    </form>

    <form action="MainController" method="POST">
        <input type="hidden" name="action" value="Logout">
        <input type="submit" value="Logout">
    </form>
    </body>
</html>
