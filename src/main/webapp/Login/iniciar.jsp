<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Iniciar sesión</title>

    <!-- Font Icon -->
    <link  href="Login/fonts/material-icon/css/material-design-iconic-font.min.css"rel="stylesheet">

    <!-- Main css -->
    <link  href="Login/css/style.css" rel="stylesheet">
</head>
<body>

    <div class="main">
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <br>
                        <figure style="margin-bottom:0px;"><img src="Login/images/login.png" alt="sing up image"></figure>
                        <a href="<%= request.getContextPath() %>/Registro" class="signup-image-link">Crear una cuenta</a>
                    </div>
                    <div class="signin-form">
                        <h2 style="text-align:center;" class="form-title">Iniciar sesión TeleDrugs</h2>
                        <form method="POST" action="<%=request.getContextPath()%>/PaginaPrincipal" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="correo"><i class="zmdi zmdi-email"></i></label>
                                <input type="correo" name="correo" id="correo" placeholder="Correo electrónico"/>
                            </div>
                            <div class="form-group">
                                <label for="contrasenia"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="constrasenia" id="contrasenia" placeholder="Contraseña"/>
                            </div>
                            <div class="form-group">
                                <br>
                                <br>
                                <a href="<%= request.getContextPath() %>/RecuperarContrasena" class="signup-image-link">¿Has olvidado tu contraseña?</a>
                            </div>
                            <div class="form-group form-button">
                                <div style="display:flex; align-items:center; justify-content:center;" class="column">
                                    <button class="btn btn-success" style="width:150px" type="submit">Ingresar</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>