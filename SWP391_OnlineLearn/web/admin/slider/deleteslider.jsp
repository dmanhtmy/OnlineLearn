<%-- 
    Document   : deleteslider
    Created on : Dec 12, 2022, 7:25:19 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<script type="text/javascript">
</script>
<section class="content" style="padding: 20px;">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel">
                <header class="panel-heading">
                    <div class="panel-heading" style="display: flex ;align-items: center">
                        <h1 style="padding: 10px;">Delete Slider</h1>

                    </div>
                </header>
                <!-- <div class="box-header"> -->
                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                <!-- </div> -->
                <div class="panel-body table-responsive">

                    <form action="deleteslider" method="Post">



                        <table class="table table-hover">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Image</th>
                                <th>Backlink</th>
                                <th>Status</th>
                                <th> Tick </th>
                            </tr>
                            <c:forEach items="${requestScope.list}" var="s" varStatus="a" begin="1">
                                <tr>
                                    <td >${a.index}</td>
                                    <td >${s.title}</td>
                                    <td ><img src="${s.image}" width="200px"></td>
                                    <td ><a href="${s.backlink}">${s.backlink}</a></td>
                                    <td>
                                        <c:if test="${s.status eq '0'}">
                                            Hide
                                        </c:if>
                                        <c:if test="${s.status eq '1'}">
                                            Show
                                        </c:if>

                                    </td>
                                    <td>
                                        <input type="checkbox" value="${s.id}" name="check12" />
                                    </td>
                                </tr>
                            </c:forEach>                                
                        </table>
                        <input type="submit" value="Delete" />
                    </form>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div>
    </div>

</section>

