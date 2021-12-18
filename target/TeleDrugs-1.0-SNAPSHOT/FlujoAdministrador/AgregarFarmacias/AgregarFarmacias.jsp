<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>TeleDrugs</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="FlujoAdministrador/css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Teledrugs</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    </ul>
                    <div class="dropdown">
                        <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class='bi bi-person-circle' style='font-size:15px'></i>
                            Usuario
                        </a>

                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li><a href="<%=request.getContextPath()%>/PaginaPrincipal?act=logout" class="dropdown-item" >Cerrar sesión</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Agregar Farmacia</h1>
                    <p class="lead fw-normal text-white-50 mb-0">Administrador <%=session.getAttribute("correo")%></p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <!--form-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
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
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-10 row-cols-xl-10 justify-content-center">
                    <div style="width:600px" class="col mb-5">
                        <div style="display:flex; justify-content:center; align-items:center; border-radius:10px; border: 2px solid #e1e7ec" class="card h-100">
                            <h1 style="margin-top:20px;"class="display-7 fw-bolder">Farmacia</h1>
                            <!-- Product image-->
                            <form method="POST" enctype="multipart/form-data" action="<%=request.getContextPath()%>/AgregarFarmacia">
                                <i class="bi bi-camera-fill"style='font-size:30px'></i>
                                <input type="file" name="Imagen" id="Foto" >
                                <div class="signup-image">
                                    <figure style="display:flex; justify-content:center; align-items:center; margin-top:20px; margin-bottom:0px"><img src="FlujoAdministrador/AgregarFarmacias/Imagenes/farmacia.jpg" alt="house image" width="230px" height="200px"></figure>
                                </div>
                                <br>
                                <input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="number" name="RUC" id="number" placeholder="RUC:">
                                <br>
                                <input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="text" name="Direccion" id="Dirección" placeholder="Dirección:">
                                <br>
                                <!--input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="search" name="Distrito" list="listadistritos" placeholder="Distrito">
                                <datalist id="listadistritos">
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
                                </datalist-->
                                <div class=" form-group" style="margin-top:15px;height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;margin-left:15px;">
                                    <!--div class=" form-group" style="width:400px;  margin:auto; margin-bottom:30px;"-->
                                    <!--label></label-->
                                    <!--input style="width:400px;" name="FechaNacimiento" placeholder="Distrito" disabled/-->
                                    <!--i class="zmdi zmdi-home"></i-->
                                    <!--input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="search" name="Distrito"  placeholder="Distrito"-->
                                    <select class="form-select form-select-sm" style="color:gray;width:400px;height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" name="Distrito">
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
                                <br>
                                <input class="m-3" style="height:30px;width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="text" name="Nombre" id="nombre" placeholder="Nombre de la Farmacia:">
                                <br>
                                <input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="email" name="Correo" id="email" placeholder="Correo:">
                                <br>
                                <input class="m-3" style="height:30px; width:400px; border-radius:7px; border: 1px solid #e1e7ec; outline:none;" type="password" name="Contrasena" id="contrasena" placeholder="Contraseña:">
                                <br>
                                <head>
                                    <!-- Modal -->
                                    <div class="modal" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalToggleLabel">Confirmación</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    ¿Estas seguro de que quiere agregar la Farmacia?
                                                </div>

                                                <div class="modal-footer">
                                                    <!--button class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button-->
                                                    <!--button class="btn btn-success" type="submit"  data-bs-dismiss="modal">Aceptar</button-->
                                                    <a class="btn btn-success" data-bs-dismiss="modal"  role="button">Cancelar</a>
                                                    <!--button class="btn btn-secondary" data-bs-dismiss="modal" >Cancelar</button-->
                                                    <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Aceptar</button>
                                                    <%//ESTO IBA EN LA LINEA DE ARRIBA onclick="alert('Se le ha enviado una confirmacion al correo electronico')">%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <a style="margin-bottom:20px; margin-top:0px; width:200px; float:left;" onclick="return eliminardatos()" class="btn btn-danger" href="<%=request.getContextPath()%>/AgregarFarmacia">Limpiar</a>
                                    <!--a style="margin-bottom:20px; margin-top:0px; width:200px; float:left;" class="btn btn-danger">Aceptar</a-->
                                    <a style="margin-bottom:20px; margin-top:0px; width:200px; float:right;" class="btn btn-success"  href="#exampleModalToggle" data-bs-toggle="modal" role="button">Aceptar</a>
                                    <!--button style="margin-bottom:20px; margin-top:0px; width:200px; float:right;" class="btn btn-success" href="#exampleModalToggle" data-bs-toggle="modal" >Aceptar</button-->
                                    <!--button class="btn btn-success" style="width:150px" data-bs-toggle="modal" type="submit">Aceptar</button-->
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <a style="margin-bottom:20px; margin-right:20px; margin-top:0px; width:200px; float: right;" class="btn btn-secondary" href="<%=request.getContextPath()%>/AdminPrincipal" data-toast="" data-toast-type="success" data-toast-position="topRight" data-toast-icon="icon-circle-check" data-toast-title="Your cart" data-toast-message="is updated successfully!" style="width:250px;">Regresar</a>
            <!-- <a href="javascript:history.back()"><img src="Imagenes/izquierda.jpg" height="48" width="48" style= "float:right" alt="Botón"</a> -->
            <!--/form-->
        </section>
        <!-- <div id="regresar"> -->

        <!--  </div> -->

        <!--/form-->
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; TeleDrugs 2021</p></div>
        </footer>

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>




