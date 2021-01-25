package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.ConectionException;
import excepciones.DAOException;
import entities.Producto;

public class ProductoDAO {

	// METODO PARA LA INSERCION DE PRODUCTOS A LA BASE DE DATOS.
	public static void inserta(Producto p) throws DAOException, ConectionException{
		// SE CREA LA CONEXION.
		Connection con = null;
		// SE CREA EL "PreparedStatement"
		PreparedStatement pst = null;
		// SE ENCIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR UN ERROR EN AL SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR UN ERROR EN LA CONEXION 
			try {
			// SE CREA LA CONEXION.
			con = AdministradorDeConexion.obtenerConexion();
			}catch(Exception e) {
				throw new ConectionException();
			}
			// MEDIANTE EL METODO "existe" SE CORROBORA SI EL PRODUCTO EXISTE EN LA BASE DE DATOS
			if (existe(p.getNombre())) {
			// SI EL PRODUCTO EXISTE - LANZA EL SIGUIENTE ERROR.
				throw new DAOException("EL PRODUCTO INGRESADO YA EXISTE EN LA BASE DE DATOS.");
			}
			// SE CREA LA SENTENCIA "sql" DE INSERCION A LA BASE DE DATOS.
			String sql = "INSERT INTO Productos (nombre, descripcion, precio) VALUES (?,?,?)";
			// SE ENVIA LA LA SENTENCIA MEDIANTE EL "PreparedStatement"
			pst = con.prepareStatement(sql);
			// SE "settea" LOS VALORES A LA SENTENCIA "sql"
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getDescripcion());
			pst.setDouble(3, p.getPrecio());
			// SE EJECUTA LA INSERCION - SE ENVIAR EL "PreparedStatement"
			pst.execute();
			// INFORMAR.
			System.out.println("EL PRODUCTO HA SIDO INSERTADO DE FORMA CORRECTA A SU BASE DE DATOS");
		// SE ATRAPA ERROR EN SENTENCIA "SQL"
		} catch (SQLException e){
			//e.printStackTrace();
			throw new DAOException("ERROR EN LA SENTENCIA SQL (INSERTAR)");
		// SIEMPRE PASE LO QUE PASE SE DEBE CERRAR LA CONEXION Y EL "PreparedStatement"
		} finally {
			try {
				// SE CIERRA EL "PreparedStatement"
				pst.close();
				// SE CIERRA LA CONEXION
				con.close();
			}catch (Exception e) {	
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException("ERROR EN EL CIERRE DE LA CONEXION");  
				
			}
		}
	}

	// METODO PARA LA EXISTENCIA DE UN PRODUCTO EN LA BASE DE DATOS - DEVUELVE UN BOOLEAN.
	public static boolean existe(String nombre) throws DAOException, ConectionException {
		// SE DEFINE LA CONEXION.
		Connection con = null;
		// SE DEFINE EL "ResultSet" - RESULTADO DE LA BUSQUEDA.
		ResultSet rs = null;
		// SE DEFINE EL "PreparedStatement"
		PreparedStatement pst = null;
		// SE DEFINE LA RESPUESTA DEL METODO.
		boolean respuesta = false;
		// SE ENCIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR UN ERROR EN AL SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE CONEXION 
			try {
				// SE CREA LA CONEXION.
				con = AdministradorDeConexion.obtenerConexion();
			// SE ATRAPA ERROR EN LA CONEXION.
			} catch(Exception e) {
				throw new ConectionException();
			}
			// SE CREA LA SENTENCIA "sql" DE SELECION A LA BASE DE DATOS.
			String sql = "SELECT * FROM Productos WHERE NOMBRE = ?";
			// SE ENVIA LA LA SENTENCIA MEDIANTE EL "PreparedStatement"
			pst = con.prepareStatement(sql);
			// SE "settea" EL VALOR A LA SENTENCIA "sql"
			pst.setString(1, nombre);
			// SE EJECUTA EL "ResultSet"
			rs = pst.executeQuery();
			// SI EXISTE UN VALOR SIGUIENTE.
			if (rs.next()) {
				respuesta = true;
			}
		// SE ATRAPA ERROR EN SENTENCIA SQL
		} catch(SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN AL SENTENCIA SQL (METODO EXISTE)");
		} finally {
			try {
				// SE CIERRA EL "ResultSet"
				rs.close();
				// SE CIERRA EL "PreparedStatement"
				pst.close();
				// SE CIERRA LA CONEXION.
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
		// SE RETORNA LA RESPUESTA DEL METODO.
		return respuesta;
	}

	// METODO PARA LA MODIFICACION DE LOS PRODUCTOS A LA BASE DE DATOS.
	public static void modifica(Producto p) throws DAOException, ConectionException {
		// SE DEFINE LA CONEXION
		Connection con = null;
		// SE CREA EL "PreparedStatement"
		PreparedStatement pst = null;
		// SE ENCIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR UN ERROR EN AL SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE CONEXION 
			try {
				// SE CREA LA CONEXION.
				con = AdministradorDeConexion.obtenerConexion();
			// SE ATRAPA ERROR EN CONEXION.
			} catch (Exception e) {
				throw new ConectionException();
			}
			// SE APLICA EL METODO "getProducto" POR "nombre" PARA CORROBORAR LA EXISTENCIA DEL MISMO.
			// IGUALO EL METODO "getProducto" A UN OBJETO DE TIPO "Producto" PARA EXTRAER SU ID YA QUE LO OBJETOS "Productos" CREADOS DESDE EL MAIN, NO POSEEN ID
			Producto p1 = getProducto(p.getNombre());
			if (p1 == null) {
				// MENSAJE POR SI EL PRODUCTO NO EXISTE.
				throw new DAOException("EL PRODUCTO NO EXISTE");
			}
			// SE CREA LA SENTENCIA "sql" DE MODIFICACION A LA BASE DE DATOS.
			String sql = "UPDATE Productos SET descripcion = ?, precio = ? WHERE nombre = ?";
			// SE CREA EL "PreparedStatement"
			pst = con.prepareStatement(sql);
			// SE "settean" LOS VALORES A LA SENTENCIA "sql"
			pst.setString(1, p.getDescripcion());
			pst.setDouble(2, p.getPrecio());
			pst.setString(3, p.getNombre());
			// SE INSERTA EL ID OBTENIDO DE LA BASE DE DATOS AL OBJETO AL OBJETO CREADO DESDE EL MAIN 
			p.setId(p1.getId());
			// SE EJECUTA EL "PreparedStatement"
			pst.execute();
			// SE INFORMA
			System.out.println("EL PRODUCTO HA SIDO MODIFICADO EXITOSAMENTE.");
			System.out.println(p);
		// SE ATRAPA ERROR EN LA SENTENCIA SQL
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN LA SENTENCIA SQL (MODIFICAR)");
		} finally {
			try {
				// SE CIERRA EL "PreparedStatement"
				pst.close();
				// SE CIERRA LA CONEXION.
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
	}

	// METODO PARA OBTENER UN PRODUCTO DE LA BASE DE DATOS USANDO SU NOMBRE
	public static Producto getProducto(String nombre) throws DAOException, ConectionException {
		// SE DEFINE LA CONEXION.
		Connection con = null;
		// SE DEFINE UN OBJETO
		Producto p = new Producto();
		// SE DEFINE EL "ResultSet"
		ResultSet rs = null;
		// SE DEFINE EL "PreparedStatement"
		PreparedStatement pst = null;
		// SE ENCIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR UN ERROR EN AL SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE CONEXION 
			try {
				con = AdministradorDeConexion.obtenerConexion();
			// SE ATRAPA UN ERROR EN LA CONEXION.
			} catch (Exception e) {
				throw new ConectionException();
			}
			// SE CREA LA SENTENCIA "sql" DE MODIFICACION A LA BASE DE DATOS.
			String sql = "SELECT * FROM Productos WHERE nombre = ?";
			// SE CREA EL "PreparedStatement"
			pst = con.prepareStatement(sql);
			// SE "settean" LOS VALORES A LA SENTENCIA "sql"
			pst.setString(1, nombre);
			// SE CREA EL "ResultSet"
			rs = pst.executeQuery();
			// SI EXISTE UN VALOR SIGUIENTE.
			if (rs.next()) {
				// SE ALMACENAN LOS VALORES EN VARIABLES LOCALES. - EL NOMBRE SE RECIBE COMO PARAMETRO DEL METODO.
				int id = rs.getInt("id");
				double precio = rs.getDouble("precio");
				String descripcion = rs.getString("descripcion");
				// SE CREA UN OBJETO DE LA CLASE "Producto" Y SE "settean" LOS VALORES OBTENIDO.
				p.setId(id);
				p.setNombre(nombre);
				p.setDescripcion(descripcion);
				p.setPrecio(precio);
				// IFORMAR
				System.out.println("EL PRODUCTO HA SIDO UBICADO DE FORMA EXITOSA.");
				System.out.println(p);
			} else {
				// MENSAJE DADO EL CASO QUE EL PRODUCTO NO EXISTA.
				throw new DAOException("EL PRODUCTO NO EXISTE");
			}
		// SE ATRAPA ERROR EN AL SENTENCIA SQL
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN AL SENTECIA SQL (BUSQUEDA POR NOMBRE)");
		} finally {
			try {
				// SE CIERRA EL "ResultSet"
				rs.close();
				// SE CIERRA EL "PreparedStatement"
				pst.close();
				// SE CIERRA LA CONEXION.
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
		// EL METODO RETORNA UN OBJETO "Producto"
		return p;
	}

	// METODO PARA OBTENER UN PRODUCTO DE LA BASE DE DATOS USANDO SU ID - SOBRE CARGA DE METODOS.
	public static Producto getProducto(int id) throws DAOException, ConectionException {
		// SE DEFINE LA CONEXION.
		Connection con = null;
		// SE DEFINE UN OBJETO
		Producto p = new Producto();
		// SE DEFINE EL "ResultSet"
		ResultSet rs = null;
		// SE DEFINE EL "PreparedStatement"
		PreparedStatement pst = null;
		// SE CIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE CONEXION 
			try {
				// SE CREA LA CONEXION.
				con = AdministradorDeConexion.obtenerConexion();
			} catch (Exception e) {
				throw new ConectionException();
			}
			// SE CREA LA SENTENCIA "sql" DE MODIFICACION A LA BASE DE DATOS.
			String sql = "SELECT * FROM Productos WHERE id = ?";
			// SE CREA EL "PreparedStatement"
			pst = con.prepareStatement(sql);
			// SE "settean" LOS VALORES A LA SENTENCIA "sql"
			pst.setInt(1, id);
			// SE CREA EL "ResultSet"
			rs = pst.executeQuery();
			// SI EXISTE UN VALOR SIGUIENTE.
			if (rs.next()) {
				// SE ALMACENAN LOS VALORES EN VARIABLES LOCALES. - EL NOMBRE SE RECIBE COMO PARAMETRO DEL METODO.
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				double precio = rs.getDouble("precio");
				// SE CREA UN OBJETO DE LA CLASE "Producto" Y SE "settean" LOS VALORES OBTENIDO.
				p.setId(id);
				p.setNombre(nombre);
				p.setDescripcion(descripcion);
				p.setPrecio(precio);
				// IFORMAR
				System.out.println("EL PRODUCTO HA SIDO UBICADO DE FORMA EXITOSA.");
				System.out.println(p);
			} else {
				// MENSAJE DADO EL CASO QUE EL PRODUCTO NO EXISTA.
				throw new DAOException("EL PRODUCTO NO EXISTE");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN LA SENTENCIA SQL (BUSQUEDA POR ID)");
		} finally {
			try {
				// SE CIERRA EL "ResultSet"
				rs.close();
				// SE CIERRA EL "PreparedStatement"
				pst.close();
				// SE CIERRA LA CONEXION.
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
		// EL METODO RETORNA UN OBJETO "Producto"
		return p;
	}
	
	// METODO PARA ELIMINAR UN PRODUCTO DE LA BASE DE DATOS.
	public static void elimina(int id) throws DAOException, ConectionException  {
		// SE DEFINE LA CONEXION.
		Connection con = null;
		// SE DEFINE EL "PreparedStatement" 
		PreparedStatement pst = null;
		// SE APLICA EL METODO "getProducto" POR "id" PARA CORROBORAR LA EXISTENCIA DEL MISMO.
		if (getProducto(id) == null) {
			throw new DAOException("EL PRODUCTO NO EXISTE");
		}
		// SE CIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR  ERROR DE CONEXION
			try {
				// SE DEFINE LA CONEXION
				con = AdministradorDeConexion.obtenerConexion();
			// SE ATRAPA ERROR EN CONEXION.
			} catch (Exception e) {
				throw new ConectionException();
			}
			// SE CREA LA SENTENCIA "sql" PARA LA ELIMINACION DE PRODUCTOS.
			String sql = "DELETE FROM Productos WHERE id = ?";
			// SE CREA EL "PreparedStatement" 
			pst = con.prepareStatement(sql);
			// SE INSERTA LOS VALORES A LA SENTENCIA "sql"
			pst.setInt(1, id);
			// SE EJECUTA EL "PreparedStatement" 
			pst.execute();
			// INFORMAR
			System.out.println("EL PRODUCTO HA SIDO ELIMINADO DE FORMA EXITOSA");
		// SE ATRAPA ERROR EN SENTENCIA SQL
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN SENTENCIA SQL (ELIMINAR)");
		} finally {
			try {
				pst.close();
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException ("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
	}

	// METODO QUE DEVUELVE UNA COLECCION DE OBJETOS DE TIPO PRODUCTO.
	public static ArrayList<Producto> getArrayList() throws DAOException, ConectionException {
		// SE DEFINE LA COLECCION DE OBJETOS DE TIPO "Productos"
		ArrayList<Producto> productos = new ArrayList<Producto>();
		// SE CREA LA CONEXION
		Connection con = null;
		// SE CREA EL "ResultSet"
		ResultSet rs = null;
		// SE CREA EL "PreparedStatement"
		PreparedStatement st = null;
		// SE CIERRA TODO EL CODIGO EN UN "try" - "catch" PARA ATRAPAR EL ERROR DE SENTENCIA SQL
		try {
			// SE CIERRA LA CONEXION EN UN "try" - "catch" PARA ATRAPAR  ERROR DE CONEXION
			try {
				con = AdministradorDeConexion.obtenerConexion();
			// SE ATRAPA EL ERROR EN CONEXION.
			}catch (Exception e) {
				throw new ConectionException();
			}
			// SE DEFINE LA SENTENCIA SQL
			String sql = "select * from Productos";
			// SE DEFINE EL "PreparedStatement"
			st = con.prepareStatement(sql);
			// SE DEFINE EL "ResultSet"
			rs = st.executeQuery();
			// SE GENERA UN ITERADOR - Object next(), retorna el siguiente elemento en la iteración.
			while (rs.next()) {
				// SE CREA UN OBJETO "Producto" EL CUAL SERA COMPLETADO Y INSERTADO A LA LISTA.
				Producto p = new Producto();
				double precio = rs.getDouble("precio");
				String nombre = rs.getString("nombre");
				String desc = rs.getString("descripcion");
				int id = rs.getInt("id");
				p.setDescripcion(desc);
				p.setId(id);
				p.setPrecio(precio);
				p.setNombre(nombre);
				// SE INSERTA EL OBJETO AL "ArrayList" productos
				productos.add(p);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DAOException("ERROR EN SENTENCIA SQL (getArrayList)");
		} finally {
			try {
				// SE CIERRA EL "ResultSet"
				rs.close();
				// SE CIERRA EL "PreparedStatement"
				st.close();
				// SE CIERRA LA CONEXION 
				con.close();
			} catch (Exception e) {
				// MENSAJE DE ERROR QUE SE ARROJA SIEMPRE QUE NO SE RESUELVA EL PEDIDO
				//throw new DAOException ("ERROR EN EL CIERRE DE LA CONEXION");
			}
		}
		// SE RETORNA LA LISTA.
		return productos;
	}
}
