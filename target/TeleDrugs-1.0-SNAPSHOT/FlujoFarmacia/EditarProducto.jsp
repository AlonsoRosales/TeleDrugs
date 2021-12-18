<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<!-- Navigation-->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container px-4 px-lg-5">
				<a class="navbar-brand" href="#!">Teledrugs</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						<li class="nav-item"><a class="nav-link" href="FlujoFarmacia/AgregarProducto.jsp">Agregar Producto</a></li>
						<li class="nav-item"><a class="nav-link" href="FlujoFarmacia/RegistroProductos.jsp">Lista de Productos</a></li>
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
					<h1 class="display-4 fw-bolder">Editar Producto</h1>
					<p class="lead fw-normal text-white-50 mb-0">Farmacia XXXX</p>
				</div>
			</div>
		</header>


		<!-- Product section-->
		<section class="py-5">
			<div class="container px-4 px-lg-5 my-5">
				<div class="row gx-4 gx-lg-5 align-items-center">
					<form method="post" action="<%=request.getContextPath()%>/FarmaciaPrincipal?action=update">

						<div class="col-md-6"><img style="width:500px; height:460px;"class="card-img-top mb-5 mb-md-0" src="FlujoFarmacia/Imagen/108218L.jpg" alt="...">
							<div style="display:flex; align-items:center; justify-content:center;"class="column">
								<a><label  style="margin-top:20px;"for="files" class="btn btn-primary">Subir imagen</label>
									<input id="files" style="visibility:hidden; position:absolute" type="file"></a>
							</div>
						</div>

						<div class="col-md-6">
							<div>
								<p style="margin-bottom:0px; margin-top:10px;">Nombre producto</p>
								<h1>Paracetamol</h1>
								<!--h1 class="display-13 fw-bolder">Paracetamol</h1!-->
								<!--div class="fs-5 mb-5">
                                    <span class="text-decoration-line-through">$45.00</span!>
                                    <span>s/</span>
                                </div!-->
							</div>
							<div>
								<p style="margin-bottom:0px; margin-top:10px;">Stock</p>
								<input class="form-control text-left" type="number" value="Stock" style="max-width: 4rem">
							</div>
							<!--h1 class="display-9 fw-bolder">50 tabletas de 4 pastillas c/u</h1!-->
							<div>
								<p style="margin-bottom:0px; margin-top:10px;">Precio del producto</p>
								<input class="form-control text-left" type="number" value="Precio del Producto" style="max-width: 4rem">
							</div>
							<!--p><input type="precio" name="precio" value=""></input></p!-->
							<!--h1 class="display-11 fw-bolder">s/4.00 cada tableta</h1!-->
							<div>
								<p style="margin-bottom:0px; margin-top:10px;">Requiere receta medica</p>
								<p style="margin-bottom:0px; margin-top:10px;">Si</p>
								<input type="radio" name="elecciones" value="Si">
								<p style="margin-bottom:0px; margin-top:10px;">No</p>
								<input type="radio" name="elecciones" value="No">
							</div>
							<div>
								<p style="margin-bottom:0px; margin-top:10px;">Descripcion del producto</p>
								<input class="form-control text-left" type="text" style="max-width: 25rem; max-height:25rem; margin-bottom:20px;">
								<div class="modal" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
									<div class="modal-dialog modal-dialog-centered">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalToggleLabel">Confirmación</h5>
												<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												¿Estas seguro de que quiere Editar el producto?
											</div>
											<div class="modal-footer">
												<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
												<button class="btn btn-success" onclick="alert('Se han realizado los cambios al producto')" data-bs-dismiss="modal">Aceptar</button>
											</div>
										</div>
									</div>
								</div>
								<div class="column"><a style="margin-left:0px;" class="btn btn-success" data-bs-toggle="modal" href="#exampleModalToggle">Aceptar</a></div>
							</div>
						</div>
					</form>
				</div>
				<a style="margin-top:20px; width:200px; float:right; "  class="btn btn-secondary" href="FlujoFarmacia/ListaProductos.jsp">Regresar</a>
			</div>
		</section>

		<!-- Footer-->
		<footer class="py-5 bg-dark">
			<div class="container"><p class="m-0 text-center text-white">Copyright © TeleDrugs 2021</p></div>
		</footer>
		<!-- Bootstrap core JS-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Core theme JS-->
		<script src="js/scripts.js"></script>


	</body></html>