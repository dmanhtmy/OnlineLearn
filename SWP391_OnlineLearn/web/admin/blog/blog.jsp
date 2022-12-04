<%-- 
    Document   : blog
    Created on : Dec 4, 2022, 8:45:11 PM
    Author     : MrTuan
--%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<main>
    <ul class="box-info-blog">
        <table class="table">
            <button type="button" class="btn btn-primary" style="background-color: #3C91E6;" onclick="createBlog()">Create Blog</button>
            <form id="form" action="blogs" method="GET">
                <div class="form-input">
                    <input name="search" type="text" placeholder="Search...">
                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Blog</th>
                    <th scope="col">#</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="i" value="0"></c:set>
                <c:forEach items="${requestScope.blogs}" var="blog">
                    <c:set var="i" value="${i+1}"></c:set>
                        <tr>
                            <th scope="row">${i}</th>
                        <td>${blog.getTitle()}</td>
                        <td>
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${blog.getId()}">Delete</button>
                            <button type="button" class="btn btn-info" onclick="updateBlog(${blog.getId()})">Update</button>
                        </td>
                    </tr>
                <div class="modal fade" id="exampleModal${blog.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"  id="exampleModalLabel">Delete Blog</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Do you want delete '${blog.getTitle()}' ?
                            </div>
                            <div class="modal-footer">
                                <button  type="submit" class="btn btn-danger" onclick="deleteBlog(${blog.getId()})" name="id" value="${blog.getId()}">Delete</button>
                                <button type="submit" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </tbody>
        </table>
    </ul>
    <div class="paging">
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${endpage}" var="p">
                <li class="page-item"><a class="page-link" href="<%= request.getContextPath()%>/admin/blogs?page=${p}">${p}</a></li>
                </c:forEach>
        </ul>
    </div>
</main>
</section>
</body>
<style>
    .form-input {
        transform: translateY(-2.2rem);
        margin-left: 500px;
        display: flex;
        text-align: right;
        height: 36px;
    }
    .form-input input {
        padding: 0 16px;
        height: 100%;
        border: none;
        background: var(--light);
        border-radius: 36px 0 0 36px;
        outline: none;
        width: 30%;
        color: var(--dark);
    }
    .form-input button {
        width: 36px;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background: var(--blue);
        color: var(--light);
        font-size: 18px;
        border: none;
        outline: none;
        border-radius: 0 36px 36px 0;
        cursor: pointer;
    }
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
    function createBlog() {
        window.location.href = "<%=request.getContextPath()%>/admin/blog/create";
    }
    function updateBlog(id) {
        window.location.href = "<%=request.getContextPath()%>/admin/blog/update?id=" + id;
    }
    function deleteBlog(id) {
        window.location.href = "<%=request.getContextPath()%>/admin/blog/delete?id=" + id;
    }

</script>
