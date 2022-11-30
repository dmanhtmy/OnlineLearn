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
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>

        <main>
            <div class="box">
                <div class="inner-box">
                    <div class="forms-wrap">
                        <form action="login" autocomplete="off" class="sign-in-form" method="post">
                            <div class="logo">
                                <img src="../homepage/css/logo.png" alt="easyclass" />&emsp;
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