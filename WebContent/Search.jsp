<!-- SE IMPORTA LA CLASE "Producto"  -->
<%@ page import = "entities.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BUSCADOR DE PRODUCTOS</title>

	<!-- ESTILO DE LOS ELEMETOS HTML -->
	<style>
	
		body{
			background-color: #333;
			text-align: center;
			}

		h1{
			color: white;
			font-size: 40px;
			padding-top: 20px;
			width: 100%;
		}

		label{
			color: white;
			display: inline - block
		}

		div{
			align-content: center;
			width: 100%;
		}

		table{
			text-align: center; 
			width: 600px; 
			background-color: white; 
			font-family: Courier, monospace; 
			margin-left: auto; 
			margin-right: auto; 
			padding: 10px; 
			border-color: black; 
			border-style: groove; 
			border-width: 5px"
		}
		
		tr{
			background-color: green;
		}
			
	</style>
</head>
<body>
	
	<h1>BUSCADOR DE PRODUCTOS</h1> 
	
	<!-- CREACION DE UN FORMULARIO:
			action: NOMBRE DEL "Servlet" DE JAVA QUE RECIBIRA LA INFORMACION
			method: METODO PARA ENVIAR DATOS "get" "post"
	 -->
	<form action = "SearchServlet" method = "POST">
	
	<div>
		<label for="nombre"><strong>Producto:</strong></label>
		<input id="nombre" type="text" name= "nombre" placeholder="  Ingrese el nombre del Producto  " style="width:15%"/>
	</div><br>
	
		<input type="submit" value = "Buscar" style="width:5%;display: inline - block">
		<input type="reset" value = "Limbiar" style="width:5%;display: inline - block">
	</form>
	
	<br><br>
	
	<!-- SI EL "request" POSEE UN ATRIBUTO MENSAJE (mensaje de error) SE MUESTRA -->
	<label>
		<% if (request.getAttribute("mensaje") != null){
			out.print(request.getAttribute("mensaje"));
			}
		%>
	</label>
	<br><br>
	
	<!-- SI EL "request" POSEE UN ATRIBUTO PRODUCTO (OBJETO DE LA BUSQUEA) SE MUESTRA -->
	<table>
		<caption style="color:black; background: white; font-size: 30px;"><strong>PRODUCTO</strong></caption> 
		<tr valign="top" style="font-family: : Courier; background-color: black; color: white;"> 
			<th style="padding: 5px;"> ID </th>
			<th style="padding: 5px;"> NOMBRE </th>
			<th style="padding: 5px;"> PRECIO </th>
			<th style="padding: 5px;"> DESCRIPCION </th>
		</tr>
		
	<% if (request.getAttribute("producto") != null) {
		
		// SE PARCEA EL "request" A UN OBJETO DE TIPO "Producto"
		Producto p = (Producto) request.getAttribute("producto");
		
		// SE MUESTRA LOS ELEMENTOS DE LA CONSULTA
		out.print("<tr>");
			out.print("<td>" + p.getId() + "</td>");
			out.print("<td>" + p.getNombre() + "</td>");
			out.print("<td>" + p.getPrecio() + "$ARG" +"</td>");
			out.print("<td>" + p.getDescripcion() + "</td>");
		out.print("</tr>");
	}
	%>
	</table>
	
</body>
</html>