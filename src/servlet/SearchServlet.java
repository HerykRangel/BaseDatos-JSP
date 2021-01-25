package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import baseDatos.ProductoDAO;
import entities.Producto;
import excepciones.ConectionException;
import excepciones.DAOException;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SE ALMACENA EL PARAMETRO RECIBIDO POR EL USARIO EN UNA VARIABLE LOCAL.
		String nombre = request.getParameter("nombre");
		try {
			// SE REALIZA LA CONSULTA A LA BASE DE DATOS Y SE ALMACENA EN UN OBJETO "Producto"
			Producto p = ProductoDAO.getProducto(nombre);
			// SE "settea" EL OBJETO RECIBIDO AL "request" CON EL NOMBRE DE SU CLASE.
			request.setAttribute("producto", p);
			} catch (DAOException e) {
			// SI SE OBTIENE UN ERRRO DE TIPO "DAOException". SE "settea" DICHO MENSAJE EN UN "request" CON EL NOMBRE DE "mensaje"
			request.setAttribute("mensaje", "POR FAVOR INSERTE UN PRODUCTO.");
		} catch (ConectionException e) {
			// SI SE OBTIENE UN ERRRO DE TIPO "ConectionException" SE "settea" DICHO MENSAJE EN UN "request" CON EL NOMBRE DE "mensaje"
			request.setAttribute("mensaje", e.getMessage());
			e.printStackTrace();
		} finally {
			// PASE LO QUE PASE EL SERVLET SE COMUNICA CON "Search.jsp" Y LE ENVIA EL "request" Y "response"
			request.getRequestDispatcher("Search.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
