package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.servicio.PedidoService;
import curso.java.tienda.vo.ProductoVO;

/**
 * Servlet implementation class PantallaPedido
 */
public class PantallaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PantallaPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String error = null;
//		String formaPago = (String) request.getParameter("formaPago");
//		if (formaPago != null) {
//			if (request.getSession().getAttribute("idUsuario") != null) {
//				ArrayList<ProductoVO> carrito = (ArrayList<ProductoVO>) request.getSession().getAttribute("carrito");
//
//				String idUsuario = (String) request.getSession().getAttribute("idUsuario");
//				String totalS = (String) request.getParameter("totalS");
//				// String formaPago = (String) request.getParameter("formaPago");
//
//				boolean okStock = new PedidoService().hayStockPedido(carrito);
//
//				if (okStock) {
//					String applicationPath = request.getServletContext().getRealPath("");
//					boolean ok = new PedidoService().grabarPedido(carrito, idUsuario, totalS, formaPago,
//							applicationPath);
//					request.getSession().removeAttribute("carrito");
//					// request.getRequestDispatcher("productos.jsp").forward(request, response);
//				} else {
//					error = "No hay stock suficiente. Revise carrito";
//				}
//
//				request.setAttribute("error", error);
//				request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
//			} else {
//				error = "Es necesario Logarse para poder comprar";
//				request.setAttribute("error", error);
//				request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
//			}
//		} else {
//			error = "Es necesario seleccionar una forma de pago";
//			request.setAttribute("error", error);
//			request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
//		}
//		
		request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String error = null;
		String formaPago = (String) request.getParameter("formaPago");
		if (formaPago != null) {
			if (request.getSession().getAttribute("idUsuario") != null) {
				ArrayList<ProductoVO> carrito = (ArrayList<ProductoVO>) request.getSession().getAttribute("carrito");

				String idUsuario = (String) request.getSession().getAttribute("idUsuario");
				String totalS = (String) request.getParameter("totalS");
				// String formaPago = (String) request.getParameter("formaPago");

				boolean okStock = new PedidoService().hayStockPedido(carrito);

				if (okStock) {
					String applicationPath = request.getServletContext().getRealPath("");
					boolean ok = new PedidoService().grabarPedido(carrito, idUsuario, totalS, formaPago,
							applicationPath);
					request.getSession().removeAttribute("carrito");
					// request.getRequestDispatcher("productos.jsp").forward(request, response);
				} else {
					error = "No hay stock suficiente. Revise carrito";
				}

				request.setAttribute("error", error);
				request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
			} else {
				error = "Es necesario Logarse para poder comprar";
				request.setAttribute("error", error);
				request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
			}
		} else {
			error = "Es necesario seleccionar una forma de pago";
			request.setAttribute("error", error);
			request.getRequestDispatcher("./jsp/pedidos.jsp").forward(request, response);
		}		
		
	}

}
