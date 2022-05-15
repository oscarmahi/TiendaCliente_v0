<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="curso.java.tienda.vo.PedidoVO"%>
<%@ page import="curso.java.tienda.vo.DetallePedidoExtVO"%>
<%@ include file="../includes/cabecera.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bicicletas Serbatic</title>
<!-- <link rel="stylesheet" href="index.css"/> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="./css/index.js"></script>
</head>
<body>
	<h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
	<br />
	<h3 class="text-center">Historial de Pedidos del Cliente</h3>
	<hr />


	<%
	String consulta = (String) request.getAttribute("consulta");
	ArrayList<PedidoVO> listaPedidos = (ArrayList<PedidoVO>) request.getAttribute("listaPedidos");
	%>

	<table class="table table-striped table-hover">
		<tr>
			<td>Pedido</td>
			<td>Fecha</td>
			<td>Metodo Pago</td>
			<td>Estado</td>
			<td>Numero Factura</td>
			<td>Total</td>
			<td>Accion</td>
			<td></td>
		</tr>
		<%
		if (listaPedidos != null) {
			for (int i = 0; i < listaPedidos.size(); i++) {
		%>
		<tr>
			<td><%=listaPedidos.get(i).getId()%></td>
			<td><%=listaPedidos.get(i).getFecha()%></td>
			<td><%=listaPedidos.get(i).getMetodo_pago()%></td>
			<td><%=listaPedidos.get(i).getEstado()%></td>
			<td><%=listaPedidos.get(i).getNum_factura()%></td>
			<td><%=listaPedidos.get(i).getTotal()%></td>
			<td><a href="HistorialPedidos?prod=<%=listaPedidos.get(i).getId()%>">Consultar</a></td>
			<td><a href="HistorialPedidos?accion=1&prod=<%=listaPedidos.get(i).getId()%>">Cancelar Pedido</a></td>
		</tr>
		<%
		}
		}
		%>
	</table>
	<br />
	<br />
	<%
	if (consulta != null) {
		ArrayList<DetallePedidoExtVO> listaDetallePedido = (ArrayList<DetallePedidoExtVO>) request
		.getAttribute("listaDetallePedido");
	%>
	<br />
	<h5 class="text-center">
		Detalle de Pedidos del Cliente
		</h3>
		<br />
		<table class="table table-striped">
			<tr>
				<td>Pedido</td>
				<td>Producto</td>
				<td>Precio Unidad</td>
				<td>Unidades</td>
				<td>Impuesto</td>
				<td>Total</td>
			</tr>
			<%
			if (listaPedidos != null) {
				for (int i = 0; i < listaDetallePedido.size(); i++) {
			%>
			<tr>
				<td><%=listaDetallePedido.get(i).getId_pedido()%></td>
				<td><%=listaDetallePedido.get(i).getProducto()%></td>
				<td><%=listaDetallePedido.get(i).getPrecio_unidad()%></td>
				<td><%=listaDetallePedido.get(i).getUnidades()%></td>
				<td><%=listaDetallePedido.get(i).getImpuesto()%></td>
				<td><%=listaDetallePedido.get(i).getTotal()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>
		<%
		}
		%>
		<br/><br/>
		<%@ include file="../includes/pie.jsp"%>
</body>
</html>
