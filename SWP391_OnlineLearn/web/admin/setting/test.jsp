<%-- 
    Document   : test
    Created on : Dec 1, 2022, 9:47:21 AM
    Author     : windc
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
       <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Settings Name</th>
                            <th>Description</th>
                            <th>Type</th>
                            <th>Value</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.listBySearch}" var="i">
                            <tr>
                                <td onclick="doEdit(${i.settingslist_id})">${i.settingslist_id}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.settingsName}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.description}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.type.setting_type_name}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.value}</td>
                                <td><button onclick="doChangeStatus(${i.settingslist_id}, '${i.status.setting_status_id}')" type="button" class="label 
                                            <c:if test="${i.status.setting_status_id eq '1'}">
                                                label-danger
                                            </c:if>
                                            <c:if test="${i.status.setting_status_id eq '2'}">
                                                label-success 
                                            </c:if>
                                            ">
                                        <c:if test="${i.status.setting_status_id eq '1'}">
                                            Active
                                        </c:if>
                                        <c:if test="${i.status.setting_status_id eq '2'}">
                                            Deactive 
                                        </c:if>
                                    </button></td>
                            </tr>
                        </c:forEach>                                
                    </table>
    </body>
    <script>
    function doEdit(id) {
        window.location.href = "edit?sid=" + id;
    }
    </script><!-- comment -->
</html>
