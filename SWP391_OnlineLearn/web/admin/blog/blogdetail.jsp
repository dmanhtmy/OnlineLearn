<%-- 
    Document   : blogdetail
    Created on : Dec 2, 2022, 11:34:23 AM
    Author     : Mr Tuan
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
        <c:forEach items="${requestScope.getdetail}" var="bt">
            <h1>${bt.getBlogdetail()}</h1>
        </c:forEach>
    </body>
</html>
