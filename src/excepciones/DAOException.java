package excepciones;

// EXEPCION DE MENSAJE VERSATIL.
public class DAOException extends Exception {
	
	private String mensaje;
	
	// CONSTRUCTOR DE LA CLASE EL CUAL RECIBE UN MENSAJE DE EXCEPCION Y LO ALMACENA EN LA VARIABLE LOCAL.
	public DAOException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMessage() {
		return mensaje;
	}
}
