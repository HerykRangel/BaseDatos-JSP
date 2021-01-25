package entities;

public class Producto {
	
	private int id;
	private double precio;
	private String nombre, descripcion;

	// CONSTRUCTOR VACIO 
	public Producto() {
	}

	// NO RECIBE EL ATRIBUTO "id" EN SU CONSTRUCTOR YA QUE SE LE ASIGNA DE FORMA AUTOMATICA EN LA BASE DATOS.
	public Producto(String nombre, String descripcion, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	// "toString" DE LA CLASE.
	@Override
	public String toString() {
		return "ID = " + id + " - NOMBRE = " + nombre +" - PRECIO = " + precio + " ARS\nDESCRIPCION =" + descripcion;
	}

	// METODOS "getter" Y "setter"
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
}
