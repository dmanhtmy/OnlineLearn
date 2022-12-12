<%-- 
    Document   : adsUpdate
    Created on : Dec 11, 2022, 8:56:17 PM
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
    <form action="/admin/ads/update" method="POST" enctype="multipart/form-data">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Name Brand</span>
            <input type="text" class="form-control" placeholder="name..." aria-label="Username" aria-describedby="basic-addon1" title="title" name="brand" value="${ads.getName_brand()}">
        </div>
        <div class="input-group mb-3" id="div-image-profile">
            <label class="input-group-text" for="inputGroupFile01">Upload Image</label>
            <input type="file" class="form-control" id="file" name="image"accept="image/png,image/jpeg" multiple="multiple">
        </div>
        <img id="photo" class= "img-profile" src="${ads.getImage()}">
        <div class="input-group mb-3" style="margin-top: 1rem!important;">
            <span class="input-group-text" id="basic-addon2">Link</span>
            <input type="text" class="form-control" placeholder="link infor..." aria-label="Recipient's username" aria-describedby="basic-addon2" name="link" value="${ads.getHref()}">
        </div><br>
        <div class="button-submit" style="text-align: center;margin-top: 50px;"><button type="submit" class="btn btn-primary" name="id" value="${ads.getId()}">Save</button></div>
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


