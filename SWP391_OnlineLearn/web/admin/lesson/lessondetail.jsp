<%-- 
    Document   : lessondetail
    Created on : Dec 14, 2022, 6:03:15 AM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../layout/layout.jsp" %>
<section class="content" style="padding: 20px;">
    <div class="row">
        <div class="col-md-12">
            <section class="panel">
                <h1 class="panel-heading">
           Update
                </h1>
          

                    <form class="form-horizontal tasi-form" action="editlesson" method="Post" >
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input style="display: none" value="${requestScope.lesson.lesson_id}" name ="lesson_id"type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Title</label>
                            <div class="col-sm-10">
                                <input name ="title" value="${requestScope.lesson.title}" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Type</label>
                            <div class="col-sm-10">
                                <input type="radio" ${requestScope.lesson.type == 1?"checked":""} name="type" value="1" required="required">
                                <label for="html">Lesson</label><br>
                                <input type="radio" ${requestScope.lesson.type == 2?"checked":""} name="type" value="2">
                                <label for="html">Topic</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Belongtotopic</label>
                            <div class="col-sm-10">
                                <select name="belongtotopic" required="required">
                                    <c:forEach items="${requestScope.ListCourse1}" var="i">
                                        <option value="${i.cid}" ${i.cid eq param.cid?"selected":""} > ${i.title} </option>
                                    </c:forEach>                                  
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Order</label>
                            <div class="col-sm-10">
                                <input name ="order" type="number" value="${requestScope.lesson.oder}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" ${requestScope.lesson.status == true?"checked":""} name="status" value="active" required="required">
                                <label for="html">Active</label><br>
                                <input type="radio" ${requestScope.lesson.status == false?"checked":""} name="status" value="deactive">
                                <label for="html">Inactive</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Video link</label>
                            <div class="col-sm-10">
                                <input name ="videolink" type="text" value="${requestScope.lesson.videolink}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Content</label>
                            <div class="col-sm-10">
                                <textarea id="content" name="content" class="form-control round-input" style="height: 100px;color: black" required="required">${requestScope.lesson.content}</textarea>
                            </div>
                        </div>
                        <div style="margin-left: 40%; margin-top: 30px; padding-bottom: 30px">
                            <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Update</button>
                            <a href="subjectLesson"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->



