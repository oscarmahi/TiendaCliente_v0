package curso.java.tienda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import curso.java.tienda.conexion.Conexion;
import curso.java.tienda.vo.DetallePedidoExtVO;

public class DetallePedidoModel {
	
	public Connection conexion;
	public Statement st;

	public ArrayList<DetallePedidoExtVO> listaDetallePedidoClienteBD(int idP) throws Exception {

		DetallePedidoExtVO detallePedido;	
		conexion = Conexion.getConexion();
		ArrayList<DetallePedidoExtVO> lista = new ArrayList<DetallePedidoExtVO>();

		if (conexion != null) {

			String query = "SELECT * FROM detalles_pedido d, productos p where pedido_id = ? and d.producto_id=p.id";

			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idP);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				int idUsuario = rs.getInt("pedido_id");
				String producto = rs.getString("nombre");
				float precio_unidad = rs.getFloat("precio_unidad");
				int unidades = rs.getInt("unidades");
				float impuesto = rs.getFloat("impuesto");
				Double total = rs.getDouble("total");
				
				detallePedido = new DetallePedidoExtVO(id, idUsuario, producto, precio_unidad, unidades, impuesto, total);
				lista.add(detallePedido);
			}
			rs.close();
			ps.close();
		}	
		
		
		return lista;		

	}

}
