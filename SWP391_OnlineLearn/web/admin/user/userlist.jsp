<%-- 
    Document   : userlist
    Created on : Dec 4, 2022, 11:47:24 AM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../layout/layout.jsp" %>

<script>
    function doEdit(id)
    {
        window.location.href = "userdetail?id=" + id;
    }
    function change() {
        document.getElementById("f1").submit();
    }
</script>

<main>
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel">
                    <header class="panel-heading">
                        <div class="panel-heading" style="display: flex;justify-content: space-between;align-items: center;">
                            <h1 style="padding: 10px"> User  List</h1>
                            <div>
                                <a href="adduser" style="padding: 10px">Add user</a>
                            </div>
                        </div>
                    </header>

                    <div class="panel-body table-responsive">
                        <div class="box-tools m-b-15" style="display: flex;">
                            <form action="users" method="get" id="f1">
                                Status <select name="status" class="input-sm" onchange="change()" >
                                    <option value="-1" ${requestScope.cid eq -1?"selected=\"selected\"" : ""}>All</option>
                                    <option value="1" ${requestScope.cid eq 1?"selected=\"selected\"" : ""}>Active</option>
                                    <option value="2" ${requestScope.cid eq 2?"selected=\"selected\"" : ""}>Deactive</option>
                                    <option value="3" ${requestScope.cid eq 3?"selected=\"selected\"" : ""}>Denied</option>
                                </select>

                                <input type="text" name="keyword" placeholder="Enter name, email.."/>
                                <input type="submit" value="Search"/>
                            </form>
                        </div>
                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
                                <th>Gender</th>
                                <th>Email</th>
                                <th>Moblie</th>
                                <th>Role</th>
                                <th>Status</th>
                            </tr>
                            <c:forEach items="${requestScope.listUser}" var="i">
                                <tr>
                                    <td onclick="doEdit(${i.id})">${i.id}</td>
                                    <td onclick="doEdit(${i.id})">${i.fullname}</td>
                                    <td onclick="doEdit(${i.id})"> 
                                        <c:if test="${i.gender == true}">
                                            Male
                                        </c:if>
                                        <c:if test="${i.gender == false}">
                                            Female 
                                        </c:if>
                                    </td>
                                    <td onclick="doEdit(${i.id})">${i.email}</td>
                                    <td onclick="doEdit(${i.id})">${i.phonenumber}</td>
                                    <td onclick="doEdit(${i.id})">${i.role.role_name}</td>
                                    <td>
                                        <c:if test="${i.status eq '1'}">
                                            Deactive
                                        </c:if>
                                        <c:if test="${i.status eq '2'}">
                                            Active
                                        </c:if>
                                        <c:if test="${i.status eq '3'}">
                                            Denied
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>  
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
       
    </section>
</section>
</main> 