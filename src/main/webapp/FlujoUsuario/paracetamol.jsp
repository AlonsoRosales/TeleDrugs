
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.DTO.DTOBuscarProductoCliente" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<DTOBuscarProductoCliente> bBuscarProductoClientes = (ArrayList) request.getAttribute("productos");%>
<%String correo = (String) request.getAttribute("correo");%>
<%BFarmacia bFarmacia2 = (BFarmacia) request.getAttribute("farmacia");%>
<%DTOBuscarProductoCliente producto = (DTOBuscarProductoCliente) request.getAttribute("producto");%>
<%ArrayList<BFarmacia> listafarmacias = (ArrayList) request.getAttribute("listafarmacias");%>
<html lang="en">
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
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">Teledrugs</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/Usuario?correo=<%=correo%>&ruc=<%=bFarmacia2.getRuc()%>">Pagina principal</a></li>
                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=historialPedidos&ruc=<%=bFarmacia2.getRuc()%>">Estado de pedido</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Farmacias</a>
                    <ul class="dropdown-menu" style="height: 200px;width: 300px;" aria-labelledby="navbarDropdown">
                        <div style="text-align: center; margin-top: 20px;"><h4 class="form-title">ELEGIR FARMACIA</h4></div>
                        <div class="modal-body">
                            <form method="post" action="<%=request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=mostrarFarmacia" class= "register-form">
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
                    <li><a href="<%= request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=mostrarPerfil&ruc=<%=bFarmacia2.getRuc()%>" class="dropdown-item" >Ver perfil</a></li>
                    <li><a href="<%= request.getContextPath()%>" class="dropdown-item" >Cerrar sesión</a></li>
                </ul>
            </div>

            <form method="post" action="<%= request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=carrito&ruc=<%=bFarmacia2.getRuc()%>">
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
    <div style="text-align: center; margin-bottom: 30px">
        <h1 >Lleva tus medicamentos a bajo precio</h1>
    </div>
    <form method="post" action="<%=request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=Buscar&ruc=<%=bFarmacia2.getRuc()%>">
        <div class = "box">
            <input  type="text" name="search" placeholder="Buscar producto" class="src" autocomplete = "off">
        </div>
    </form>
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="<%=producto.getFoto()%>" alt="..." /></div>
            <div class="col-md-6">
                <h1 class="display-5 fw-bolder"><%=producto.getNombre()%></h1>
                <div class="fs-5 mb-5">
                    <!--<span class="text-decoration-line-through">$45.00</span> -->
                    <span>S/.<%=producto.getPrecio()%></span>
                </div>
                <p class="lead"><%=producto.getNombre()%> <%=producto.getDescripcion()%></p>
                <div class="d-flex">
                    <form method="post" action="<%=request.getContextPath()%>/Usuario?correo=<%=correo%>&opcion=carrito&ruc=<%=bFarmacia2.getRuc()%>&idProducto=<%=producto.getIdProducto()%>">
                        <div class="value-button" id="decrease" onclick="decreaseValue()" value="Decrease Value">-</div>
                        <input name="cantidad" type="number" id="number" value="0" />
                        <div class="value-button" id="increase" onclick="increaseValue()" value="Increase Value">+</div>
                        <button onclick="return confirm('Esta seguro de comprar esta cantidad?')" class="btn btn-outline-dark flex-shrink-0" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Add to cart
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
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