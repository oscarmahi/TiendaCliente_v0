package curso.java.tienda.servicio;

import java.sql.SQLException;
import java.util.ArrayList;

import curso.java.tienda.modelo.ProductoModel;
import curso.java.tienda.vo.ProductoVO;

public class ProductoService {

	public ArrayList<ProductoVO> validarProductos(ArrayList<ProductoVO> listaProductos) {

		try {
			listaProductos= new ProductoModel().listarProductos(listaProductos);
		} catch (SQLException e) {
			System.out.println("Error al consultar los productos.");
			e.printStackTrace();
		}

		return listaProductos;
	}

	
	public ArrayList<ProductoVO> listarProductos() {
		ArrayList<ProductoVO> lista=null;

		try {
			lista= new ProductoModel().listaDeProductos();
		} catch (SQLException e) {
			System.out.println("Error al consultar los productos.");
			e.printStackTrace();
		}

		return lista;
	}

	
	public ArrayList<ProductoVO> listarProductosFiltro(ArrayList<ProductoVO> listaProductos, String filtro) {


		try {
			listaProductos= new ProductoModel().listarProductosFiltroBD(listaProductos, filtro);
		} catch (Exception e) {
			System.out.println("Error listarProductosFiltro");
			e.printStackTrace();
		}

		return listaProductos;
	}


	public ArrayList<ProductoVO> listarProductosFiltroBusqueda(ArrayList<ProductoVO> listaProductos,
			String filtroNombre, String filtroDescripcion) {
		
		try {
			listaProductos= new ProductoModel().listarProductosFiltroBusquedaBD(listaProductos, filtroNombre, filtroDescripcion);
		} catch (Exception e) {
			System.out.println("Error listarProductosFiltroBusqueda");
			e.printStackTrace();
		}
		return listaProductos;
	}
	
	
}
