package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.modelo.PedidoModel;
import curso.java.tienda.servicio.DetallePedidoService;
import curso.java.tienda.servicio.PedidoService;
import curso.java.tienda.vo.DetallePedidoExtVO;
import curso.java.tienda.vo.PedidoVO;

/**
 * Servlet implementation class HistorialPedidos
 */
public class HistorialPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistorialPedidos() {
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

		//System.out.println(request.getParameter("accion"));
		//System.out.println(request.getParameter("prod"));
		
		if ((request.getParameter("accion") != null) && (request.getParameter("accion").equals("1"))) {
			boolean ok = new PedidoService().PCPedidoCliente((request.getParameter("prod")));
			System.out.println(ok);
		}
		
		if (request.getParameter("prod") != null) {
			String idPedidoS = request.getParameter("prod");
			int idPedido = Integer.parseInt(idPedidoS);
			

			ArrayList<DetallePedidoExtVO> listaDetallePedido = new DetallePedidoService().listaDetallePedidoCliente(idPedido);
			
			//for (DetallePedidoExtVO detallePedidoExtVO : listaDetallePedido) {
		//		System.out.println(detallePedidoExtVO.getId_pedido());
		//	}
			
			
			String idUsuario = (String) request.getSession().getAttribute("idUsuario");
			ArrayList<PedidoVO> listaPedidos = new PedidoService().listaPedidosCliente(idUsuario);
			request.setAttribute("listaPedidos", listaPedidos);
			
			
			request.setAttribute("listaDetallePedido", listaDetallePedido);
			request.setAttribute("consulta", "ok");
			request.getRequestDispatcher("./jsp/historialPedidos.jsp").forward(request, response);
			
		} else {
			String idUsuario = (String) request.getSession().getAttribute("idUsuario");
			ArrayList<PedidoVO> listaPedidos = new PedidoService().listaPedidosCliente(idUsuario);

			request.setAttribute("listaPedidos", listaPedidos);
			request.getRequestDispatcher("./jsp/historialPedidos.jsp").forward(request, response);
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
