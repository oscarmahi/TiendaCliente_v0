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
 * Servlet implementation class Carrito
 */
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Carrito() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion;
		String idProductoS;
		Integer idProducto = null;
		String pagina = "./jsp/productos.jsp";

		// PROTEGEMOS LOS PARAMETROS EN EL CASO EN EL QUE VENGAN A NULL
		if (request.getParameter("prod") != null) {
			idProductoS = request.getParameter("prod");
			idProducto = Integer.parseInt(idProductoS);
			//System.out.println("producto "+idProducto);
		}

		if (request.getParameter("accion") != null) {
			accion = request.getParameter("accion");
			//System.out.println("accion: "+accion);
		} else {
			accion = "0";
		}

		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		lista = new ProductoService().validarProductos(lista);
		
		//-------------------------------------------------------------------------------------
		if (request.getSession().getAttribute("carrito") == null){
			request.getSession().setAttribute("carrito", new ArrayList<ProductoVO>());
		}
		//-------------------------------------------------------------------------------------
		

		ArrayList<ProductoVO> carrito = (ArrayList<ProductoVO>) request.getSession().getAttribute("carrito");
		ArrayList<ProductoVO> carritoAux = null;

		String error = null;
		// System.out.println(request.getSession().getAttribute("idUsuario")
		// CASO AÑADIR****************************************************************************
		if (accion.equals("1")) {
			// System.out.println("entra en 1");
			for (ProductoVO producto : lista) {
				if (producto.getId() == idProducto) {
					// System.out.println("linea 64");
					carrito = addProductoCarrito(carrito, idProducto, producto);
					// System.out.println("sumamos la cantidad");
				}
			}
			request.getSession().removeAttribute("carrito");
			request.getSession().setAttribute("carrito", carrito);
		}

		// CASO ELIMINAR*************************************************************************
		else if (accion.equals("2")) {
			int contador = 0;
			carritoAux = new ArrayList<ProductoVO>();
			for (ProductoVO producto : carrito) {
				if (producto.getId() != idProducto) {
					carritoAux.add(producto);
				}
				contador++;
			}
			request.getSession().removeAttribute("carrito");
			request.getSession().setAttribute("carrito", carritoAux);
			pagina = "./jsp/carrito.jsp";
		} else {
			// System.out.println("entra en else");
			pagina = "./jsp/carrito.jsp";
		}

		request.setAttribute("error", error);
		request.setAttribute("listaProductos", lista);
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	/**
	 * Metodo para añadir un producto a un carrito
	 * @param carrito
	 * @param idProducto
	 * @param producto
	 * @return
	 */
	private ArrayList<ProductoVO> addProductoCarrito(ArrayList<ProductoVO> carrito, Integer idProducto,
			ProductoVO producto) {
		boolean enc = false;
		// Buscamos a ver si existe el articulo y actualizamos su cantidad
		for (int i = 0; i < carrito.size(); i++) {
			if (carrito.get(i).getId() == idProducto) {
				carrito.get(i).setCantidad(carrito.get(i).getCantidad() + 1);
				enc = true;
			}
		}

		if (!enc) {
			// En este caso añadimos el articulo por primera vez
			ProductoVO productoComprado = new ProductoVO(producto);
			productoComprado.setCantidad(1);
			carrito.add(productoComprado);
		}

		return carrito;
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
