<%-- 
    Document   : test
    Created on : Nov 30, 2022, 11:49:49 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Hello World
            <%= session.getAttribute("user") %>
            !</h1>
            <h1>${requestScope.mail}</h1>

    </body>
</html>
