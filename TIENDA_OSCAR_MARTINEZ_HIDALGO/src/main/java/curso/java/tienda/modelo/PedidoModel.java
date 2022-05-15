package curso.java.tienda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import curso.java.tienda.conexion.Conexion;
import curso.java.tienda.vo.PedidoVO;
import curso.java.tienda.vo.ProductoVO;

public class PedidoModel {
	
	public Connection conexion;
	public Statement st;
	
	public ArrayList<PedidoVO> listarPedidos(ArrayList<PedidoVO> lista) throws Exception{		
		PedidoVO pedido;	
		conexion = Conexion.getConexion();

		if (conexion != null) {

			String query = "SELECT * FROM pedidos";

			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				int idUsuario = rs.getInt("usuario_id");
				String fecha = rs.getString("fecha");
				String metodoPago = rs.getString("metodo_pago");
				String estado = rs.getString("estado");
				String numFactura = rs.getString("num_factura");
				Double total = rs.getDouble("total");
				
				pedido = new PedidoVO(id, idUsuario, fecha, metodoPago, estado, numFactura, total);
				lista.add(pedido);
			}
			rs.close();
			ps.close();
		}	
		
		
		return lista;
	}

	public boolean grabarPedidos(PedidoVO pedido) throws Exception {

		boolean ok = true;
		conexion = Conexion.getConexion();
		if (conexion != null) {

			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO pedidos (usuario_id, fecha, metodo_pago, estado, num_factura, total) "
							+ "VALUES (?, ?, ?, ?, ?, ?)");

			ps.setInt(1, pedido.getIdUsuario());
			ps.setString(2, pedido.getFecha());
			ps.setString(3, pedido.getMetodo_pago());
			ps.setString(4, pedido.getEstado());
			ps.setString(5, pedido.getNum_factura());
			ps.setDouble(6, pedido.getTotal());
			int resultado = ps.executeUpdate();

			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido insertar el pedido");
			}
			conexion.commit();
			ps.close();
		}	
		return ok;
	}

	public int obtenerIdPedidos() throws Exception {
		conexion = Conexion.getConexion();
		int id = 0;
		if (conexion != null) {

			String query = "SELECT id FROM pedidos order by id desc limit 1";

			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			ps.close();
		}			
		return id;
	}

	public boolean grabarLineasPedido(int numPedido, String idUsuario, ArrayList<ProductoVO> carrito) throws Exception {
		boolean ok = true;
		
		conexion = Conexion.getConexion();
		if (conexion != null) {
			int resultado = 0;
			double total =0;
			PreparedStatement ps=null;
			for (ProductoVO productoVO : carrito) {
				total = (productoVO.getCantidad()*productoVO.getPrecio()) + (productoVO.getCantidad()*productoVO.getPrecio()*productoVO.getImpuesto());
				ps = conexion.prepareStatement(
						"INSERT INTO detalles_pedido (pedido_id, producto_id, precio_unidad, unidades, impuesto, total) "
								+ "VALUES (?, ?, ?, ?, ?, ?)");
				ps.setInt(1, numPedido);
				ps.setInt(2, productoVO.getId());
				ps.setDouble(3, productoVO.getPrecio());
				ps.setInt(4, productoVO.getCantidad());
				ps.setFloat(5, productoVO.getImpuesto());
				ps.setDouble(6, total);
				resultado = ps.executeUpdate();
			}
			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido insertar el pedido");
			}
			conexion.commit();
			ps.close();
		}	
		return ok;
	}

	public boolean actualizarStock(ArrayList<ProductoVO> carrito) throws Exception {
		boolean ok = true;
		conexion = Conexion.getConexion();
		int cantidad = 0;

		if (conexion != null) {
			PreparedStatement ps=null;
			int resultado = 0;
			for (ProductoVO productoVO : carrito) {
				ps = conexion.prepareStatement("UPDATE productos SET stock = stock - ? WHERE id=?");
				ps.setInt(1, productoVO.getCantidad());
				ps.setInt(2, productoVO.getId());
				resultado = ps.executeUpdate();
			}
			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido actualizar");
			}
			conexion.commit();

			ps.close();			
		}
		return ok;
	}

	public ArrayList<PedidoVO> listaPedidosClienteBD(int idUsuarioI) throws Exception {
		PedidoVO pedido;	
		conexion = Conexion.getConexion();
		ArrayList<PedidoVO> lista = new ArrayList<PedidoVO>();

		if (conexion != null) {

			String query = "SELECT * FROM pedidos where usuario_id = ?";

			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idUsuarioI);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				int idUsuario = rs.getInt("usuario_id");
				String fecha = rs.getString("fecha");
				String metodoPago = rs.getString("metodo_pago");
				String estado = rs.getString("estado");
				String numFactura = rs.getString("num_factura");
				Double total = rs.getDouble("total");
				
				pedido = new PedidoVO(id, idUsuario, fecha, metodoPago, estado, numFactura, total);
				lista.add(pedido);
			}
			rs.close();
			ps.close();
		}	
		
		
		return lista;
	}
	
	public boolean PCPedidoClienteBD(int id) throws Exception {
		boolean ok = true;
		conexion = Conexion.getConexion();

		if (conexion != null) {
			PreparedStatement ps=null;
			int resultado = 0;
			ps = conexion.prepareStatement("UPDATE pedidos SET estado = 'PC' WHERE id=?");
			ps.setInt(1, id);
			resultado = ps.executeUpdate();
			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido actualizar el pedido en PCPedidoClienteBD");
			}
			conexion.commit();

			ps.close();			
		}
		return ok;
	}


	
}
