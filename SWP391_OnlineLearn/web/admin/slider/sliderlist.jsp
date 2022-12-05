<%-- 
    Document   : sliderlist
    Created on : Dec 2, 2022, 12:05:48 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            html{
                font-size: small;

            }
            table{
                padding: 10px;

            }
        </style>
    </head>
    <body>
        <table style="width:100%">
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Title

                </th>
                <th>
                    Image
                </th>
                <th>
                    BackLink
                </th>
                <th>
                    Status
                </th>
            </tr>
            <c:forEach items="${requestScope.list}" var = "i" >
                <tr>
                    <td>
                        ${i.id}
                    </td>
                    <td>
                        ${i.title}
                    </td>
                    <td>
                        ${i.image}
                    </td>
                    <td>
                        ${i.backlink}
                    </td>

                    <c:if test = "${i.status == 1}">

                        <td>  <c:out value = "show"/></td>
                    </c:if> 
                    <c:if test = "${i.status == 0}">

                        <td>  <c:out value = "hide"/></td>
                    </c:if>   
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
