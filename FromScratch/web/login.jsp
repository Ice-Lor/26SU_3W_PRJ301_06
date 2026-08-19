<%-- 
    Document   : login
    Created on : Aug 18, 2026, 8:57:45 PM
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
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Username</td>
                    <td>
                        <input type="text"
                               name="txtUsername"
                               required/>
                    </td>
                </tr>

                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password"
                               name="txtPassword"
                               required/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Login"/>
                    </td>
                </tr>
            </table>
        </form>

    <c:if test="${not empty errorMessage}">
        <span style="color: red">
            ${errorMessage}
        </span>
    </c:if>
    </body>
</html>
