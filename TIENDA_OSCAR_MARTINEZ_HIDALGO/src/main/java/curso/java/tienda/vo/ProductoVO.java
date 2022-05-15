package curso.java.tienda.vo;

/**
 * Clase POJO de productos
 * @author Oscar Martinez Hidalgo
 *
 */
public class ProductoVO {
	
	private int id;
	private int id_categoria;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private String fecha_alta;
	private String fecha_baja;
	private float impuesto;
	private String imagen;
	private int cantidad;
	
	public ProductoVO(int id, int id_categoria, String nombre, String descripcion, double precio, int stock,
			String fecha_alta, String fecha_baja, float impuesto, String imagen, int cantidad) {
		this.id = id;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
		this.cantidad = cantidad;
	}
	

	public ProductoVO(int id_categoria, String nombre, String descripcion, double precio, int stock,
			String fecha_alta, String fecha_baja, float impuesto, String imagen) {
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
	}
	
	public ProductoVO(ProductoVO producto) {
		this.id = producto.getId();
		this.id_categoria = producto.getId_categoria();
		this.nombre = producto.getNombre();
		this.descripcion = producto.getDescripcion();
		this.precio = producto.getPrecio();
		this.stock = producto.getStock();
		this.fecha_alta = producto.getFecha_alta();
		this.fecha_baja = producto.getFecha_baja();
		this.impuesto = producto.getImpuesto();
		this.imagen = producto.getImagen();
		this.cantidad = producto.getCantidad();
	}
	
	public ProductoVO() {
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
}
