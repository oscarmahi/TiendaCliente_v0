package curso.java.tienda.vo;

/**
 * Clase POJO de detalle de pedido auxiliar para mostrar el nombre del producto
 * @author Oscar Martinez Hidalgo
 *
 */
public class DetallePedidoExtVO {
	
	private int id;
	private int id_pedido;
	private String producto;
	private float precio_unidad;
	private int unidades;
	private float impuesto;
	private double total;
	
	public DetallePedidoExtVO(int id, int id_pedido, String producto, float precio_unidad, int unidades, float impuesto,
			double total) {
		this.id = id;
		this.id_pedido = id_pedido;
		this.producto = producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}

	public DetallePedidoExtVO(int id_pedido, int id_producto, float precio_unidad, int unidades, float impuesto,
			double total) {
		this.id_pedido = id_pedido;
		this.producto = producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}
	
	public DetallePedidoExtVO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public float getPrecio_unidad() {
		return precio_unidad;
	}

	public void setPrecio_unidad(float precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


}
