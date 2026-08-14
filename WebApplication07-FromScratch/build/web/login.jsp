<%-- 
    Document   : login.jsp
    Created on : Aug 13, 2026, 9:22:03 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Form</h1>
        <form action="MainController" method="POST">
            Username: <input type="text" name="username" required="">
            Password: <input type="password" name="password" required="">
            <input type="submit" name="action" value="Login">
        </form>
    </body>
</html>
