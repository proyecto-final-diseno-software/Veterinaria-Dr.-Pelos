# Veterinaria Dr.Pelos
Proyecto del Segundo Parcial para la materia Diseño de Software de la ESPOL.

## Descripcion:
Se realizó un diseño completo de la aplicación para la empresa Dr. Pelos.
La empresa Dr. Pelos brinda servicios (atención veterinaria, peluquería etc.) y productos veterinarios (medicinas, juguetes, ropas, alimentos, etc.) en algunos puntos de la ciudad a través de una agencia principal y sucursales. 

Dr. Pelos importa productos veterinarios para la venta en sus locales al por menor y al por mayor con precios diferenciados.

La aplicación esta escrita en Java y se encuentra conectada a una base de datos Dr.Pelos. Para el diseño se utilizó la herramienta *Visual Paradigm* y los diagramas UML se los puede encontrar en la carpeta *Diagrama*

## Funcionalidad
La aplicación cuenta con los componentes: Clientes, Inventario, Proovedores, Reportes, Ventas, Traslado y Facturación. Las funcionalidades Añadir Cliente, Realizar Venta, Buscar y Filtrar Productos, Consultar Entrega de Producto y Traslado de Mascotas estan completamente implementadas. 

## ¿Cómo utilizar la aplicación?

### Base de datos
La base de datos se la realizó en *MySQL*. Para ejecutar el programa, se debe ejecutar el SQL Script que se encuentra en la siguiente ruta:
```
 ..\res\TablasBdDrPelosCentral.sql
```
### Aplicación
La aplicación se la realizó en *JAVA*. Para acceder a esta, se debe dirigir a la siguiente ruta y ejecutar el archivo
```
..\dist\VeterinariaDrPelos.jar
```

Para ingresar al programa se puede utilizar las credenciales (usuario y contraseña) de cualquier persona registrada como *Personal de Caja*. Estos usuario se encuentran en la base de datos. A modo de ejemplo, puede acceder con las siguientes credenciales:
```
usuario = mromero
contraseña = caja
```



