package curso.java.tienda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import curso.java.tienda.conexion.Conexion;
import curso.java.tienda.vo.ProductoVO;

public class ProductoModel {
	
	public Connection conexion;
	public Statement st;

	public ArrayList<ProductoVO> listarProductos(ArrayList<ProductoVO> listaProductos) throws SQLException {
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		if (conexion != null) {
			String query = "SELECT * FROM productos";
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int id_categoria = rs.getInt("categorias_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String fecha_alta = rs.getString("fecha_alta");
				String fecha_baja = rs.getString("fecha_baja");
				float impuesto = rs.getFloat("impuesto");
				String imagen = rs.getString("imagen");
				
				producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
				listaProductos.add(producto);
			}
			rs.close();
			ps.close();
		}	
		return listaProductos;
	}
	
	public ArrayList<ProductoVO> listaDeProductos() throws SQLException {
		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		if (conexion != null) {
			String query = "SELECT * FROM productos";
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int id_categoria = rs.getInt("categorias_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String fecha_alta = rs.getString("fecha_alta");
				String fecha_baja = rs.getString("fecha_baja");
				float impuesto = rs.getFloat("impuesto");
				String imagen = rs.getString("imagen");
				
				producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
				lista.add(producto);
			}
			rs.close();
			ps.close();
		}	
		return lista;
	}	
	

	public  ArrayList<ProductoVO> listarProductorPaginados(ArrayList<ProductoVO> listaProductos, int start, int total) throws Exception {
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		if (conexion != null) {
			String query = "select * from productos limit " + (start - 1) + "," + total;
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int id_categoria = rs.getInt("categorias_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String fecha_alta = rs.getString("fecha_alta");
				String fecha_baja = rs.getString("fecha_baja");
				float impuesto = rs.getFloat("impuesto");
				String imagen = rs.getString("imagen");
				
				producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
				listaProductos.add(producto);
			}
			rs.close();
			ps.close();
		}	
		return listaProductos;		
	}
	
	
	public int getNumeroProductos() throws Exception {	
			conexion = Conexion.getConexion();
			if (conexion != null) {			
				PreparedStatement ps = conexion.prepareStatement("select count(id) from productos");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		return 0;
	}
			
	
	public boolean grabarListaDeProductos(ArrayList<ProductoVO> listaGrabar) throws SQLException {
		boolean ok = true;
		int resultado=0;
		PreparedStatement ps=null;
		conexion = Conexion.getConexion();
		if (conexion != null) {

			for (int i = 0; i < listaGrabar.size(); i++) {
				ps = conexion.prepareStatement(
						"INSERT INTO productos (categorias_id,nombre,descripcion,precio,stock,fecha_alta, fecha_baja,impuesto,imagen) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

				ps.setInt(1, listaGrabar.get(i).getId_categoria());
				ps.setString(2, listaGrabar.get(i).getNombre());
				ps.setString(3, listaGrabar.get(i).getDescripcion());
				ps.setDouble(4, listaGrabar.get(i).getPrecio());
				ps.setInt(5, listaGrabar.get(i).getStock());
				ps.setString(6, listaGrabar.get(i).getFecha_alta());
				//ps.setString(7, listaGrabar.get(i).getFecha_baja());
				ps.setString(7, null);
				ps.setFloat(8, listaGrabar.get(i).getImpuesto());
				ps.setString(9, null);
				
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

	public ArrayList<ProductoVO> listarProductosFiltroBD(ArrayList<ProductoVO> listaProductos, String filtro) throws Exception {
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		String query="";
		if (conexion != null) {
			if (filtro.equals("todos")) {
				query = "SELECT * FROM productos";
			}else {
				query = "SELECT * FROM productos ORDER BY "+filtro;
			}
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int id_categoria = rs.getInt("categorias_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String fecha_alta = rs.getString("fecha_alta");
				String fecha_baja = rs.getString("fecha_baja");
				float impuesto = rs.getFloat("impuesto");
				String imagen = rs.getString("imagen");
				
				producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
				listaProductos.add(producto);
			}
			rs.close();
			ps.close();
		}	
		return listaProductos;
	}

	public ArrayList<ProductoVO> listarProductosFiltroBusquedaBD(ArrayList<ProductoVO> listaProductos,
			String filtroNombre, String filtroDescripcion) throws Exception {
		String query=null;
		if ((filtroNombre.length() == 0) && (filtroDescripcion.length() == 0)){
			listaProductos= listaDeProductos();
		}else if ((filtroNombre.length() != 0) && (filtroDescripcion.length() == 0)){
			query = "SELECT * FROM productos WHERE nombre LIKE ?";
			listaProductos = listaProductosUnParametro(filtroNombre, listaProductos, query);
		}else if ((filtroNombre.length() == 0) && (filtroDescripcion.length() != 0)){
			query = "SELECT * FROM productos WHERE descripcion LIKE ?";
			listaProductos = listaProductosUnParametro(filtroDescripcion, listaProductos, query);
		}else if ((filtroNombre.length() != 0) && (filtroDescripcion.length() != 0)){
			query = "SELECT * FROM productos WHERE nombre LIKE ? and descripcion LIKE ?";
			ProductoVO producto;	
			conexion = Conexion.getConexion();
			if (conexion != null) {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, "%"+filtroNombre+"%");
				ps.setString(2, "%"+filtroDescripcion+"%");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					int id_categoria = rs.getInt("categorias_id");
					String nombre = rs.getString("nombre");
					String descripcion = rs.getString("descripcion");
					Double precio = rs.getDouble("precio");
					int stock = rs.getInt("stock");
					String fecha_alta = rs.getString("fecha_alta");
					String fecha_baja = rs.getString("fecha_baja");
					float impuesto = rs.getFloat("impuesto");
					String imagen = rs.getString("imagen");
					producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
					listaProductos.add(producto);
				}
				rs.close();
				ps.close();
			}	
		}	
		return listaProductos;
	}

	private ArrayList<ProductoVO> listaProductosUnParametro(String filtro, ArrayList<ProductoVO> listaProductos, String query) throws Exception {
		ProductoVO producto;	
		conexion = Conexion.getConexion();
		if (conexion != null) {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, "%"+filtro+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int id_categoria = rs.getInt("categorias_id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String fecha_alta = rs.getString("fecha_alta");
				String fecha_baja = rs.getString("fecha_baja");
				float impuesto = rs.getFloat("impuesto");
				String imagen = rs.getString("imagen");
				producto = new ProductoVO(id,id_categoria,nombre,descripcion,precio,stock,fecha_alta,fecha_baja,impuesto,imagen,0);
				listaProductos.add(producto);
			}
			rs.close();
			ps.close();
		}	
		return listaProductos;
	}
	
	
	
	
	
}
