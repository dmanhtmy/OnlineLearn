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
                padding: 20px;
                text-align: center;
                width: 95%;
                height: 100%;
                border: solid white;
                margin: auto;
                border-radius: 10px;
                background-color: white;
            }
            .btn-edit{
                height: 20px;
                width: 30px;
                background-color: #3498DB ;
                color: white;
                border-radius: 4px;
            }
            h1{
                padding: 10px;
                color: #3498DB;
            }
            .menu-container{
                display: flex;
                padding: 10px;
                margin: auto;
                justify-content: space-between;
            }
            .filter-container{
                display: flex;
                justify-content: space-between;
            }
            td{
                height: 30px;
                background: #3498DB;
            }
        </style>
    </head>
    <body>
        <h1>User List</h1>
        <div class="menu-container">
            <div class="filter-container">
                <h3>Filter</h3>
                <select name="cars" id="cars">
                    <option value="1">Admin</option>
                    <option value="4">Customer</option>
                </select>
            </div>
            <div>
                <a href="admin/user/adduser.jsp"> Add User</a>
            </div>
        </div>
        <table>
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
                    Detail
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
                        <a href="/admin/userdetail?id=${i.id}" class="btn-edit">edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>