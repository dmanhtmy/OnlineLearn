<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Sign in & Sign up Form</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
                integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>
        <style>
            /*
        Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
        Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/CascadeStyleSheet.css to edit this template
            */
            /* 
                Created on : Nov 30, 2022, 10:44:08 AM
                Author     : hp
            */
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800&display=swap");

            *,
            *::before,
            *::after {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            body,
            input {
                font-family: serif;
            }

            main {
                width: 100%;
                min-height: 100vh;
                overflow: hidden;
                background-color: #ff8c6b;
                padding: 2rem;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: linear-gradient(rgba(0,0,0,0.4),rgba(0,0,0,0.4)),url(../..//homepage/css/fu.jpg);
                background-position: center;
                background-size: cover;
                position: absolute;
            }

            .box {
                position: relative;
                width: 100%;
                max-width: 1400px;
                height: 570px;
                background-color: #fff;
                border-radius: 3.3rem;
                box-shadow: 0 60px 40px -30px rgba(0, 0, 0, 0.27);
            }

            .inner-box {
                position: absolute;
                width: calc(100% - 4.1rem);
                height: calc(100% - 4.1rem);
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }

            .forms-wrap {
                position: absolute;
                height: 100%;
                width: 40%;
                top: 0;
                left: 0;
                display: grid;
                grid-template-columns: 1fr;
                grid-template-rows: 1fr;
                transition: 0.8s ease-in-out;
            }

            form {
                max-width: 280px;
                width: 100%;
                margin: 0 auto;
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: space-evenly;
                grid-column: 1 / 2;
                grid-row: 1 / 2;
                transition: opacity 0.02s 0.4s;
            }

            form.sign-up-form {
                opacity: 0;
                pointer-events: none;
            }

            .logo {
                display: flex;
                align-items: center;
            }

            .logo img {
                width: 100px;
                margin-right: 0.3rem;
            }

            .logo h4 {
                font-size: 1.1rem;
                margin-top: -9px;
                letter-spacing: -0.5px;
                color: #151111;
            }

            .heading h2 {
                font-size: 2.1rem;
                font-weight: 600;
                color: #ff6600;
            }

            .heading h6 {
                color: #bababa;
                font-weight: 400;
                font-size: 0.75rem;
                display: inline;
            }

            .toggle {
                color: #ff6600;
                text-decoration: none;
                font-size: 0.75rem;
                font-weight: 500;
                transition: 0.3s;
            }

            .toggle:hover {
                color: #000;
            }

            .input-wrap {
                position: relative;
                height: 37px;
                margin-bottom: 2rem;
            }

            .input-field {
                position: absolute;
                width: 100%;
                height: 100%;
                background: none;
                border: none;
                outline: none;
                border-bottom: 1px solid #bbb;
                padding: 0;
                font-size: 0.95rem;
                color: #151111;
                transition: 0.4s;
            }

            label {
                position: absolute;
                left: 0;
                top: 50%;
                transform: translateY(-50%);
                font-size: 0.95rem;
                color: #bbb;
                pointer-events: none;
                transition: 0.4s;
            }

            .input-field.active {
                border-bottom-color: #151111;
            }

            .input-field.active + label {
                font-size: 0.75rem;
                top: -2px;
            }

            .sign-btn {
                display: inline-block;
                width: 100%;
                height: 43px;
                background-color: #151111;
                color: #fff;
                border: none;
                cursor: pointer;
                border-radius: 0.8rem;
                font-size: 0.8rem;
                margin-bottom: 2rem;
                transition: 0.3s;
            }

            .sign-btn:hover {
                background-color: #8371fd;
            }

            .text {
                color: #bbb;
                font-size: 0.7rem;
            }

            .text a {
                color: #ff6600;
                transition: 0.3s;
            }

            .text a:hover {
                color: #000;
            }

            main.sign-up-mode form.sign-in-form {
                opacity: 0;
                pointer-events: none;
            }

            main.sign-up-mode form.sign-up-form {
                opacity: 1;
                pointer-events: all;
            }

            main.sign-up-mode .forms-wrap {
                left: 55%;
            }

            main.sign-up-mode .carousel {
                left: 0%;
            }

            .carousel {
                position: absolute;
                /*height: 100%;*/
                width: 55%;
                left: 45%;
                top: 0;
                background-color: #ffe0d2;
                border-radius: 2rem;
                display: grid;
                grid-template-rows: auto 1fr;
                /*padding-bottom: 2rem;*/
                overflow: hidden;
                transition: 0.8s ease-in-out;
            }

            .images-wrapper {
                display: grid;
                grid-template-columns: 1fr;
                grid-template-rows: 1fr;
            }

            .image {
                width: 100%;
                grid-column: 1/2;
                grid-row: 1/2;
                opacity: 0;
                transition: opacity 0.3s, transform 0.5s;
            }


            .image.show {
                opacity: 1;
                transform: none;
            }

            .text-slider {
                display: flex;
                align-items: center;
                justify-content: center;
                flex-direction: column;
            }

            .text-wrap {
                max-height: 2.2rem;
                overflow: hidden;
                margin-bottom: 2.5rem;
            }

            .text-group {
                display: flex;
                flex-direction: column;
                text-align: center;
                transform: translateY(0);
                transition: 0.5s;
            }

            .text-group h2 {
                line-height: 2.2rem;
                font-weight: 600;
                font-size: 1.6rem;
            }

            .bullets {
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .bullets span {
                display: block;
                width: 0.5rem;
                height: 0.5rem;
                background-color: #aaa;
                margin: 0 0.25rem;
                border-radius: 50%;
                cursor: pointer;
                transition: 0.3s;
            }

            .bullets span.active {
                width: 1.1rem;
                background-color: #151111;
                border-radius: 1rem;
            }

            @media (max-width: 850px) {
                .box {
                    height: auto;
                    max-width: 550px;
                    overflow: hidden;
                }

                .inner-box {
                    position: static;
                    transform: none;
                    width: revert;
                    height: revert;
                    padding: 2rem;
                }

                .forms-wrap {
                    position: revert;
                    width: 100%;
                    height: auto;
                }

                form {
                    max-width: revert;
                    padding: 1.5rem 2.5rem 2rem;
                    transition: transform 0.8s ease-in-out, opacity 0.45s linear;
                }

                .heading {
                    margin: 2rem 0;
                }

                form.sign-up-form {
                    transform: translateX(100%);
                }

                main.sign-up-mode form.sign-in-form {
                    transform: translateX(-100%);
                }

                main.sign-up-mode form.sign-up-form {
                    transform: translateX(0%);
                }

                .carousel {
                    position: revert;
                    height: auto;
                    width: 100%;
                    padding: 3rem 2rem;
                    display: flex;
                }

                .images-wrapper {
                    display: none;
                }

                .text-slider {
                    width: 100%;
                }
            }

            @media (max-width: 530px) {
                main {
                    padding: 1rem;
                }

                .box {
                    border-radius: 2rem;
                }

                .inner-box {
                    padding: 1rem;
                }

                .carousel {
                    padding: 1.5rem 1rem;
                    border-radius: 1.6rem;
                }

                .text-wrap {
                    margin-bottom: 1rem;
                }

                .text-group h2 {
                    font-size: 1.2rem;
                }

                form {
                    padding: 1rem 2rem 1.5rem;
                }
            }


            .slider{
                width: 800px;
                height: 500px;
                border-radius: 10px;
                overflow: hidden;
            }

            .slides{
                width: 500%;
                height: 500px;
                display: flex;
            }

            .slides input{
                display: none;
            }

            .slide{
                width: 20%;
                transition: 2s;
            }

            .slide img{
                width: 800px;
                height: 500px;
            }

            /*css for manual slide navigation*/

            .navigation-manual{
                position: absolute;
                width: 800px;
                margin-top: -40px;
                display: none;
                justify-content: center;
            }

            .manual-btn{
                border: 2px solid #40D3DC;
                padding: 5px;
                border-radius: 10px;
                cursor: pointer;
                transition: 1s;
            }

            .manual-btn:not(:last-child){
                margin-right: 40px;
            }

            .manual-btn:hover{
                background: #40D3DC;
            }

            #radio1:checked ~ .first{
                margin-left: 0;
            }

            #radio2:checked ~ .first{
                margin-left: -20%;
            }

            #radio3:checked ~ .first{
                margin-left: -40%;
            }

            #radio4:checked ~ .first{
                margin-left: -60%;
            }

            /*css for automatic navigation*/

            .navigation-auto{
                position: absolute;
                display: flex;
                width: 800px;
                justify-content: center;
                margin-top: 460px;
            }

            .navigation-auto div{
                border: 2px solid #40D3DC;
                padding: 5px;
                border-radius: 10px;
                transition: 1s;
            }

            .navigation-auto div:not(:last-child){
                margin-right: 40px;
            }

            #radio1:checked ~ .navigation-auto .auto-btn1{
                background: #40D3DC;
            }

            #radio2:checked ~ .navigation-auto .auto-btn2{
                background: #40D3DC;
            }

            #radio3:checked ~ .navigation-auto .auto-btn3{
                background: #40D3DC;
            }

            #radio4:checked ~ .navigation-auto .auto-btn4{
                background: #40D3DC;
            }

            /*CSS login google*/
            .login-google{
                text-align: center;
            }
            .btn-google {
                color: #545454;
                background-color: #ffffff;
                box-shadow: 0 1px 2px 1px #ddd
            }
            .line-separator {
                background-color: #ccc;
                flex-grow: 5;
                height: 1px;
                width: auto;

            }
            .or-label {
                flex-grow: 1;
                margin: 0 15px;
                text-align: center
            }
            .or-container {
                align-items: center;
                color: #ccc;
                display: flex;
                margin-bottom: 20px;
            }

        </style>
    </head>
    <body>

        <main>
            <div class="box">
                <div class="inner-box">
                    <div class="forms-wrap">
                        <form action="login" autocomplete="off" class="sign-in-form" method="post">
                            <div class="logo">
                                 <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668565240/Home/logo/logo_ocndyv.png" alt="easyclass" />&emsp;
                                <h4 style="color: #ff6600;">FPT &nbsp;<span style="color: #000;">Accommodation</span></h4>
                            </div>

                            <div class="heading">
                                <h2>Welcome Back</h2>
                                <h6>Not registred yet?</h6>
                                <a href="#" class="toggle">Sign up</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input type="text" minlength="4" class="input-field" name="username" autocomplete="off" required/>
                                    <label>Name</label>
                                </div>

                                <div class="input-wrap">
                                    <input type="password" minlength="4" class="input-field" name="password" autocomplete="off" required/>
                                    <label>Password</label>
                                </div>

                                <input type="submit" value="Sign In" class="sign-btn" />
                                <div class="login-google">
                                    <div class="or-container">
                                        <div class="line-separator"></div>
                                        <div class="or-label">or</div>
                                        <div class="line-separator"></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12"> <a class="btn btn-lg btn-google btn-block btn-outline" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/SWP391-SE1623-JS-G6/logingoogle&response_type=code&client_id=1086381055681-0upu0i0nmp2v352u8b46hplsd8hpicc8.apps.googleusercontent.com&approval_prompt=force"><img src="https://img.icons8.com/color/20/000000/google-logo.png"> Sign In With Google</a> </div>
                                    </div> <br>
                                </div>
                                <p class="text">
                                    Forgotten your password or you login datails?
                                    <a href="#">Get help</a> signing in
                                </p>
                            </div>
                        </form>

                        <form action="regiter" autocomplete="off" class="sign-up-form" method="post">
                            <div class="logo">
                                <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668565240/Home/logo/logo_ocndyv.png" alt="easyclass" />&emsp;
                                <h4 style="color: #ff6600;">FPT &nbsp;<span style="color: #000;">Accommodation</span></h4>
                            </div>

                            <div class="heading">
                                <h2>Get Started</h2>
                                <h6>Already have an account?</h6>
                                <a href="#" class="toggle">Sign in</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input type="text" minlength="4" class="input-field" name="username" autocomplete="off" required/>
                                    <label>Name</label>
                                </div>

                                <div class="input-wrap">
                                    <input type="email" class="input-field" name="email" autocomplete="off" required />
                                    <label>Email</label>
                                </div>

                                <div class="input-wrap">
                                    <input type="password" minlength="4" class="input-field" name="password" autocomplete="off" required />
                                    <label>Password</label>
                                </div>

                                <input type="submit" value="Sign Up" class="sign-btn" />

                                <p class="text">
                                    By signing up, I agree to the
                                    <a href="#">Terms of Services</a> and
                                    <a href="#">Privacy Policy</a>
                                </p>
                            </div>
                        </form>
                    </div>
                    <script type="text/javascript">
                        var counter = 1;
                        setInterval(function () {
                            document.getElementById('radio' + counter).checked = true;
                            counter++;
                            if (counter > 4) {
                                counter = 1;
                            }
                        }, 5000);
                    </script>
                    <div class="carousel">
                        <!--image slider start-->
                        <div class="slider">
                            <div class="slides">
                                <!--radio buttons start-->
                                <input type="radio" name="radio-btn" id="radio1">
                                <input type="radio" name="radio-btn" id="radio2">
                                <input type="radio" name="radio-btn" id="radio3">
                                <input type="radio" name="radio-btn" id="radio4">
                                <!--radio buttons end-->
                                <!--slide images start-->
                                <div class="slide first">
                                    <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668544609/Home/homepage/dhfpt_tkbry2.jpg" alt="">
                                </div>
                                <div class="slide">
                                    <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668565410/Home/homepage/fpt-uni_eeqgh8.jpg" alt="">
                                </div>
                                <div class="slide">
                                    <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668565332/Home/homepage/fu_vj7koe.jpg" alt="">
                                </div>
                                <div class="slide">
                                    <img src="https://res.cloudinary.com/ddrjnfihc/image/upload/v1668565331/Home/homepage/fu_3_peez8o.jpg" alt="">
                                </div>
                                <!--slide images end-->
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </main>

        <!-- Javascript file -->

        <script>
            const inputs = document.querySelectorAll(".input-field");
            const toggle_btn = document.querySelectorAll(".toggle");
            const main = document.querySelector("main");
            const bullets = document.querySelectorAll(".bullets span");
            const images = document.querySelectorAll(".image");

            inputs.forEach((inp) => {
                inp.addEventListener("focus", () => {
                    inp.classList.add("active");
                });
                inp.addEventListener("blur", () => {
                    if (inp.value !== "")
                        return;
                    inp.classList.remove("active");
                });
            });

            toggle_btn.forEach((btn) => {
                btn.addEventListener("click", () => {
                    main.classList.toggle("sign-up-mode");
                });
            });
        </script>

    </body>
</html>

