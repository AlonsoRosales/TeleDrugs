<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BCliente" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.DTO.DTOCarritoCliente" %>
<!DOCTYPE html>
<%ArrayList<DTOCarritoCliente> listaCarrito = session.getAttribute("carrito") != null ? (ArrayList) session.getAttribute("carrito") : new ArrayList<>();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%BCliente bCliente = (BCliente) session.getAttribute("usuario");%>
<%BFarmacia bFarmacia2 = (BFarmacia) session.getAttribute("farmacia");%>
<%ArrayList<BFarmacia> listafarmacias = (ArrayList) session.getAttribute("listafarmacias");%>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Producto</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="FlujoUsuario/css/styles.css" rel="stylesheet" />

</head>
<body>
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="#!">Teledrugs</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <li class="nav-item"><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/Usuario">Pagina principal</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Usuario?opcion=historialPedidos">Estado de pedido</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Farmacias</a>
                        <ul class="dropdown-menu" style="height: 200px;width: 300px;" aria-labelledby="navbarDropdown">
                            <div style="text-align: center; margin-top: 20px;"><h4 class="form-title">ELEGIR FARMACIA</h4></div>
                            <div class="modal-body">
                                <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=mostrarFarmacia" class= "register-form">
                                    <div style="margin-bottom: 20px; display: flex; justify-content: center;" class="form-group">
                                        <select class="form-control form-select-sm" name="ruc">
                                            <%for (BFarmacia bFarmacia : listafarmacias){%>
                                            <option value="<%=bFarmacia.getRuc()%>"><%=bFarmacia.getNombre()%> - <%=bFarmacia.getDistrito()%> - <%=bFarmacia.getDireccion()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="form-group form-button">
                                        <div style="margin-top:5px; text-align: center;"><button type="submit" class="btn btn-success">Continuar</button></div>
                                    </div>
                                </form>
                            </div>
                        </ul>
                    </li>
                </ul>
                    <div class="dropdown">
                      <a class="btn btn-outline-dark dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                         <i class='bi bi-person-circle' style='font-size:15px'></i>
                         Usuario
                      </a>

                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                          <li><a href="<%= request.getContextPath()%>/Usuario?opcion=mostrarPerfil" class="dropdown-item" >Ver perfil</a></li>
                          <li><a href="<%= request.getContextPath()%>/Usuario?opcion=logout" class="dropdown-item" >Cerrar sesión</a></li>
                      </ul>
                    </div>

                <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=carrito">
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Carrito
                            <span class="badge bg-dark text-white ms-1 rounded-pill"><%=listaCarrito.size()%></span>
                        </button>
                    </form>
                </form>
            </div>
        </div>
    </nav>


<div class="container bootstrap snippets bootdey" style=" margin-top: 25px;">
    <% if (session.getAttribute("msg") != null){ %>
    <div id="mensaje" class="alert alert-danger">
        <%=session.getAttribute("msg")%>
    </div>
    <script type="text/javascript">
        window.setTimeout("closeHelpDiv();", 5000);
        function closeHelpDiv(){
            document.getElementById("mensaje").style.display=" none";
        }
    </script>
    <% session.removeAttribute("msg");
    }%>
<div class="panel-body inf-content">
<div id="contenedor2" class="row">
    <div class="col-md-4">
    <br>
        <img alt="" style="width:600px; " title="" class="img-circle img-thumbnail isTooltip" src="https://bootdey.com/img/Content/avatar/avatar7.png" data-original-title="Usuario">
        <ul title="Ratings" class="list-inline ratings text-center">
            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
        </ul>
    </div>
    <div class="col-md-6">
    <br>
        <strong>Informacion</strong><br>
        <div class="table-responsive">
        <table class="table table-user-information">
            <tbody>
                <tr>
                    <td>
                        <strong>
                            <span class="glyphicon glyphicon-asterisk text-primary"></span>
                            DNI
                        </strong>
                    </td>
                    <td class="text-primary">
                        <%=bCliente.getDNI()%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>
                            <span class="glyphicon glyphicon-user  text-primary"></span>
                            Nombre
                        </strong>
                    </td>
                    <td class="text-primary">
                        <%=bCliente.getNombre()%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>
                            <span class="glyphicon glyphicon-cloud text-primary"></span>
                            Apellido
                        </strong>
                    </td>
                    <td class="text-primary">
                        <%=bCliente.getApellidos()%>
                    </td>
                </tr>



                <tr>
                    <td>
                        <strong>
                            <span class="glyphicon glyphicon-eye-open text-primary"></span>
                            Distrito
                        </strong>
                    </td>
                    <td class="text-primary">
                        <%=bCliente.getDistrito()%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>
                            <span class="glyphicon glyphicon-envelope text-primary"></span>
                            Correo
                        </strong>
                    </td>
                    <td class="text-primary">
                        <%=bCliente.getLogueoCorreo()%>
                    </td>
                </tr>

            </tbody>
        </table>
        <div id="cont_button_edit">
         <button id="botoneditar" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">Editar Perfil</button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Editar datos</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=Update">
                              <div class="mb-3">
                                <label for="recipient-name" class="col-form-label">Nombres</label>
                                <input name="Nombres" type="text" class="form-control" id="recipient-name">
                              </div>
                            <div class="mb-3">
                                <label for="recipient-name2" class="col-form-label">Apellidos</label>
                                <input name="Apellidos" type="text" class="form-control" id="recipient-name2">
                            </div>
                              <div class="mb-3">
                                    <div class="form-group">
                                    <label for="recipient-name3" class="col-form-label">Distrito:</label>
                                        <select id="recipient-name3" class="form-control form-select-sm" name="Distrito">
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
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button onclick="return confirm('¿Estas seguro que deseas editar tus datos?')" type="submit" class="btn btn-primary">Aceptar</button>
                                </div>
                                </form>
                              </div>
                        </div>
                      </div>
                    </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
    <!-- Footer-->
    <footer class="py-5 bg-dark">
        <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
    </footer>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body>
</html>