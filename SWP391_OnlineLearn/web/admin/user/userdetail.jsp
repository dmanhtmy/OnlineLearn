<%-- 
    Document   : userdetail
    Created on : Dec 4, 2022, 12:59:26 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../layout/layout.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .container{
                padding: 10px;
                display: flex;
                flex-direction: column;
            }
            .input-container{
                display: flex;
                flex-direction: row;
                padding: 15px;
                justify-content: center;
            }
            .input{
                width: 50%;
                height: 30px;
                margin-left: 20px;
            }
            .input1{
                width: 21%;
            }
            p{
                width: 100px;
            }
            .button{
                width: 10%;
                margin: auto;

            }
        </style>
    </head>
    <body>
        <div >
            <form class="container" action="userdetail" method="Post">
                <input class="input" type="text" value="${requestScope.user.id}" name="id" hidden="" />
                <div class="input-container">
                    <p>UserName</p>
                    <input class="input" type="text" value="${requestScope.user.username}" name="username" />
                </div>
                <div class="input-container">
                    <p>Full Name</p>
                    <input class="input" type="text" value="${requestScope.user.fullname}" name="fullname" />
                </div>
                <div class="input-container">
                    <p>Gender</p>
                    Male   <input class="input1" type="radio" value="true" name="gender" <c:if test = "${requestScope.user.gender == true}">
                                  checked=""
                        </c:if> />

                    Female  <input class="input1" type="radio" value="false" name="gender"<c:if test = "${requestScope.user.gender == false}">
                                   checked=""
                        </c:if>     />
                </div>
                <div class="input-container">
                    <p>Address</p>
                    <input class="input" type="text" value="${requestScope.user.address}" name="address"/>
                </div>
                <div class="input-container">
                    <p>Email</p>
                    <input class="input" type="text" value="${requestScope.user.email}" name="email"/>
                </div>
                <div class="input-container">
                    <p>Mobiphone</p>
                    <input class="input" type="text" value="${requestScope.user.phonenumber}" name="phone"/>
                </div>
                <div class="input-container">
                    <p>Status</p>
                    Active   <input class="input1" type="radio" value="1" name="status" <c:if test = "${requestScope.user.status == 1}">
                                    checked=""
                        </c:if> />

                    Deactive  <input class="input1" type="radio" value="2" name="status"<c:if test = "${requestScope.user.status == 2}">
                                     checked=""
                        </c:if>     />
                </div>
                <input type="submit" value="Save" class="button" />
            </form>
        </div>

    </body>
</html>
