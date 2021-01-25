package excepciones;

// EXCEPSION DE MENSAJER STATICO PARA PROBLEMAS DE CONEXION.
public class ConectionException extends Exception {
	
	private static String mensaje = "HA OCURRIDO UN PROBLEMA EN LA CONEXION \nINTENTE MAS TARDE..";
	
	public String getMessage() {
		return mensaje;
	}
}
