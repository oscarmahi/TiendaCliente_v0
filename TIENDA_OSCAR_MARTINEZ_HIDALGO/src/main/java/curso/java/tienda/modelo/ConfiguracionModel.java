package curso.java.tienda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import curso.java.tienda.conexion.Conexion;
import curso.java.tienda.vo.ProductoVO;

public class ConfiguracionModel {
	
	public Connection conexion;
	public Statement st;

	
	public HashMap<String, String> obtenerDatosEmpresa() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		String query = "SELECT * FROM configuracion WHERE clave LIKE ? ";
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		if (conexion != null) {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, "%empresa%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String clave = rs.getString("clave");
				String valor = rs.getString("valor");
				map.put(clave, valor);
			}
			rs.close();
			ps.close();
		}
		return map;
	}


}
