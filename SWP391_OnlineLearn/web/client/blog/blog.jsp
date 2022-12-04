<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive Blog Post </title>
        <style>
            *, *::before, *::after {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .container {
                margin-top: 100px;
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
                display: flex;
                align-items: center;
                border-radius: .8rem;
                margin: 10px;
            }

            .blog-post_img {
                min-width: 20rem;
                max-width: 20rem;
                height: 15rem;
                transform: translateX(-8rem);
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
                    max-width: 30rem;
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
            <c:forEach items="${requestScope.getAll}" var="blog">
                <div class="blog-post">
                    <div class="blog-post_img">
                        <img src="https://images.unsplash.com/photo-1612287230202-1ff1d85d1bdf?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTU3fHx0ZWNobm9sb2d5fGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                             alt="">
                    </div>
                    <div class="blog-post_info">
                        <h1 class="blog-post_title">${blog.getTitle()}</h1>
                        <div class="blog-post_date">
                            <span>${blog.getPostdate()}</span>
                        </div>
                        <p class="blog-post_text">${blog.getBrief_info()}</p>
                        <a href="" onclick="blogDetail(${blog.getId()})" class="blog-post_cta">Read More</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
    <%@include file="../components/footer.jsp" %>
</html>
<script>
    function blogDetail(id) {
        window.location.href = "<%= request.getContextPath()%>/home/blog?id=" + id;
    }
</script>


