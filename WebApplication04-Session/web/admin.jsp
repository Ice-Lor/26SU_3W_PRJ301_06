<%-- 
    Document   : admin
    Created on : Aug 12, 2026, 8:09:35 PM
    Author     : admin
--%>

<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            UserDTO loggedUser = (UserDTO) session.getAttribute("loggedUser");
            if(!loggedUser.getRoleID().equalsIgnoreCase("ADM") && !loggedUser.getRoleID().equalsIgnoreCase("MNG")){
                String url = "error.jsp";
                request.setAttribute("errorMessage", "403 - Access denied");
                request.getRequestDispatcher(url).forward(request, response);
            }
        %>
        <h1>Mot so tinh nang cua admin!</h1>
    </body>
</html>
