<%-- 
    Document   : Login
    Created on : Nov 30, 2022, 2:15:28 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .login_container{
                margin: 100px auto;
                height: 400px;
                width: 500px;
                background-color:#c9eedc;

            }
            .username{
                height: 30px;
                width: 50%;
                margin: 20px auto;
                border: none;
                border-radius: 4px;
            }
            .input{
                display: flex;
                flex-direction: column;
                margin-top: 60px;
            }
            .button{
                margin: 30px 200px;
                height: 30px;
                width: 100px;

            }
            .login{
                border-bottom: black solid;
            }
            .resister{
                display: flex;
                justify-content: space-between;
            }
            .btn{
                margin-top: 10px;
                border: none;
            }
        </style>
    </head>
    <body>
        <div class="login_container">
            <form>
                <div class="login">
                    <div>
                        <h2>Login</h2>
                    </div>
                    <div class="input">
                        <input class="username" type="text" placeholder="User Name"/>
                        <input class="username" type="password" placeholder="Password"/>
                    </div>
                    <button class="button">
                        Login
                    </button>
                </div>
                <div class="resister">
                    <button class="btn">
                        Resister
                    </button>
                    <button class="btn">
                        ForgotPassword
                    </button>
                </div>


            </form>  

        </div>

    </body>
</html>
