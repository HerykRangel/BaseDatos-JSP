# BaseDatos-JSP
El siguiente proyecto tiene como objetivo realizar diversas consultas individuales o lista de objetos de tipo “Productos” a una base de datos local (MySQL) en lenguaje “SQL” desde un formulario creado en una página JSP (Java Server Pages) utilizando servlets como herramienta de interacción con las demás clases del proyecto y trabajando en un servidor local (ApacheTomcat).

Se posee una clase llamada “AdministradorDeConexion” la cual establece la conexión con la base de datos a usar (MySQL en este caso). Seguidamente se tiene la clase llamada “ProductoDAO” donde se definen todos los métodos de consulta, inserción, modificación y eliminación a la base de datos usando los métodos de la clase “PreparedStatement”.

Como entidad única y principal se posee la clase “Producto” en la cual se definen los atributos (id, nombre, descripción, precio) de la clase y sus métodos. El atributo “id” no está definido dentro del constructor de la clase ya que dicho atributo es establecido de forma automática por la base de datos.

En la clase “SearchServlet()” se definen los métodos  “doGet” y “doPost” donde se almacena el parámetro recibido por el usuario desde la página JSP, seguidamente se realiza la consulta a la base de datos, se setea el atributo recibido en el “request” y finalmente se envía tanto el “request” y “response” a la página JSP.

La página JSP se construyó usando un formulario básico que apunta hacia la clase “SearchServlet()” donde se realiza una consulta de existencia por nombre de producto y se imprime si la consulta fue exitosa o no. 

En el “main” del proyecto se encuentra la aplicación individual a modo de prueba de todos los métodos definidos en la clase “ProductoDAO” con el objetivo de corroborar el funcionamiento de estos.

Heryk Rangel

