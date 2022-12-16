<%-- 
    Document   : sliderdetail
    Created on : Dec 11, 2022, 3:09:26 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<link href="css/edit.css" rel="stylesheet" type="text/css" />
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <section class="panel" style="padding: 20px;">
                    <h1 class="panel-heading">
                        Edit Slider
                    </h1>
                    <div class="panel-body">
                        <form class="form-horizontal tasi-form" method="POST" action="sliderdetail" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">ID</label>
                                <div class="col-sm-10">
                                    <input readonly="" value="${requestScope.sliderdetail.id}" name ="id" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                <div class="col-sm-10">
                                    <input value="${requestScope.sliderdetail.title}" name ="title" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Image</label>
                                <div class="col-sm-10">
                                    <img id="blah"  src="${requestScope.sliderdetail.image}" style="border-radius: 1rem" width="50%" value = "${requestScope.sliderdetail.image}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Upload Image</label>
                                <div class="col-sm-10">
                                    <input type="file" accept="image/*" name="image" id="imgInp" value="${requestScope.sliderdetail.image}">
                                </div>
                            </div>    
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Backlink</label>
                                <div class="col-sm-10">
                                    <input value="${requestScope.sliderdetail.backlink}" name="backlink" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-10">
                                    <input type="radio" id="status" name="status1" value="1"
                                           ${requestScope.sliderdetail.status == 1 ? "checked=\"checked\"" : ""}>
                                    <label for="html">Show</label><br>
                                    <input type="radio" id="status" name="status1" value="0"
                                           ${requestScope.sliderdetail.status == 0 ? "checked=\"checked\"" : ""}>
                                    <label for="css">Hide</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Note</label>
                                <div class="col-sm-10">
                                    <input value="${s.note}" name ="note"type="text" class="form-control">
                                </div>
                            </div>
                            <div style="margin-left: 40%; margin-top: 30px; padding-bottom: 30px">
                                <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Edit</button>
                                <a href="slider"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<script>
    imgInp.onchange = evt => {
        const [file] = imgInp.files;
        if (file) {
            blah.src = URL.createObjectURL(file);
        }
    };
</script>

