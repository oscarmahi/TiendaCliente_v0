package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.servicio.ProductoService;
import curso.java.tienda.vo.ProductoVO;

/**
 * Servlet implementation class Catalogo2
 */
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Catalogo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		ArrayList<ProductoVO> listaProductos = null;
		String tipoFiltro = (String) request.getParameter("inputFiltrar");
		if (tipoFiltro == null) {
			tipoFiltro = "Filtro1";
		}
		//System.out.println(tipoFiltro);
		if (tipoFiltro.equals("Filtro1")) {
			String filtro = (String) request.getParameter("filtro");
			// System.out.println(filtro);

			// SI NO HA PULSADO EL BOTON DE FILTRO, MUESTO LOS PRODUCTOS ORDENADOS POR ID
			if (filtro == null) {
				// System.out.println("entra en catalogo");
				listaProductos = new ArrayList<ProductoVO>();
				listaProductos = new ProductoService().validarProductos(listaProductos);
			} else {
				// MUESTRO LOS PRODUCTOS ORDENADOS POR EL CAMPO QUE ME INDIQUE EL USUARIO
				listaProductos = new ArrayList<ProductoVO>();
				listaProductos = new ProductoService().listarProductosFiltro(listaProductos, filtro);
			}

			if (request.getSession().getAttribute("carrito") == null) {
				request.getSession().setAttribute("carrito", new ArrayList<ProductoVO>());
			}

			request.setAttribute("listaProductos", listaProductos);
			request.getRequestDispatcher("./jsp/productos.jsp").forward(request, response);
		}else if (tipoFiltro.equals("Filtro2")){
				String filtroNombre = (String) request.getParameter("filtroNombre");
				String filtroDescripcion = (String) request.getParameter("filtroDescripcion");
				listaProductos = new ArrayList<ProductoVO>();
				listaProductos = new ProductoService().listarProductosFiltroBusqueda(listaProductos, filtroNombre, filtroDescripcion);
				request.setAttribute("listaProductos", listaProductos);
				request.getRequestDispatcher("./jsp/productos.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
