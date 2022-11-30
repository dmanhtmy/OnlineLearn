<%-- 
    Document   : dashboard
    Created on : Nov 30, 2022, 12:01:23 AM
    Author     : Mr Tuan
--%>
<%@include file="../layout/layout.jsp" %>
<main>
    <ul class="box-info">
        <li>
            <i class='bx bxs-calendar-check' ></i>
            <span class="text">
                <h3>1020</h3>
                <p>Users</p>
            </span>
        </li>
        <li>
            <i class='bx bxs-group' ></i>
            <span class="text">
                <h3>2834</h3>
                <p>Categories</p>
            </span>
        </li>
        <li>
            <i class='bx bxs-dollar-circle' ></i>
            <span class="text">
                <h3>$2543</h3>
                <p>Courses</p>
            </span>
        </li>
    </ul>
</main>
<style>
    main{
        text-align:center;
    }
    .box-info{
        display: flex;
        margin-left: auto;
    }
    .box-info li{
        text-align: center;
    }
</style>

