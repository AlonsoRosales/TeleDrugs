<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registro - TeleDrugs</title>

        <!-- Font Icon -->
        <link href="Login/fonts/material-icon/css/material-design-iconic-font.min.css" rel="stylesheet">

        <!-- Main css -->
        <link href="Login/css/styles.css" rel="stylesheet">
    </head>
    <body>

        <div class="main">
            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <% if (session.getAttribute("msg") != null) {%>
                    <div class="alert alert-success" role="alert" >
                        <%=(String) session.getAttribute("msg")%>
                    </div>
                    <% session.removeAttribute("msg"); %>
                    <% } %>
                    <% if (session.getAttribute("err") != null) {%>
                    <div class="alert alert-danger" role="alert" >
                        <%=(String) session.getAttribute("err")%>
                    </div>
                    <% session.removeAttribute("err"); %>
                    <% } %>
                    <div class="signup-content">
                        <div class="signup-form"  style="margin-right:10px; margin-left:10px;" >
                            <h2 style="text-align:center" class="form-title">Registrarse</h2>
                            <form method="POST" action="<%=request.getContextPath()%>/PaginaPrincipal?act=reg" class="register-form" id="register-form" >
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input style="width:400px;" type="text" name="Nombres" id="name" placeholder="Nombres"/>
                                </div>
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input style="width:400px;" type="text" name="Apellidos" id="lastname" placeholder="Apellidos"/>
                                </div>
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="dni"><i class="zmdi zmdi-assignment-account"></i></label>
                                    <input style="width:400px;" type="number" name="DNI" id="dni" placeholder="DNI"/>
                                </div>
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="birthday"><i class="zmdi zmdi-calendar"></i></label>
                                    <input style="width:400px;" type="date" name="FechaNacimiento" id="birthday" placeholder="Fecha de nacimiento"/>
                                </div>
                                <div class=" form-group" style="width:400px;height:30px;margin:auto">
                                    <!--label></label-->
                                    <!--input style="width:400px;" name="FechaNacimiento" placeholder="Distrito" disabled/-->
                                    <i class="zmdi zmdi-home" style="position: absolute" ></i>
                                    <select class="form-select form-select-sm" style="color:gray;width:400px;height:30px;border-top: none;border-left: none;border-right: none;text-indent: 30px; display:flex;align-items: center" name="Distrito">
                                        <option hidden selected>Distrito en el que reside</option>
                                        <option value="Ancón">Ancón</option>
                                        <option value="Ate">Ate</option>
                                        <option value="Barranco">Barranco</option>
                                        <option value="Breña">Breña</option>
                                        <option value="Carabayllo">Carabayllo</option>
                                        <option value="Chaclacayo">Chaclacayo</option>
                                        <option value="Chorrillos">Chorrillos</option>
                                        <option value="Cieneguilla">Cieneguilla</option>
                                        <option value="Comas">Comas</option>
                                        <option value="El Agustino">El Agustino</option>
                                        <option value="Independencia">Independencia</option>
                                        <option value="Jesus María">Jesus María</option>
                                        <option value="La Molina">La Molina</option>
                                        <option value="La Victoria">La Victoria</option>
                                        <option value="Lima">Lima</option>
                                        <option value="Lince">Lince</option>
                                        <option value="Los Olivos">Los Olivos</option>
                                        <option value="Lurigancho">Lurigancho</option>
                                        <option value="Lurín">Lurín</option>
                                        <option value="Magdalena del Mar">Magdalena del Mar</option>
                                        <option value="Miraflores">Miraflores</option>
                                        <option value="Pachacamac">Pachacamac</option>
                                        <option value="Pucusana">Pucusana</option>
                                        <option value="Pueblo Libre">Pueblo Libre</option>
                                        <option value="Puente Piedra">Puente Piedra</option>
                                        <option value="Punta Hermosa">Punta Hermosa</option>
                                        <option value="Punta Negra">Punta Negra</option>
                                        <option value="Rimac">Rimac</option>
                                        <option value="San Bartolo">San Bartolo</option>
                                        <option value="San Borja">San Borja</option>
                                        <option value="San Isidro">San Isidro</option>
                                        <option value="San Juan de Lurigancho">San Juan de Lurigancho</option>
                                        <option value="San Juan de Miraflores">San Juan de Miraflores</option>
                                        <option value="San Luis">San Luis</option>
                                        <option value="San Martin de Porres">San Martin de Porres</option>
                                        <option value="San Miguel">San Miguel</option>
                                        <option value="Santa Anita">Santa Anita</option>
                                        <option value="Santa María del Mar">Santa María del Mar</option>
                                        <option value="Santa Rosa">Santa Rosa</option>
                                        <option value="Santiago de Surco">Santiago de Surco</option>
                                        <option value="Surquillo">Surquillo</option>
                                        <option value="Villa el Salvador">Villa el Salvador</option>
                                        <option value="Villa María del Triunfo">Villa María del Triunfo</option>
                                    </select>
                                </div>
                                <!--div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="listadistrito"><i class="zmdi zmdi-home"></i></label>

                                    <input style="width:400px;" type="search" name="Distrito" list="listadistrito" placeholder="Distrito en el que reside">

                                    <datalist id="listadistrito">
                                        <option value="Ancón">Ancón</option>
                                        <option value="Ate">Ate</option>
                                        <option value="Barranco">Barranco</option>
                                        <option value="Breña">Breña</option>
                                        <option value="Carabayllo">Carabayllo</option>
                                        <option value="Chaclacayo">Chaclacayo</option>
                                        <option value="Chorrillos">Chorrillos</option>
                                        <option value="Cieneguilla">Cieneguilla</option>
                                        <option value="Comas">Comas</option>
                                        <option value="El Agustino">El Agustino</option>
                                        <option value="Independencia">Independencia</option>
                                        <option value="Jesus María">Jesus María</option>
                                        <option value="La Molina">La Molina</option>
                                        <option value="La Victoria">La Victoria</option>
                                        <option value="Lima">Lima</option>
                                        <option value="Lince">Lince</option>
                                        <option value="Los Olivos">Los Olivos</option>
                                        <option value="Lurigancho">Lurigancho</option>
                                        <option value="Lurín">Lurín</option>
                                        <option value="Magdalena del Mar">Magdalena del Mar</option>
                                        <option value="Miraflores">Miraflores</option>
                                        <option value="Pachacamac">Pachacamac</option>
                                        <option value="Pucusana">Pucusana</option>
                                        <option value="Pueblo Libre">Pueblo Libre</option>
                                        <option value="Puente Piedra">Puente Piedra</option>
                                        <option value="Punta Hermosa">Punta Hermosa</option>
                                        <option value="Punta Negra">Punta Negra</option>
                                        <option value="Rimac">Rimac</option>
                                        <option value="San Bartolo">San Bartolo</option>
                                        <option value="San Borja">San Borja</option>
                                        <option value="San Isidro">San Isidro</option>
                                        <option value="San Juan de Lurigancho">San Juan de Lurigancho</option>
                                        <option value="San Juan de Miraflores">San Juan de Miraflores</option>
                                        <option value="San Luis">San Luis</option>
                                        <option value="San Martin de Porres">San Martin de Porres</option>
                                        <option value="San Miguel">San Miguel</option>
                                        <option value="Santa Anita">Santa Anita</option>
                                        <option value="Santa María del Mar">Santa María del Mar</option>
                                        <option value="Santa Rosa">Santa Rosa</option>
                                        <option value="Santiago de Surco">Santiago de Surco</option>
                                        <option value="Surquillo">Surquillo</option>
                                        <option value="Villa el Salvador">Villa el Salvador</option>
                                        <option value="Villa María del Triunfo">Villa María del Triunfo</option>
                                    </datalist>

                                </div-->
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input style="width:400px;" type="email" name="Correo" id="email" placeholder="Correo electrónico"/>
                                </div>
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input style="width:400px;" type="password" name="Contrasena" id="pass" placeholder="Contraseña"/>
                                </div>
                                <div class="form-group" style="width:400px; margin:auto; margin-bottom:30px;">
                                    <label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input style="width:400px;" type="password" name="RePass" id="re_pass" placeholder="Repita su contraseña"/>
                                </div>
                                <!--<div class="form-group">
                                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                    <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                                </div>-->
                                <div class="form-group form-button">
                                    <div style="display:flex; align-items:center; justify-content:center;" class="column">
                                        <button class="btn btn-success" style="width:150px" type="submit">Registrar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image" style="margin-right:0px; margin-left:0px" >
                            <figure style="margin-bottom:40px; margin-top:40px; height:400px; width:400px;"><img src="Login/images/Cyborg-amico.png" alt="sing up image"></figure>
                            <a href="<%= request.getContextPath() %>" class="signup-image-link">Ya soy miembro</a>
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