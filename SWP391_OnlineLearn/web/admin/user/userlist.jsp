<%-- 
    Document   : userlist
    Created on : Dec 4, 2022, 11:47:24 AM
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
                text-align: center;
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
                    Full Name    
                </th>
                <th>
                    Gender
                </th>
                <th>
                    Email
                </th>
                <th>
                    Mobile
                </th>
                <th>
                    Role
                </th>
                <th>
                    Status
                </th>
                <th>
                    detail
                </th>
            </tr>
            <c:forEach items="${requestScope.listUser}" var = "i" >
                <tr>
                    <td>
                        ${i.id}
                    </td>
                    <td>
                        ${i.fullname}
                    </td>
                    <c:if test = "${i.gender == true}">

                        <td>  <c:out value = "male"/></td>
                    </c:if> 
                    <c:if test = "${i.gender == false}">

                        <td>  <c:out value = "female"/></td>
                    </c:if>   
                    <td>
                        ${i.email}
                    </td>


                    <td>
                        ${i.phonenumber}
                    </td>
                    <td>
                        ${i.role.role_name}
                    </td>
                    <c:if test = "${i.status == 1}">

                        <td>  <c:out value = "show"/></td>
                    </c:if> 
                    <c:if test = "${i.status == 2}">

                        <td>  <c:out value = "hide"/></td>
                    </c:if>  
                    <c:if test = "${i.status == 3}">

                        <td>  <c:out value = "hide"/></td>
                    </c:if>
                    <td>
                        <a href="/admin/userdetail?id=${i.id}">edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>