<%-- 
    Document   : sliderlist
    Created on : Dec 2, 2022, 12:05:48 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<script type="text/javascript">
    function searchStatus(status)
    {
        window.location.href = "sliders?status1=" + status;
    }

    function submitForm(e) {
        let form = e.parentElement;
        form.submit();
    }
    function doEdit(id) {
        window.location.href = "sliderdetail?id=" + id;
    }
</script>
<section class="content" style="padding: 20px;">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel">
                <header class="panel-heading">
                    <div class="panel-heading" style="display: flex ;align-items: center">
                        <h1 style="padding: 10px;">Sliders  List</h1>
                        <div>
                            <a href="addslider" style="margin-left: 90px">Add Slider</a>
                            <a href="deleteslider" style="margin-left: 90px">Delete Slider</a>

                        </div>
                    </div>
                </header>
                <!-- <div class="box-header"> -->
                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                <!-- </div> -->
                <div class="panel-body table-responsive">
                    <div class="box-tools m-b-15" style="display: flex;">
                        <form action="sliderdetail" method="GET">
                            Status <select name="status1" onchange="searchStatus(status1.value);" class="input-sm">
                                <option value="-1" ${requestScope.cid1 == -1 ? "selected" : ""}>All</option>
                                <option value="1" ${requestScope.cid1 == 1 ? "selected" : ""}>Show</option>
                                <option value="0" ${requestScope.cid1 == 0 ? "selected" : ""}>Hide</option>
                            </select>

                        </form>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Image</th>
                            <th>Backlink</th>
                            <th>Status</th>
                            <th> </th>
                        </tr>
                        <c:forEach items="${requestScope.list}" var="s" varStatus="a" begin="0">
                            <tr>
                                <td onclick="doEdit(${s.id})">${a.index+1}</td>
                                <td onclick="doEdit(${s.id})">${s.title}</td>
                                <td onclick="doEdit(${s.id})"><img src="${s.image}" width="200px"></td>
                                <td onclick="doEdit(${s.id})"><a href="${s.backlink}">${s.backlink}</a></td>
                                <td>
                                    <c:if test="${s.status eq '0'}">
                                        Hide
                                    </c:if>
                                    <c:if test="${s.status eq '1'}">
                                        Show
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
