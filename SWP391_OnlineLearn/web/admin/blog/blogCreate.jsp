<%-- 
    Document   : blogCreate
    Created on : Dec 6, 2022, 11:27:43 PM
    Author     : MrTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<main>
    <form action="/admin/blog/create" method="POST" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">*Title</span>
            <input type="text" class="form-control" placeholder="title..." aria-label="Username" aria-describedby="basic-addon1" title="title" name="title">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Brief Infor</span>
            <input type="text" class="form-control" placeholder="brief infor..." aria-label="Recipient's username" aria-describedby="basic-addon2" name="brief">
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon3">Author</span>
            <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3" value="${sessionScope.user.getUsername()}" readonly="true" disabled="true">
        </div>
        <div class="input-group mb-3" id="div-image-profile">
            <label class="input-group-text" for="inputGroupFile01">Upload Image</label>
            <input type="file" class="form-control" id="file" name="image"accept="image/png,image/jpeg" multiple="multiple">
        </div>
            <img id="photo" class= "img-profile" src="">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Category</span>
            <div class="col-sm-8">
                <select name="category" required="required" style="height: 40px;">
                    <c:forEach items="${requestScope.listCategory}" var="ca" >
                        <option value="${ca.getId()}">${ca.getName()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="input-group">
            <span class="input-group-text">Content</span>
            <textarea class="form-control" aria-label="With textarea" style="height: 200px;" name="content"></textarea>
        </div>
        <div class="button-submit" style="text-align: center;margin-top: 50px;"><button type="submit" class="btn btn-primary" >Create Blog</button></div>
    </form>
</main>
</section>
</body>
<script>
    const imgDiv = document.querySelector('#div-image-profile');
    const img = document.querySelector('#photo');
    const file = document.querySelector('#file');

    file.addEventListener('change', function () {
        const choosedFile = this.files[0];

        if (choosedFile) {

            const reader = new FileReader();

            reader.addEventListener('load', function () {
                img.setAttribute('src', reader.result);
            });

            reader.readAsDataURL(choosedFile);
        }
    });
</script>
<style>
    .div-image-profile{
        display: block;
        margin: auto;
        overflow: hidden;
    }
    .img-profile{
        width: 200px;
        height: auto;
        cursor: zoom-in;
        background-color: lightgray;
    }
</style>
