<%-- 
    Document   : blogs
    Created on : Dec 1, 2022, 11:27:11 PM
    Author     : MrTuan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<main>
    <ul class="box-info-blog">
        <c:forEach items="${requestScope.getAll}" var="blog">
            <li class="item-blog" onclick="getDetail(${blog.getId()})">
                <span class="text">
                    <h3>${blog.getTitle()}</h3>
                    <p class="text-bi">${blog.getBrief_info()}</p>
                    <p class="text-pd">${blog.getPostdate()}</p>
                </span>
            </li>
        </c:forEach>
    </ul>
</main>
</section>
</body>
<style>
    .box-info-blog{
        display: block;
        margin-top: 30px;
    }
    .box-info-blog .item-blog{
        padding: 24px;
        background: var(--light);
        border-radius: 20px;
        align-items: center;
        margin-top: 24px;
        cursor: pointer;
    }
    .text-bi{
        font-family: sans-serif;
    }
    .text-pd{
        margin-top: 15px;
        color: #bbb;
        font-size: 15px;
        font-family: initial;
    }
</style>
<script>
    function getDetail(id) {
        window.location.href = "<%=request.getContextPath()%>/admin/blog/blogdetail?id=" + id;
    }
</script>