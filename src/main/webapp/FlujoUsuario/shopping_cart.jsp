<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.DTO.DTOCarritoCliente" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<DTOCarritoCliente> listaCarrito = session.getAttribute("carrito") != null ? (ArrayList) session.getAttribute("carrito") : new ArrayList<>();%>
<%ArrayList<BFarmacia> listafarmacias = (ArrayList) session.getAttribute("listafarmacias");%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Producto</title>
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
                              <li><a href="<%= request.getContextPath()%>/Usuario?opcion=mostrarPerfil&" class="dropdown-item" >Ver perfil</a></li>
                              <li><a href="<%= request.getContextPath()%>/Usuario?opcion=logout" class="dropdown-item" >Cerrar sesión</a></li>
						  </ul>
						</div>

                    <form method="post" action="<%= request.getContextPath()%>/Usuario?&opcion=carrito">
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

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container padding-bottom-3x mb-1", style=" margin-top: 25px;">
    <!-- Shopping Cart-->
    <div class="table-responsive shopping-cart">
        <table class="table">
            <thead>
                <tr>
                    <th>Productos</th>
                    <th class="text-center">Cantidad</th>
                    <th class="text-center">Precio</th>
                    <th class="text-center"><a class="btn btn-sm btn-outline-danger" href="<%=request.getContextPath()%>/Usuario?opcion=limpiarcarrito">Limpiar Carrito</a></th>
					<th class="text-center">Receta</th>
                </tr>
            </thead>
            <tbody>
            <%int i=0;%>
            <%for(DTOCarritoCliente bCarritoCliente : listaCarrito){%>
            <tr>
                <td>
                    <div class="product-item">
                        <a class="product-thumb" href="#"><img src="<%=bCarritoCliente.getFoto()%>" alt="Product"></a>
                        <div class="product-info">
                            <h4 class="product-title"><a href="#"><%=bCarritoCliente.getProducto()%></a></h4><span><em>Codigo:</em><%=bCarritoCliente.getCodigo()%></span><span><em>Stock:</em><%=bCarritoCliente.getStock()%></span>
                        </div>
                    </div>
                </td>
                    <td class="text-center text-lg text-medium"><%=bCarritoCliente.getCantidad()%></td>
                    <td class="text-center text-lg text-medium">S/.<%=bCarritoCliente.getPrecio()%></td>
					<td class="text-center">
						<div class="modal" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
						  <div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
							  <div class="modal-header">
								<h5 class="modal-title" id="exampleModalToggleLabel">Confirmación</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							  </div>
							  <div class="modal-body">
								¿Estas seguro de que quiere eliminar el producto del carrito?
							  </div>
							  <div class="modal-footer">
								<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
								<button class="btn btn-success" onclick="alert('Se ha eliminado el producto del carrito')" data-bs-dismiss="modal">Aceptar</button>
							  </div>
							</div>
						  </div>
						</div>
                        <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=borraruno">
                            <input name="idcarrito" value="<%=i%>" type="hidden"/>
                            <button  class="btn btn-outline-danger flex-shrink-0" type="submit">
                                <i class="bi bi-trash"></i>
                            </button>
                        </form>
					</td>
                <%if(bCarritoCliente.isRequiereReceta()){%>
                <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=subirReceta">
                    <td class="text-center"><a><label for="files" class="btn btn-primary">Subir receta</label>
                        <input name="imagen" id="files" style="visibility:hidden; position:absolute" type="file"></a>
                        <input name="idcarrito" value="<%=i%>" type="hidden"/>
                        <button type="submit">Aceptar</button>
					</td>
                </form>
                <%}%>
                </tr>
                <%i=i+1;%>
            <%}%>
            </tbody>
        </table>
        <div class="shopping-cart-footer">

            <!-- Button trigger modal -->

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" style="display: flex; align-items: center; align-content: center;">
                    <div class="modal-content">
                        <form method="post" action="<%=request.getContextPath()%>/Usuario?opcion=comprar">
                        <div style="text-align: center;"><div class="modal-body">
                                <h4 class="text-center">SELECCIONA LA FECHA</h4>
                                <input class="form-control-lg mt-2 mb-2" type="date" name="fecha"/>

                                <h4 class="text-center">SELECCIONA LA HORA</h4>
                                <input class="form-control-lg mt-2 mb-2" type="time" name="hora" min="10:00:00" max="22:00:00"/>

                        </div></div>
                            <div class="modal-footer" style="outline: none">
                            <button type="button" class="btn btn-secondary m-2" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary">Aceptar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="column">
                <button onclick="" type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Comprar
                </button>
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
        <script src="FlujoUsuario/js/scripts.js"></script>
    </body>
</html>
