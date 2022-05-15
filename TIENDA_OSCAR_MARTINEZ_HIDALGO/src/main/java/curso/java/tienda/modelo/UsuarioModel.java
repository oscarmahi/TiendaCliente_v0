package curso.java.tienda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jasypt.util.password.StrongPasswordEncryptor;

import curso.java.tienda.conexion.Conexion;
import curso.java.tienda.vo.PedidoVO;
import curso.java.tienda.vo.UsuarioVO;

public class UsuarioModel {

	public Connection conexion;
	public Statement st;

	public int verificarUsuario(UsuarioVO usuario) throws SQLException {
		int ok = 0;
		conexion = Conexion.getConexion();
		if (conexion != null) {
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			
			String query = "SELECT * FROM usuarios WHERE email = ?";
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setString(1, usuario.getEmail());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {				
				//String claveBD = rs.getString("clave");
				//System.out.println(claveBD);
				if (passwordEncryptor.checkPassword(usuario.getClave(), rs.getString("clave"))) {
					ok = rs.getInt("id");
				}else {
					ok = 0;
				}
			}
			rs.close();
			ps.close();
		}
		return ok;
	}
	
	public int verificarEmailUsuario(String eMail) throws SQLException {
		int ok = 0;
		conexion = Conexion.getConexion();
		if (conexion != null) {
			
			String query = "SELECT * FROM usuarios WHERE email = ?";
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setString(1, eMail);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {				
				ok = rs.getInt("id");
			}
			rs.close();
			ps.close();
		}
		return ok;
	}

	public String obtenerRol(int idUsuario) throws Exception {
		String rolM = "";
		conexion = Conexion.getConexion();
		if (conexion != null) {
			String query = "select r.rol from usuarios u, roles r where u.id=? and u.roles_id=r.id";
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setInt(1, idUsuario);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				rolM = rs.getString("r.rol");
				//System.out.println("rol en DAO: "+rolM);
			}
			rs.close();
			ps.close();
		}
		return rolM;
	}

	public UsuarioVO obtenerDatosUsuarioBBDD(int id) throws Exception {
		UsuarioVO usuario = null;
		conexion = Conexion.getConexion();
		if (conexion != null) {
			String query = "select * from usuarios where id=?";
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new UsuarioVO(rs.getInt("roles_id"), rs.getString("email"), rs.getString("clave"),
						rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"),
						rs.getString("direccion"), rs.getString("provincia"), rs.getString("localidad"),
						rs.getString("telefono"), rs.getString("dni"));
			}
			rs.close();
			ps.close();
		}
		return usuario;
	}

	public boolean grabarUsuarioBBDD(UsuarioVO usuario) throws Exception {
		boolean ok = true;
		conexion = Conexion.getConexion();
		if (conexion != null) {
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(usuario.getClave());

			PreparedStatement ps = conexion.prepareStatement(
					"INSERT INTO usuarios (roles_id, email, clave, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono,dni) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, usuario.getId_rol());
			ps.setString(2, usuario.getEmail());
			
			ps.setString(3, encryptedPassword);
			
			ps.setString(4, usuario.getNombre());
			ps.setString(5, usuario.getApellido1());
			ps.setString(6, usuario.getApellido2());
			ps.setString(7, usuario.getDireccion());
			ps.setString(8, usuario.getProvincia());
			ps.setString(9, usuario.getLocalidad());
			ps.setString(10, usuario.getTelefono());
			ps.setString(11, usuario.getDni());

			int resultado = ps.executeUpdate();

			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido insertar el usuario. grabarUsuarioBBDD");
			}
			conexion.commit();
			ps.close();
		}
		return ok;

	}

	public boolean actualizarUsuarioBBDD(int idUsuario, UsuarioVO usuario) throws Exception {
		boolean ok = true;
		conexion = Conexion.getConexion();
		if (conexion != null) {
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(usuario.getClave());
			
			PreparedStatement ps = conexion.prepareStatement(
					"UPDATE usuarios SET `clave`=?,`nombre`=?,`apellido1`=?,`apellido2`=?,`direccion`=?,`provincia`=?,`localidad`=?,`telefono`=?,`dni`=? WHERE id=?");

			ps.setString(1, encryptedPassword);
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellido1());
			ps.setString(4, usuario.getApellido2());
			ps.setString(5, usuario.getDireccion());
			ps.setString(6, usuario.getProvincia());
			ps.setString(7, usuario.getLocalidad());
			ps.setString(8, usuario.getTelefono());
			ps.setString(9, usuario.getDni());
			ps.setInt(10, idUsuario);
			int resultado = ps.executeUpdate();

			if (resultado == 0) {
				ok = false;
				System.out.println("NO se ha podido actualizar el usuario. actualizarUsuarioBBDD");
			}
			conexion.commit();
			ps.close();
		}
		return ok;
	}
}
