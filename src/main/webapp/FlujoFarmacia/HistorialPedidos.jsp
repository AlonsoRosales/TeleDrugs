<!DOCTYPE html>
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BPedidoHistorial" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" %>
<jsp:useBean type="pe.edu.pucp.iweb.teledrugs.Beans.BFarmacia" scope="request" id="farmacia"/>
<% ArrayList<BPedidoHistorial>listaHistorialPedidos = (ArrayList<BPedidoHistorial>) request.getAttribute("listaHistorialPedidos"); %>




<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Bare - Start Bootstrap Template</title>
	<!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="FlujoFarmacia/css/styles.css" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="#!">Teledrugs</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="nav-item"><a class="nav-link" href="FlujoFarmacia/AgregarProducto.jsp">Agregar Producto</a></li>
				<li class="nav-item"><a class="nav-link" href="FlujoFarmacia/ListaProductos.jsp">Lista de Productos</a></li>
				<li class="nav-item"><a class="nav-link" href="FlujoFarmacia/HistorialPedidos.jsp">Historial de pedidos</a></li>
			</ul>
			<div class="dropdown">
				<a class="btn btn-outline-dark dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
					<i class='bi bi-person-circle' style='font-size:15px'></i>
					Usuario
				</a>

				<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<li><a class="dropdown-item" href="Login/iniciar.jsp">Cerrar sesión</a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>
<header class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-white">
			<h1 class="display-4 fw-bolder">Historial de pedidos</h1>
			<p class="lead fw-normal text-white-50 mb-0">Farmacia <%=farmacia.getNombre()%></p>
		</div>
	</div>
	<div class = "box">
		<input type="text" name="search" placeholder="Buscar producto" class="src" autocomplete = "off">
		<div style="display:flex; align-items:center; justify-content:center; margin-top: 50px; margin-bottom: 15px;">
			<a class="btn btn-success" href="FlujoFarmacia/AgregarProducto.jsp" style="width:250px;">Agregar Producto</a>
			<a class="btn btn-success" href="/trabajo_war_exploded/ListaProductos?ruc=<%=request.getAttribute("ruc")%>" style="width:250px;">Ver Lista de Productos</a>
		</div>
	</div>
</header>
<div class="container padding-bottom-3x mb-1", style=" margin-top: 25px;">
	<div class="table-responsive shopping-cart">
		<table class="table">
			<thead>
			<tr>
				<th class="text-center">Numero de orden</th>
				<th class="text-center">Datos del cliente</th>
				<th class="text-center">Estado de pedido</th>
				<th class="text-center">Fecha de recojo</th>
				<th class="text-center">Receta</th>
				<th class="text-center">Detalle de compra</th>
				<th class="text-center">Cancelar</th>
			</tr>
			</thead>
			<tbody>
			<%
				int i = 1;
				for (BPedidoHistorial pedidoHistorial : listaHistorialPedidos) {
			%>
			<tr>
				<td class="text-center text-lg text-medium"><%=i++%></td>
				<td class="text-center text-lg text-medium">
					<ul class="list-group">
						<li class="list-group-item"><%=pedidoHistorial.getNombreCompleto()%></li>
						<li class="list-group-item"><%=pedidoHistorial.getDni()%></li>
					</ul>
				</td>
				<td class="text-center text-lg text-medium"><%=pedidoHistorial.getEstado()%></td>
				<td class="text-center text-lg text-medium"><%=pedidoHistorial.getFechaRecojo()%></td>
				<td class="text-center text-lg text-medium">
					<div class="modal" id="Receta1" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">Receta medica del pedido 322</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<img style = "width: 20%; margin:auto" src="FlujoFarmacia/Imagen/receta.png">
								</div>
								<div class="modal-footer">
									<button class="btn btn-success" data-bs-dismiss="modal">Aceptar</button>
								</div>
							</div>
						</div>
					</div>
					<div class="column"><a style="width:120px; margin:5px;" class="btn btn-success" data-bs-toggle="modal" href="#Receta1">Receta</a></div>
				</td>
				<td class="text-center text-lg text-medium">
					<div class="modal" id="Pedido1" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalToggleLabel">Detalles del Pedido 322</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<ul class="list-group list-group-flush">
										<li class="list-group-item">Paracetamol (1)</li>
										<li class="list-group-item">Ensure (2)</li>
										<li class="list-group-item">Analgesicos (3)</li>
										<li class="list-group-item">Vitaminca C (2) </li>
										<li class="list-group-item">Vitamina B (1)</li>
									</ul>
								</div>
								<div class="modal-footer">
									<button class="btn btn-success" data-bs-dismiss="modal">Aceptar</button>
								</div>
							</div>
						</div>
					</div>
					<div class="column"><a style="width:120px; margin:5px;" class="btn btn-success" data-bs-toggle="modal" href="#Pedido1">Detalles</a></div>
				</td>
				<td class="text-center">
					<div class="modal" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalToggleLabel">Confirmación</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<div class="modal-body">
									¿Estas seguro de que quiere eliminar el pedido?
								</div>
								<div class="modal-footer">
									<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
									<button class="btn btn-success" onclick="alert('Se ha eliminado el pedido')" data-bs-dismiss="modal">Aceptar</button>
								</div>
							</div>
						</div>
					</div>
					<a class="remove-from-cart" data-bs-toggle="modal" href="#exampleModalToggle" data-toggle="tooltip" title="" data-original-title="Remove item"><i class="bi bi-trash"></i></a>
				</td>
			</tr>
			<%
				}
			%>
			</tbody>
		</table>
		<div class="column" style="margin-top:20px;">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled">
						<a class="page-link">Previous</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item">
						<a class="page-link" href="#">Next</a>
					</li>
				</ul>
				<a style="margin-top:20px; width:200px; float:right; "  class="btn btn-secondary justify-content-right"  href="/trabajo_war_exploded/ListaProductos?ruc=<%=request.getAttribute("ruc")%>" style="width:250px;">Regresar</a>
			</nav>

		</div>

	</div>


</div>

<footer class="py-5 bg-dark">
	<div class="container"><p class="m-0 text-center text-white">Copyright &copy; TeleDrugs 2021</p></div>
</footer>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
