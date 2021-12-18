<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--jsp:useBean type="java.lang.String" scope="session" id="msg" class="java.lang.String"/-->

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recuperar contraseña - TeleDrugs</title>

    <!-- Font Icon -->
    <link href="Login/fonts/material-icon/css/material-design-iconic-font.min.css" rel="stylesheet" >

    <!-- Main css -->
    <link href="Login/css/styles.css" rel="stylesheet" >
</head>
<body>

    <div class="main">
        <!-- Recover password -->
        <section class="signup">
            <div class="container">
                <% if (session.getAttribute("err") != null) {%>
                <div class="alert alert-danger" role="alert" >
                    <%=(String) session.getAttribute("err")%>
                </div>
                <% session.removeAttribute("err"); %>
                <% } %>
                <% if (session.getAttribute("msg") != null) {%>
                <div class="alert alert-success" role="alert" >
                    <%=(String) session.getAttribute("msg")%>
                </div>
                <% session.removeAttribute("msg"); %>
                <% } %>
                <div class="signup-content" >
                    <div class="signup-form">
                        <h2 style="text-align:center;" class="form-title">Recuperar contraseña</h2>
                        <form method="POST" action="<%=request.getContextPath()%>/RecuperarContrasena" class="register-form" id="register-form">
                            <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input style="width:400px;" type="email" name="Correo" id="email" placeholder="Correo electrónico"/>
                            </div>
                            <div style="width:400px; margin:auto; margin-bottom:30px;" class="form-group">
                                <label for="dni"><i class="zmdi zmdi-assignment-account"></i></label>
                                <input type="number" name="DNI" id="dni" placeholder="DNI" />
                            </div>

                            <div class="form-group form-button">
                                <!--div style="display:flex; align-items:center; justify-content:center;" class="column"><a style="width:150px" class="btn btn-success" data-bs-toggle="modal" href="#exampleModalToggle" onclick="alert('Se ha enviado el codigo de recuperacion a su correo')">Recuperar</a></div-->
                                <div style="display:flex; align-items:center; justify-content:center;" class="column"><button  type="submit" style="width:150px" class="btn btn-success" data-bs-toggle="modal" href="#exampleModalToggle" onclick="alert('Validando datos ingresados')">Recuperar</button></div>
                                <!--div style="display:flex; align-items:center; justify-content:center;" class="column"><button  type="submit" style="width:150px" class="btn btn-success">Recuperar</button></div-->
                            </div>
                        </form>
                    </div>
                    <div class="signup-image" style="margin-right:0px;">
                        <figure style="margin-bottom:0px; margin-right:0px; margin-top:0px;"><img width="350px" height="250px" src="Login/images/Forgot password-bro.png" alt="recover image"></figure>
                        <a href="<%= request.getContextPath() %>" class="signup-image-link" style="margin-top:20px;">Volver inicio de sesión</a>
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