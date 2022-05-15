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
	<!-- <link rel="stylesheet" href="index.css"/> -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
		rel="stylesheet" 
		integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
		crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="./css/index.js"></script>
</head>
<body>
    <h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
    <br />
    <h3 class="text-center">Carrito de Productos</h3>
    <hr/>
	
		<%
		ArrayList<ProductoVO> lista = (ArrayList<ProductoVO>) request.getSession().getAttribute("carrito");
		%>

	<table  class="table table-striped">
		<tr>
			<td>Categoria</td>
			<td>Nombre</td>
			<td>Descripcion</td>
			<td>Precio</td>
			<td>Stock</td>
			<td>Unidades</td>
			<td>Accion</td>
		</tr>
<%
		if (lista != null){
			for (int i=0; i<lista.size(); i++){
			%>	
			<tr>
				<td><%=lista.get(i).getId_categoria() %></td>
				<td><%=lista.get(i).getNombre() %></td>
				<td><%=lista.get(i).getDescripcion() %></td>
				<td><%=lista.get(i).getPrecio() %></td>
				<td><%=lista.get(i).getStock() %></td>
				<td><%=lista.get(i).getCantidad() %></td>
				<td><a href="Carrito?accion=2&prod=<%=lista.get(i).getId()%>">Borrar</a></td>
			</tr>
			<%	
			}
		}
		%>		

	</table>
	<br/><br/>
	
	<a href="Catalogo">Seguir comprando</a>
	<br/>
	<br/>
	<!-- <a href="pedidos.jsp">Comprar</a> -->
	
	<br/><br/>
	
	<form action="PantallaPedido">
       <div class="row w-100 align-items-center">
           <div class="col text-center">             
               <button class="btn btn-primary regular-button" type="submit" name="verCarrito">COMPRAR</button>  
           </div>  
       </div>
    </form>
	
	<%@ include file="../includes/pie.jsp"%>

	
</body>
</html>
