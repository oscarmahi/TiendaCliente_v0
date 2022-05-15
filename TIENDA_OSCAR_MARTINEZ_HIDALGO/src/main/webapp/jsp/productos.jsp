<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="curso.java.tienda.vo.ProductoVO"%>
<%@ include file="../includes/cabecera.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bicicletas Serbatic</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

</head>
<body>
	<h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
	<br />
	<h3 class="text-center">Lista de Productos: Bicicletas</h3>
	<hr/>
	<!-- FORMULARIO PARA EL PUNTO DEL FILTRADO DEL CATALOGO DE PRODUCTOS -->
	<div class="text-center">
	<form action="Catalogo" method="post">
        <legend>FILTROS</legend>          
            <br/><br/>   
            <label class="label2"  for="filtro">Filtro de Ordenacion</label>
            <select class="select1" name="filtro" id="filtro">
                <option value="todos">Todos</option>
                <option value="nombre">Nombre</option>
                <option value="descripcion">Descripcion</option>
                <option value="precio">Precio</option>
                <option value="stock">Stock</option>
            </select>
            <input type="hidden" name="inputFiltrar" value="Filtro1">
            <input type="submit" value="Filtro de Orden">
	</form>
    <span>.</span>
	<form action="Catalogo" method="post">
            <label class="label2"  for="filtroNombre">  Filtro de Busqueda: </label>
            <input type = "text" name="filtroNombre" placeholder="Nombre"/>
            <label class="label2"  for="filtroDescripcion"></label>
            <input type = "text" name="filtroDescripcion" placeholder="Descripcion"/>
            <input type="hidden" name="inputFiltrar" value="Filtro2">
            <input type="submit" value="Filtro Busqueda">
	</form>
	</div>
	<hr/>
	<!-- FORMULARIO PARA EL TEMA DE LA PAGINACION  -->
	<form action="ServletPaginador" method="post">
		<select name="select">
			<option value="5" selected>5</option>
			<option value="10">10</option>
			<option value="25">25</option>
			<option value="50">50</option>
			<option value="100">100</option>
		</select>
		 <input type="submit" value="Paginar">
	</form>
	<h6>Pagina No:<%=request.getAttribute("pagina")%></h6>
	
	
	<!-- LISTAR LOS PRODUCTOS -->
	<%
	ArrayList<ProductoVO> lista = (ArrayList<ProductoVO>) request.getAttribute("listaProductos");
	%>
	<table class="table table-striped table-hover">
		<tr>
			<td>Categoria</td>
			<td>Nombre</td>
			<td>Descripcion</td>
			<td>Precio</td>
			<td>Stock</td>
			<td>Accion</td>
			<td></td>
		</tr>
		<%
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
		%>
		<tr>
			<td><%=lista.get(i).getId_categoria()%></td>
			<td><%=lista.get(i).getNombre()%></td>
			<td><%=lista.get(i).getDescripcion()%></td>
			<td><%=lista.get(i).getPrecio()%></td>
			<td><%=lista.get(i).getStock()%></td>			
			<td><a href="Carrito?accion=1&prod=<%=lista.get(i).getId()%>" onclick="mostrarMensaje()">Añadir</a></td>
			<td>
				<button type="button" class="btn btn-light" 
					data-bs-toggle="modal" data-bs-target="#detalleModal"
					data-bs-whatever="@mdo" id="btnModal"
					data-id=<%=lista.get(i).getId()%>
					data-id_categoria=<%=lista.get(i).getId_categoria()%>
					data-nombre=<%=lista.get(i).getNombre()%>
					data-descripcion=<%=lista.get(i).getDescripcion()%>
					data-precio=<%=lista.get(i).getPrecio()%>
					data-stock=<%=lista.get(i).getStock()%>
					data-fecha_alta=<%=lista.get(i).getFecha_alta()%>
					data-fecha_baja=<%=lista.get(i).getFecha_baja()%>
					data-impuesto=<%=lista.get(i).getImpuesto()%>
					data-imagen=<%=lista.get(i).getImagen()%>
					><img src="./imagenes/visible.png" width="20"/></button>
			</td>
		</tr>
		<%
		}
		}
		%>

	</table>
	
	
	<!-- ENLACES PARA EL TEMA DEL PAGINADOR -->
	<a href="ServletPaginador?page=1&total=<%=request.getAttribute("total")%>">Primero</a>
	<%
	if (request.getAttribute("numPaginas") != null) {
		int numPaginas = (int) request.getAttribute("numPaginas");
		for (int i = 0; i < numPaginas; i++) {
	%>	
	<a
		href="ServletPaginador?page=<%=i + 1%>&total=<%=request.getAttribute("total")%>"><%=i + 1%></a>
	<%
	}
	}
	%>
	<a href="ServletPaginador?page=<%=request.getAttribute("numPaginas")%>&total=<%=request.getAttribute("total")%>">Ultimo</a>	
	

	<br />
	<br />
	<br />
	<!-- SI NO ESTA REGISTRADO MUESTRO UN MENSAJE -->
	<%
	String error = (String) request.getAttribute("error");
	if (error != null) {
	%><h1 class="text-primary fs-1 fw-bold text-center fst-italic">Hay
		que logarse</h1>
	<%
	}
	%>
    <form action="Carrito">
       <div class="row w-100 align-items-center">
           <div class="col text-center">             
               <button class="btn btn-primary regular-button" type="submit" name="verCarrito">VER CARRITO</button>  
           </div>  
       </div>
    </form>
	<br />
	<br />
	<br />

    <!-- ENLACE PARA VOLVER AL INICIO -->
	<a href="index.jsp">Pulse para volver a inicio</a>
	<!-- <a href="Carrito">Ver carrito</a> -->
	<%@ include file="../includes/pie.jsp"%>



    <!-- ESTO ES TODO LO DE LA PANTALLA MODAL DEL DETALLE DEL PRODUCTO -->
	<div class="modal fade" id="detalleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Detalle de
						Producto</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
				
				    <div class="col-md-3 border-right center mx-auto">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                            <img class="rounded-circle mt-5" width="150px" src="./imagenes/gravel1.jpg"></img>
                        </div>
                    </div>
				
				
				
					<form name="carrito" action="Carrito" method="post" >
						<div class="mb-3 d-none">
							<label for="prod" class="col-form-label"></label>
							<input type="text" class="form-control" name="prod" id="prod">
						</div>				
						<div class="mb-3 d-none">
							<label for="accion" class="col-form-label"></label>
							<input type="text" class="form-control" name="accion" id="accion" value="1" >
						</div>				
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">Id
								Categoria:</label> <input type="text" class="form-control" id="id_categoriaI" readonly>
						</div>
	
						<div class="mb-3">
							<label for="nombreI" class="col-form-label">Nombre:</label>
							<input type="text" class="form-control" id="nombreI" readonly>
						</div>
						<div class="mb-3">
							<label for="descripcionI" class="col-form-label">Descripcion:</label>
							<input type="text" class="form-control" id="descripcionI" readonly>
						</div>
						<div class="mb-3">
							<label for="precioI" class="col-form-label">Precio:</label>
							<input type="text" class="form-control" id ="precioI" readonly>
						</div>
						<div class="mb-3">
							<label for="stockI" class="col-form-label">Stock:</label> <input
								type="text" class="form-control" id="stockI" readonly>
						</div>
						<div class="mb-3">
							<label for="fecha_altaI" class="col-form-label">Fecha Alta:</label> <input
								type="text" class="form-control" id="fecha_altaI" readonly>
						</div>			
						<div class="mb-3">
							<label for="fecha_bajaI" class="col-form-label">Fecha Baja:</label> <input
								type="text" class="form-control" id="fecha_bajaI" readonly>
						</div>						
						
						<div class="mb-3">
							<label for="impuestoI" class="col-form-label">Impuesto:</label> <input
								type="text" class="form-control" id="impuestoI" readonly>
						</div>						
						
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>
							<button type="submit" class="btn btn-primary">Añadir</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


    <!-- CODIGO JQUERY PARA PASAR LOS DATOS DE LA TABLA A LA VENTANA DEL MODAL DEL DETALLE DEL PRODUCTO -->
	<script src="https://code.jquery.com/jquery-3.4.1.js">		
	</script>
	<script type="text/javascript">
		$(document).on("click", "#btnModal", function() {
			var id = $(this).data('id');
			var idC = $(this).data('id_categoria');
			var nombreV = $(this).data('nombre');
			var descripcionV = $(this).data('descripcion');
			var precioV = $(this).data('precio');
			var stockV = $(this).data('stock');
			var fecha_altaV = $(this).data('fecha_alta');
			var fecha_bajaV = $(this).data('fecha_baja');
			var impuestoV = $(this).data('impuesto');
			var imagenV = $(this).data('imagen');
			
			$("#prod").val(id);
			$("#id_categoriaI").val(idC);
			$("#nombreI").val(nombreV);
			$("#descripcionI").val(descripcionV);
			$("#precioI").val(precioV);
			$("#stockI").val(stockV);
			$("#fecha_altaI").val(fecha_altaV);
			$("#fecha_bajaI").val(fecha_bajaV);
			$("#impuestoI").val(impuestoV);
			$("#imagenI").val(imagenV);
			
		})
	</script>
	
	<!-- CODIGO JAVASCRIPT PARA MOSTRAR MENSAJE DE QUE HE AÑADIDO PRODUCTO AL CARRITO -->
	<script type="text/javascript">
		function mostrarMensaje(){
			alert("Producto añadido al carrito.");
		}
	
	</script>

</body>
</html>