<%-- 
    Document   : adduser
    Created on : Dec 5, 2022, 10:51:55 AM
    Author     : hp
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../layout/layout.jsp" %>

<section class="content" style="padding: 10px;">
    <div class="row">
        <div class="col-md-12">
            <section class="panel">
                <h1 class="panel-heading">
                    Add User
                </h1>
                <div class="panel-body">
                    <form class="form-horizontal tasi-form" method="post" action="adduser" >
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Username</label>
                            <div class="col-sm-10">
                                <input name="username" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Password</label>
                            <div class="col-sm-10">
                                <input name="password" type="password" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Full name</label>
                            <div class="col-sm-10">
                                <input name ="fullname" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Gender</label>
                            <div class="col-sm-10">
                                <input type="radio" id="gender" name="gender" value="male" checked>
                                <label for="html">Male</label><br>
                                <input type="radio" id="gender" name="gender" value="female" >
                                <label for="css">Female</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Address</label>
                            <div class="col-sm-10">
                                <input name ="address" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <input name ="email" type="text" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Phone number</label>
                            <div class="col-sm-10">
                                <input name ="phonenumber" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" id="status" name="status" value="2" checked>
                                <label for="html">Active</label><br>
                                <input type="radio" id="status" name="status" value="1" >
                                <label for="css">Deactive</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Role</label>
                            <div class="col-sm-10">
                                <select name="role_id">
                                    <option value="1">admin</option>
                                    <option value="4">customer</option>
                                </select>
                            </div>
                        </div>
                        <div style="margin-left: 40%; margin-top: -30px; padding-bottom: 30px">
                            <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Add</button>
                            <a href="users"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->




