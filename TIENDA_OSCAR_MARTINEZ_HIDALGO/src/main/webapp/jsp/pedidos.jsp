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
<script type="text/javascript" src="./js/index.js"></script>
</head>
<body>
    <h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
    <br />
    <h3 class="text-center">Confirmacion de Pedido</h3>
    <hr/>	
	

	<%
	ArrayList<ProductoVO> lista = (ArrayList<ProductoVO>) request.getSession().getAttribute("carrito");
	double total = 0.0;
	double cantidad = 0;
	%>
	<table class="table table-striped">
		<tr>
			<td>Categoria</td>
			<td>Nombre</td>
			<td>Descripcion</td>
			<td>Precio</td>
			<td>Stock</td>
			<td>Unidades</td>
		</tr>
		<%
		if (lista != null) {
			for (int i = 0; i < lista.size(); i++) {
				cantidad = lista.get(i).getCantidad() * lista.get(i).getPrecio();
				total += (cantidad + (cantidad * lista.get(i).getImpuesto()));
		%>
		<tr>
			<td><%=lista.get(i).getId_categoria()%></td>
			<td><%=lista.get(i).getNombre()%></td>
			<td><%=lista.get(i).getDescripcion()%></td>
			<td><%=lista.get(i).getPrecio()%></td>
			<td><%=lista.get(i).getStock()%></td>
			<td><%=lista.get(i).getCantidad()%></td>
		</tr>
		<%
		}
		}
		total = Math.round(total*100.0)/100.0;
		%>
	</table>
	<br />
	<br />
	<br />

	<form name="confirmaPedido" action="PantallaPedido" method="post">
		<fieldset>
			<legend>DATOS DEL PEDIDO</legend><br/><br/>
			<label>Forma de Pago: </label>
			<select class="select1" name="formaPago" id="formaPago">
				<option selected disabled value="">Seleccione...</option>
				<option value="tarjeta">Tarjeta</option>
				<option value="Pay Pal">Pay Pal</option>
			</select> <br /><br/>
			<label>Total del pedido: </label>
			<input type="text" name="totalS" id="totalS" value=<%=total%> readonly></input><br/><br/>		
			<div class="row w-100 align-items-center">
                <div class="col text-center"> 
    		         <input type="submit" name="comprar" value="Comprar" class="button btn btn-warning"/>			
			    </div>  
             </div>			
		</fieldset>
		<br />
	</form>
	
	
    <form action="Carrito">
       <div class="row w-100 align-items-center">
           <div class="col text-center">             
               <button class="btn btn-primary regular-button" type="submit" name="verCarrito">VOLVER AL CARRITO</button>  
           </div>  
       </div>
    </form>	
	
	<%
	String error = (String) request.getAttribute("error");
	if (error != null){
		%><h1 class="text-primary fs-1 fw-bold text-center fst-italic"><%=error %></h1><%
	}

	%> 		
		
	<%@ include file="../includes/pie.jsp"%>
</body>
</html>