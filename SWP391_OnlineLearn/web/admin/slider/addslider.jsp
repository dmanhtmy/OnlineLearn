<%-- 
    Document   : addslider
    Created on : Dec 11, 2022, 3:09:40 PM
    Author     : hp
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>

<link href="css/edit.css" rel="stylesheet" type="text/css" />
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content" style="padding: 20px;">

        <div class="row">
            <div class="col-md-12">
                <section class="panel">
                    <h1 class="panel-heading">
                        Add Slider
                    </h1>
                    <div class="panel-body">
                        <form class="form-horizontal tasi-form" method="post" action="addslider" enctype="multipart/form-data" >

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                <div class="col-sm-10">
                                    <input name ="title"type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Image</label>
                                <div class="col-sm-10">
                                    <input name ="image" type="file" accept="image/*" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Backlink</label>
                                <div class="col-sm-10">
                                    <input name ="backlink"type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-10">
                                    <input type="radio" id="status" name="status1" value="1" checked>
                                    <label for="html">Show</label><br>
                                    <input type="radio" id="status" name="status1" value="0" >
                                    <label for="css">Hide</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Note</label>
                                <div class="col-sm-10">
                                    <input name ="note"type="text" class="form-control">
                                </div>
                            </div>
                            <div style="margin-left: 40%; margin-top: 30px; padding-bottom: 30px">
                                <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Add</button>
                                <a href="sliders"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section><!-- /.content -->
</aside><!-- /.right-side -->



