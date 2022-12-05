<%-- 
    Document   : dashboard
    Created on : Nov 30, 2022, 12:01:23 AM
    Author     : Mr Tuan
--%>
<%@include file="../layout/layout.jsp" %>
<main>
    <ul class="box-info">
        <li>
            <i class='bx bxs-user' ></i>
            <span class="text">
                <h3>${requestScope.users}</h3>
                <p>Total Users</p>
            </span>
        </li>
        <li>
            <i class='bx bxl-blogger' ></i>
            <span class="text">
                <h3>${requestScope.blogs}</h3>
                <p>Total Blogs</p>
            </span>
        </li>
        <li>
            <i class='bx bxs-book' ></i>
            <span class="text">
                <h3>${requestScope.courses}</h3>
                <p>Total Courses</p>
            </span>
        </li>
    </ul>
</main>
</section>
</body>

