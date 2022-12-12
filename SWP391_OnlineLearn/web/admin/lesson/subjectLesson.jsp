<%-- 
    Document   : subjectLesson
    Created on : 12 Dec, 2022, 11:12:32 PM
    Author     : HP
--%>
<%@page import="DAO.Impl.LessonDAOImpl"%>
<%@page import="Models.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="Models.Topic"%>
<%@page import="Models.Lesson"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../layout/layout.jsp" %>
<main>
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel">
                    <header class="panel-heading">
                        <div class="panel-heading" style="display: flex;">
                            <div style="margin-right: 74%;"> Lesson List</div>
                            <div>
                                <a href="add-lesson">Add New Lesson </a>
                            </div>
                        </div>
                    </header>
                    <!-- <div class="box-header"> -->
                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                    <!-- </div> -->
                    <div class="panel-body table-responsive">
                        <div class="box-tools m-b-15" style="display: flex;">
                            <form action="subjectLesson" method="get">
                                Subject &nbsp; <select name="cid" class="input-sm">
                                    <c:forEach items="${requestScope.ListCourse}" var="i">
                                        <option onclick="" value="${i.cid}" ${i.cid eq param.cid?"selected":""} > ${i.title} </option>
                                    </c:forEach>
                                </select>
                                &nbsp;
                                <input type="submit" value="Choose"/>
                            </form>
                        </div>
                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Type</th>
                                <th>Be Long To Topic</th>
                                <th>Order</th>
                                <th>Status</th>
                            </tr>
                            <c:choose>
                                <c:when test="${requestScope.ListTopic!= null}">
                                    <%  int rold_id = 0;
                                        List<Topic> listTopic = (List) request.getAttribute("ListTopic");
                                        LessonDAOImpl lDao = new LessonDAOImpl();
                                        for (Topic topic : listTopic) {
                                            Topic key = topic;
                                            List<Lesson> value = lDao.getLessonByTopicId(topic.getId());
                                            for (Lesson lesson : value) {

                                    %>
                                    <tr>
                                        <td onclick="doEdit(<%=lesson.getLesson_id()%>)"><%=lesson.getLesson_id()%></td>
                                        <td onclick="doEdit(<%=lesson.getLesson_id()%>)"><%=lesson.getTitle()%></td>
                                        <td onclick="doEdit(<%=lesson.getLesson_id()%>)"><%=lesson.getType()%></td>
                                        <td onclick="doEdit(<%=lesson.getLesson_id()%>)"><%=key.getTopic_title()%></td>
                                        <td onclick="doEdit(<%=lesson.getLesson_id()%>)"><%=lesson.getOder()%></td>
                                        <td>
                                            <%if (lesson.isStatus() == true) {%>
                                            <button style="background-color: green;color: white" onclick="doChangeStatusLesson(<%=lesson.getLesson_id()%>, false,<%=key.getCourse_id()%>)">Active</button>
                                            <%}%>
                                            <%if (lesson.isStatus() == false) {%>
                                            <button style="background-color: red;color: white" onclick="doChangeStatusLesson(<%=lesson.getLesson_id()%>, true,<%=key.getCourse_id()%>)">Deactivate</button>
                                            <%}%>
                                        </td>
                                        <%}
                                        }%>
                                    </c:when>
                                    <c:when test="${requestScope.ListTopicLesson == null}">
                                    <h3>Choose Course</h3>
                                </c:when>
                            </c:choose>
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
    </section>
</main>

<script>
    function doChangeStatusLesson(lesson_id, lesson_status, cid) {
        var c = confirm("Change Lesson Status ?");
        if (c) {
            window.location.href = "subjectLesson?cid=" + cid;
        }
    }
    function doEdit(id) {
        window.location.href = "edit-lesson?id=" + id;
    }
</script>
