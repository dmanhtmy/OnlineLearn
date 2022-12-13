<%-- 
    Document   : blogDetail
    Created on : Dec 4, 2022, 5:20:15 PM
    Author     : MrTuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Detail</title>
        <style>
            *, *::before, *::after {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .container {
                margin-top: 150px;
            }

            .container h2 {
                letter-spacing: 1px;
                font-size: 50px;
                color: #6968aa;
                border: 2px dashed #0181a0;
                padding: 10px;
                text-transform: uppercase;
                border-radius: 10px;
                display: inline-block;
                cursor: pointer;
                text-align: center;
                margin-top: 90px;
                margin-left: 375px;
            }

            .blog-post {
                /*width: 100%;*/
                max-width: 98rem;
                padding: 2rem;
                background-color: #dbf4ff21;
                box-shadow: 0 1.4rem 8rem rgba(0, 0, 0, 0.2);
                /*display: flex;*/
                align-items: center;
                border-radius: .8rem;
                margin: 10px;
            }
            .blog-post_info{
                transform: translateY(-5rem);
            }

            .blog-post_img {
                min-width: 30rem;
                max-width: 64rem;
                height: 35rem;
                transform: translateY(-8rem);
                position: relative;
            }

            .blog-post_img img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: .8rem;
                display: block;
            }

            .blog-post_img img::before {
                content: '';
                width: 100%;
                height: 100%;
                position: absolute;
                top: 0;
                left: 0;
                box-shadow: .5rem .5rem 3rem 1px rgba(0, 0, 0, 0.5);
                border-radius: .8rem;
            }

            .blog-post_date span {
                display: block;
                color: #00000080;
                font-weight: 600;
                margin: .5rem 0;
            }

            .blog-post_title {
                font-size: 2.5rem;
                margin: 1.5rem 0 2rem;
                text-transform: uppercase;
                color: #FF6600;
            }

            .blog-post_text {
                margin-bottom: 3rem;
                font-size: 1rem;
                color: #000000b3;
            }

            .blog-post_cta {
                display: inline-block;
                padding: 0.5rem 1rem;
                letter-spacing: 1px;
                font-size: 1.2rem;
                color: #fff;
                text-decoration: none;
                border-radius: .8rem;
                background: linear-gradient(to right, #FF6600 0%, #04a6bd 100%);
                cursor: pointer;
            }

            .blog-post_cta:hover {
                color: #FF6600;
                background: linear-gradient(to right, #04a6bd 0%, #FF6600 100%);
            }

            @media screen and (max-width: 1068px) {
                .blog-post {
                    max-width: 80rem;
                }
                .blog-post_img {
                    min-width: 30rem;
                    max-width: 64rem;
                }
                .container h2 {
                    margin-top: 120px;
                    margin-left: 275px;
                }
            }

            @media screen and (max-width: 868px) {
                .blog-post {
                    max-width: 70rem;
                }
                .container h2 {
                    margin-top: 20px;
                    margin-left: 142px;
                }
            }

            @media screen and (max-width: 768px) {
                .blog-post {
                    padding: 2.5rem;
                    flex-direction: column;
                }
                .blog-post_img {
                    min-width: 100%;
                    max-width: 100%;
                    transform: translate(0, -1rem);
                }
                .container {
                    margin-top: auto;
                }
            }

            @media screen and (max-width: 823px) {
                .container h2 {
                    margin-top: 35px;
                    margin-left: 142px;
                }
            }

        </style>
    </head>
    <%@include file="../components/header.jsp" %>
    <body>
        <div class="container">
            <div class="blog-post">
                <div class="blog-post_img">
                    <img src="${getdetail.getThumbnail()}"alt="">
                </div>
                <div class="blog-post_info">
                    <div class="blog-post_date">
                        <span>${getdetail.getPostdate()}</span>
                    </div>
                    <h1 class="blog-post_title">${getdetail.getTitle()}</h1>
                    <p class="blog-post_text">${getdetail.getBlogdetail()}</p>
                </div>
            </div>
        </div>
    </body>
    <%@include file="../components/footer.jsp" %>
</html>
