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
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
        </symbol>
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
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
    <div class="position-fixed w-100" id="alert-div">
        <c:if test="${status.equals('true')}">
            <button class="alert alert-success d-flex align-items-center position-absolute ms-3 pe-auto" id="alert" role="alert" onclick="closeAlertModal()">
                <svg id="suc" class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                <div>
                    Successful!
                </div>
            </button>
        </c:if>
        <c:if test="${status.equals('false')}">
            <button class="alert alert-danger d-flex align-items-center position-absolute ms-3 pe-auto" id="alert" role="alert" onclick="closeAlertModal()">
                <svg id="fa" class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Failed!
                </div>
            </button>
        </c:if>
    </div>
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
    .fadeOutLeft{
        animation: fadeOutLeft 0.3s ease-in;
        animation-fill-mode: forwards;
    }
    @keyframes fadeOutLeft {
        0% {
            opacity: 1;
            transform: translateX(0);
        }
        50%{
            opacity: 1;
            transform: skewX(-5deg);
        }
        75%{
            opacity: 1;
            transform: skewX(5deg);
        }
        100% {
            opacity: 0;
            transform: translateX(-100%);
        }
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
    function closeAlertModal() {
        let modal = document.getElementById("alert");
        modal.classList.add("fadeOutLeft");
        console.log(modal);
    }
</script>
