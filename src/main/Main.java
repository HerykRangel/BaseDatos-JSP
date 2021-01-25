package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import baseDatos.ProductoDAO;
import entities.Producto;
import excepciones.ConectionException;
import excepciones.DAOException;
import utils.OrdenProductosNombre;

public class Main {
	
public static void main(String[] args) {
	
	
	// METODO QUE MUESTRA LA COLECCION DE PRODUCTOS EXTRAIDA DE LA BASE DE DATOS.
	try {
		ArrayList<Producto> productos = ProductoDAO.getArrayList();				
		Collections.sort(productos, new OrdenProductosNombre());											
		for (Producto p : productos) {	
			System.out.println(p);
		}
		
	} catch (ConectionException e) {
		e.printStackTrace();
	} catch (DAOException e) {
		e.printStackTrace();
	}
	
		// METODO PARA INSERTAR PRODUCTOS A LA BASE DE DATOS.
	/*	Producto p1 = new Producto("Pepsi","Bebida", 20);
		try {
			ProductoDAO.inserta(p1);
		} catch (ConectionException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	*/	
		// METOO PARA OBTENER UN PRODUCTO DE LA BASE DE DATOS SEGUN SU NOMBRE.
	/*	try {
			ProductoDAO.getProducto("Pepsi");
		} catch (ConectionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/	
		
		// METODO PARA MODIFICAR DATOS SEGUN SUN NOMBRE
	/*	Producto p2 = new Producto("Chicha", "Bebida", 40);
		try {
			ProductoDAO.modifica(p2);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ConectionException e) {
			e.printStackTrace();
		}
	*/	
		
		// METOO PARA OBTENER UN PRODUCTO DE LA BASE DE DATOS SEGUN SU ID.
	/*	try {
			ProductoDAO.getProducto(3);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ConectionException e) {
			e.printStackTrace();
		}
	*/
		
		
		// METODO PARA ELIMINAR UN PRODUCTO DE LA BASE DE DATOS SEGUN SU ID.
	/*	try {
			ProductoDAO.elimina(7);
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ConectionException e) {
			e.printStackTrace();
		}
	*/		
		
	}
}
