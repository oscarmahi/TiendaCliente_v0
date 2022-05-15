package curso.java.tienda.servicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import curso.java.tienda.ficherosexternos.FicheroPdf;
import curso.java.tienda.modelo.ConfiguracionModel;
import curso.java.tienda.modelo.PedidoModel;
import curso.java.tienda.modelo.UsuarioModel;
import curso.java.tienda.recursos.Constantes;
import curso.java.tienda.vo.PedidoVO;
import curso.java.tienda.vo.ProductoVO;
import curso.java.tienda.vo.UsuarioVO;

/**
 * Clase de servicio para el pedido
 * @author Formacion
 *
 */
public class PedidoService {
	
	public ArrayList<PedidoVO> validarPedidos(ArrayList<PedidoVO> lista) {
		try {
			lista= new PedidoModel().listarPedidos(lista);
		} catch (Exception e) {
			System.out.println("Error al recuperar klos pedidos");
			e.printStackTrace();
		}
		return lista;
	}

	public boolean grabarPedido(ArrayList<ProductoVO> carrito, String idUsuario, String totalS, String formaPago, String applicationPath) {
		boolean ok = true;		
		//TODO: grabar pedido*****************************************************************
		//clave temporal para el string del numero de factura------------------
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String numFactura = dtf.format(LocalDateTime.now());
		dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String fecha = dtf.format(LocalDateTime.now()); 
	    //----------------------------------------------------------------------
		PedidoVO pedido = new PedidoVO(Integer.parseInt(idUsuario), fecha, formaPago, Constantes.PENDIENTE_ENVIAR, numFactura, Double.parseDouble(totalS));
		try {
			ok = new PedidoModel().grabarPedidos(pedido);
		} catch (Exception e) {
			System.out.println("Error al grabar el pedido");
			e.printStackTrace();
		}
		
		
		//TODO:grabar lineas de pedido********************************************************
		if (ok) {
			int numPedido = 0;
			try {
				numPedido = new PedidoModel().obtenerIdPedidos();
			} catch (Exception e) {
				System.out.println("Error al obtener el id del pedido");
				e.printStackTrace();
			}
			try {	
				ok = new PedidoModel().grabarLineasPedido(numPedido,idUsuario,carrito);
			} catch (Exception e) {
				System.out.println("Error al actualizar las lineas de pedidos.");
				e.printStackTrace();
			}
			
			
			//TODO: actualizar stock*************************************************************
			if (ok) {
				try {
					ok = new PedidoModel().actualizarStock(carrito);
				} catch (Exception e) {
					System.out.println("Error al actualizar el stock.");
					e.printStackTrace();
				}
			}
					
			//AHORA IMPRIMO EN PDF LA FACTURA DEL PEDIDO*****************************************************************************************
			//obtener los datos del usuario
			try {
				HashMap<String, String> datosEmpresa = new ConfiguracionModel().obtenerDatosEmpresa();
				FicheroPdf.generarPdf(datosEmpresa, pedido, new UsuarioModel().obtenerDatosUsuarioBBDD(Integer.parseInt(idUsuario)), carrito, applicationPath);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok;
	}

	public boolean hayStockPedido(ArrayList<ProductoVO> carrito) {
		boolean ok = true;
		for (ProductoVO productoVO : carrito) {
			if (productoVO.getCantidad() > productoVO.getStock()) {
				ok = false;
				break;
			}
		}
		return ok;
	}

	public ArrayList<PedidoVO> listaPedidosCliente(String idUsuario) {
		
		ArrayList<PedidoVO> lista = null;
		try {
			int idUsuarioI = Integer.parseInt(idUsuario);
			lista = new PedidoModel().listaPedidosClienteBD(idUsuarioI);
		} catch (Exception e) {
			System.out.println("Error en listaPedidosCliente");
			e.printStackTrace();
		}
		return lista;
	}

	public boolean PCPedidoCliente(String id) {
		boolean ok = true;
		try {
			ok = new PedidoModel().PCPedidoClienteBD(Integer.parseInt(id));	
		} catch (Exception e) {
			System.out.println("Error en PCPedidoCliente");
			e.printStackTrace();
		}
		
		return ok;
	}

}
