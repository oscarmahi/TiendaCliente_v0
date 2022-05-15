package curso.java.tienda.vo;

/**
 * Clase POJO de pedido
 * @author Oscar Martinez Hidalgo
 *
 */
public class PedidoVO {

	private int id;
	private int idUsuario;
	private String fecha;
	private String metodo_pago;
	private String estado;
	private String num_factura;
	private Double total;
	
	 /**
	  * ‘pendiente envío’ (PE), ‘pendiente cancelación’ (PC), ‘enviado’ (E) o ‘cancelado’ (C)
	  * 
	  */
	
	public PedidoVO(int id, int idUsuario, String fecha, String metodo_pago, String estado, String num_factura, Double total) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.metodo_pago = metodo_pago;
		this.estado = estado;
		this.num_factura = num_factura;
		this.total = total;
	}

	
	public PedidoVO(int idUsuario, String fecha, String metodo_pago, String estado, String num_factura, Double total) {
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.metodo_pago = metodo_pago;
		this.estado = estado;
		this.num_factura = num_factura;
		this.total = total;
	}
	
	public PedidoVO() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
