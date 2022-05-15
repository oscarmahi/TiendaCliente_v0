package curso.java.tienda.servicio;

import java.util.ArrayList;

import curso.java.tienda.modelo.DetallePedidoModel;
import curso.java.tienda.vo.DetallePedidoExtVO;

public class DetallePedidoService {

	public ArrayList<DetallePedidoExtVO> listaDetallePedidoCliente(int idPedido) {

		ArrayList<DetallePedidoExtVO> lista=null;
		try {
			lista = new DetallePedidoModel().listaDetallePedidoClienteBD(idPedido);
		} catch (Exception e) {
			System.out.println("Error en listaDetallePedidoCliente");
			e.printStackTrace();
		}
		
		return lista;
	}

}
