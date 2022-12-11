<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <section class="panel">
                <header class="panel-heading">
                    <a href="subjectlist">
                        <span>Subject List</span>
                    </a>
                </header>
                <div class="panel-body">
                    <c:if test="${sessionScope.message != null}">
                        <div style="text-align: center">
                            <h1>${message}<i class="fa fa-check-square-o"></i></h1>
                        </div>
                    </c:if>
                    <c:set var="message" value="${null}"></c:set>
                        <form class="form-horizontal tasi-form" method="POST" action="editcoursesubject" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="col-sm-8">
                                    <input style="display: none" value="${course.cid}" name ="cid "type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Title</label>
                            <div class="col-sm-10">
                                <input name ="title" type="text" value="${course.title}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">ThumbNail</label>
                            <div class="col-sm-10">
                                <img id="blah" onchange="PreviewImage();" src="${course.thumbnail}" style="border-radius: 1rem" width="50%">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Upload Thumbnail</label>
                            <div class="col-sm-10">
                                <input type="file" accept="image/*" name="thumbnail" id="imgInp">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Upload Document</label>
                            <div class="col-sm-10">
                                <input type="file" name="document" id="fileInput">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Document File</label>
                            <div class="col-sm-10">
                                <a href="${pageContext.servletContext.contextPath}/dowload/document?id=${course.cid}">${course.filename}</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Brief Info</label>
                            <div class="col-sm-10">
                                <textarea  name="brief" class="form-control round-input" style="height: 100px;color: black" required="required"> ${course.briefinfo}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Introduction</label>
                            <div class="col-sm-10">
                                <textarea id="introduction" name="introduction" class="form-control round-input" style="height: 100px;color: black" required="required">${course.introduction}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Author</label>
                            <div class="col-sm-10">
                                <select name="authors" required="required">
                                    <c:forEach items="${requestScope.authors}" var="i">
                                        <option value="${i.id}" ${i.id == course.author.id?"selected":""}>${i.fullname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Category</label>
                            <div class="col-sm-10">
                                <select name="categorys" required="required">
                                    <c:forEach items="${requestScope.categorys}" var="i">
                                        <option value="${i.id}">${i.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div <c:if test="${user.role.role_id == 3}">
                                hidden="hidden"
                            </c:if>>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">List Price</label>
                                <div class="col-sm-10">
                                    <input name ="listprice" type="text" class="form-control" value="${course.listprice}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Sale Price</label>
                                <div class="col-sm-10">
                                    <input name ="saleprice" type="text" class="form-control" value="${course.saleprice}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-10">
                                    <input type="radio" id="status" ${course.status ==  true?"checked":""} name="status" value="Publish" required="required">
                                    <label for="html">Publish</label><br>
                                    <input type="radio" id="status" ${course.status ==  false?"checked":""} name="status" value="Unpublish">
                                    <label for="html">Unpublish</label><br>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Future Flag</label>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input type="checkbox" name="feature" value="ON" ${course.featureflag ==  true?"checked":""}/>
                                </div>
                            </div>
                        </div>
                        <button type="submit" value="${course.cid}" name="cidi" id="buu" class="btn btn-success btn-lg ">Edit Subject</button>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->
<!-- Modal -->
<script src="//cdn.ckeditor.com/4.17.2/full/ckeditor.js"></script>

<script>
                                    CKEDITOR.replace('introduction');

                                    document.getElementById('fileInput').onchange = function () {
                                        alert('Selected file: ' + this.value);
                                    };
</script>

<script>
    imgInp.onchange = evt => {
        const [file] = imgInp.files;
        if (file) {
            blah.src = URL.createObjectURL(file);
        }
    };
</script>

