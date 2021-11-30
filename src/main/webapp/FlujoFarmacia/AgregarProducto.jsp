
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Agregar Producto Farmacia</title>
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

			</ul>
			<div class="dropdown">
				<a class="btn btn-outline-dark dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
					<i class='bi bi-person-circle' style='font-size:15px'></i>
					Usuario
				</a>

				<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<li><a class="dropdown-item" href="<%=request.getContextPath()%>">Cerrar sesión</a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>
<header class="bg-dark py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-white">
			<h1 class="display-4 fw-bolder">Agregar Producto</h1>

		</div>
	</div>
</header>


<!-- Product section-->
<section class="py-5">
	<div class="container px-4 px-lg-5 my-5"  style="margin-bottom:5px;">
		<div class="row gx-4 gx-lg-5">
			<div class="col-md-6"><img style="width:500px; height:470px;"class="card-img-top mb-5 mb-md-0" src="FlujoFarmacia/Imagen/asd.jpg" alt="...">
					<div style="display:flex; align-items:center; justify-content:center;"class="column">
						<a><label  style="margin-top:10px;"for="files" class="btn btn-primary">Subir imagen</label>
							<input id="files" style="visibility:hidden; position:absolute" type="file"></a>
					</div>
			</div>

			<div class="col-md-6">
				<form method="POST" action="<%=request.getContextPath() %>/FarmaciaPrincipal?action=crear">
				<div>
					<p style="margin-bottom:0px; margin-top:10px;">Nombre producto</p>
					<input name="nombre" class="form-control text-left me-3" id="inputQuantity" type="" style="max-width: 15rem">
				</div>
				<div>
					<p style="margin-bottom:0px; margin-top:10px;">Stock</p>
					<input name="stock" class="form-control text-left" type="number" value="Stock" style="max-width: 4rem">
				</div>
				<div>
					<p style="margin-bottom:0px; margin-top:10px;">Precio del producto</p>
					<input name="precio" class="form-control text-left" type="number" value="Precio del Producto" style="max-width: 4rem">
				</div>
				<div>
					<p style="margin-bottom:0px; margin-top:10px;">Requiere receta medica</p>
					<select class="form-control-lg" style="border-color: lightgray" name="requiere">
						<option value="1">Si</option>
						<option selected value="0">No</option>
					</select>
				</div>
				<div>
					<p style="margin-bottom:0px; margin-top:10px;">Descripcion del producto</p>
					<input name="descripcion" class="form-control text-left" type="text" style="max-width: 25rem; max-height:25rem; margin-bottom:20px;">
				</div>
				<div class="column">
					<button onclick="window.confirm('¿Seguro de añadir el producto?')" style="margin-left:0px;" class="btn btn-success" type="submit">Agregar Producto</button>
				</div>
				</form>
			</div>
		</div>
		<a href="<%=request.getContextPath()%>/FarmaciaPrincipal" style="margin-top:20px; width:200px; float:right; "  class="btn btn-secondary" style="width:250px;">Regresar</a>
	</div>
</section>

<div>
	<footer class="py-5 bg-dark">
		<div class="container"><p class="m-0 text-center text-white">Copyright © TeleDrugs 2021</p></div>
	</footer>
	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="FlujoUsuario/js/scripts.js"></script>
</div>

</body></html>