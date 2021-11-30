
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.DTO.DTOBuscarProductoCliente" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<DTOBuscarProductoCliente> bBuscarProductoClientes = (ArrayList) request.getAttribute("productos");%>
<%ArrayList<BFarmacia> listafarmacias = (ArrayList) session.getAttribute("listafarmacias");%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Jabon</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="FlujoUsuario/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="FlujoUsuario/css/styles.css" rel="stylesheet" />
		
    </head>
    <body>
        <!-- Navigation-->
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

                    <form method="post" action="<%= request.getContextPath()%>/Usuario?opcion=carrito">
                        <form class="d-flex">
                            <button class="btn btn-outline-dark" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Carrito
                                <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                            </button>
                        </form>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Product section-->


        <section class="py-5">
            <h1 style="text-align: center; margin-bottom: 30px;"> BUSCA LO QUE TU KIERAS MY KING </h1>
            <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=Buscar">
                <div class = "box">
                    <input  type="text" name="search" placeholder="Buscar producto" class="src" autocomplete = "off">
                </div>
            </form>
            <div class="container px-4 px-lg-5 mt-5" style="margin-top: 0px">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <%for(DTOBuscarProductoCliente bProducto : bBuscarProductoClientes){%>
                    <div class="col mb-5">
                        <div class="card h-100" style="display: flex; align-content: center; align-items: center;">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">En venta</div>
                            <!-- Product image-->
                            <img style="height: 206px; width: 206px;" class="card-img-top" src="<%=bProducto.getFoto()%>" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder"><%=bProducto.getNombre()%></h5>
                                    <!-- Product price-->
                                    S/. <%=bProducto.getPrecio()%>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <form class="mb-5" method="post" action="<%=request.getContextPath()%>/Usuario?opcion=mostrarProducto">
                                <input name="idProducto" value="<%=bProducto.getIdProducto()%>" type="hidden"/>
                                <button  class="btn btn-outline-dark flex-shrink-0" type="submit">
                                    Ver detalle
                                </button>
                            </form>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </section>
        <div class="column" style="margin-top:20px;">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
        <!-- Related items section-->
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="FlujoUsuario/js/scripts.js"></script>
    </body>
</html>
