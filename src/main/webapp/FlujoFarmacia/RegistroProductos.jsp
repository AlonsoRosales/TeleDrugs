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
              <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Teledrugs</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                      FlujoFarmacia/
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
                    <h1 class="display-4 fw-bolder">Lista de productos</h1>
                    <p class="lead fw-normal text-white-50 mb-0">Farmacia XXXX</p>
                </div>
            </div>
			<div class = "box">
					<input type="text" name="search" placeholder="Buscar producto" class="src" autocomplete = "off">
			</div>
			<div style="display:flex; align-items:center; justify-content:center; margin-top: 50px; margin-bottom: 15px;">
				<a class="btn btn-success" href="FlujoFarmacia/AgregarProducto.jsp" style="width:250px;">Agregar Producto</a>
				<a class="btn btn-success" href="<%= request.getContextPath()%>/HistorialPedido" style="width:250px;">Ver Historial de Pedidos</a>

			</div>
		</header>
		</div>
	<div class="container padding-bottom-3x mb-1", style=" margin-top: 25px;">
		<div class="table-responsive shopping-cart">
        <table class="table">
            <thead>
                <tr>
					<th class="text-center">Imagen del producto</th>
					<th class="text-center">Nombre del producto</th>
					<th class="text-center">Descripcion</th>
					<th class="text-center">Stock/Cantidad</th>
					<th class="text-center">Precio Unitario</th>
					<th class="text-center">Editar</th>
					<th class="text-center">Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <tr>
					<td class="text-center text-lg text-medium"><center><img style = "width: 20%; margin:auto" src="FlujoFarmacia/Imagen/provisional.png"></center> </td>
					<td class="text-center text-lg text-medium">Paracetamol</td>
					<td class="text-center text-lg text-medium">El paracetamol es un medicamento de venta sin receta médica que se da para aliviar la fiebre y el dolor.</td>
					<td class="text-center text-lg text-medium">5</td>
					<td class="text-center text-lg text-medium">S/10</td>
					<td class="text-center text-lg text-medium">
						<div class="column"><a style="width:120px; margin:5px;" class="btn btn-success" href="FlujoFarmacia/EditarProducto.jsp">Editar</a></div>
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
								¿Estas seguro de que quiere eliminar el producto?
							  </div>
							  <div class="modal-footer">
								<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
								<button class="btn btn-success" onclick="alert('Se ha eliminado el producto')" data-bs-dismiss="modal">Aceptar</button>
							  </div>
							</div>
						  </div>
						</div>
						<a class="remove-from-cart" data-bs-toggle="modal" href="#exampleModalToggle" data-toggle="tooltip" title="" data-original-title="Remove item"><i class="bi bi-trash"></i></a>
					</td>                
                </tr>
                <tr>
					<td class="text-center text-lg text-medium"><center><img style = "width: 20%; margin:auto" src="FlujoFarmacia/Imagen/provisional.png"></center> </td>
					<td class="text-center text-lg text-medium">Paracetamol</td>
					<td class="text-center text-lg text-medium">El paracetamol es un medicamento de venta sin receta médica que se da para aliviar la fiebre y el dolor.</td>
					<td class="text-center text-lg text-medium">5</td>
					<td class="text-center text-lg text-medium">S/10</td>
					<td class="text-center text-lg text-medium">
						<div class="column"><a style="width:120px; margin:5px;" class="btn btn-success" href="FlujoFarmacia/EditarProducto.jsp">Editar</a></div>
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
								¿Estas seguro de que quiere eliminar el producto?
							  </div>
							  <div class="modal-footer">
								<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
								<button class="btn btn-success" onclick="alert('Se ha eliminado el producto')" data-bs-dismiss="modal">Aceptar</button>
							  </div>
							</div>
						  </div>
						</div>
						<a class="remove-from-cart" data-bs-toggle="modal" href="#exampleModalToggle" data-toggle="tooltip" title="" data-original-title="Remove item"><i class="bi bi-trash"></i></a>
					</td> 
                </tr>
                
				<tr>
					<td class="text-center text-lg text-medium"><div style="text-align: center;"><img style = "width: 20%; margin:auto" src="FlujoFarmacia/Imagen/provisional.png"></div> </td>
					<td class="text-center text-lg text-medium">Paracetamol</td>
					<td class="text-center text-lg text-medium">El paracetamol es un medicamento de venta sin receta médica que se da para aliviar la fiebre y el dolor.</td>
					<td class="text-center text-lg text-medium">5</td>
					<td class="text-center text-lg text-medium">S/10</td>
					<td class="text-center text-lg text-medium">
						<div class="column"><a style="width:120px; margin:5px;" class="btn btn-success" href="EditarProducto.jsp">Editar</a></div>
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
								¿Estas seguro de que quiere eliminar el producto?
							  </div>
							  <div class="modal-footer">
								<button style="margin-right:10px" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
								<button class="btn btn-success" onclick="alert('Se ha eliminado el producto')" data-bs-dismiss="modal">Aceptar</button>
							  </div>
							</div>
						  </div>
						</div>
						<a class="remove-from-cart" data-bs-toggle="modal" href="#exampleModalToggle" data-toggle="tooltip" title="" data-original-title="Remove item"><i class="bi bi-trash"></i></a>
					</td> 
                </tr>
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
			</nav>
			
		</div>
		</div>
	</div>		
		<footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; TeleDrugs 2021</p></div>
        </footer>
		
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
