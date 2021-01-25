package utils;

import java.util.Comparator;
import entities.Producto;

// SE DEFINE UN ORDEN DIFERENTE AL NATURAL
public class OrdenProductosNombre implements Comparator<Producto> {

	@Override
	public int compare(Producto p1, Producto p2) {		
		return p2.getNombre().compareTo(p1.getNombre());
	}

}
