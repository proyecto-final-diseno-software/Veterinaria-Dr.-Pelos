drop database if exists DrPelosLocal;
create database DrPelosLocal;
use DrPelosLocal;

-- ENTIDADES

DROP TABLE IF EXISTS Persona;
CREATE TABLE Persona(
  cedula varchar(10) NOT NULL,
  nombre varchar(30) NULL DEFAULT "CONSUMIDOR FINAL",
  apellido varchar(30) NULL DEFAULT "CONSUMIDOR FINAL",
  PRIMARY KEY(cedula),
  UNIQUE KEY cedula_unique (cedula)
  );

DROP TABLE IF EXISTS Sucursal;
CREATE TABLE Sucursal(
	sucursal_ID int NOT NULL ,
	nombre int NOT NULL,
	direccion varchar(120) NOT NULL,
    hasServices boolean NOT NULL,
	PRIMARY KEY(sucursal_ID)
  );
  
DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario(
	usuario_ID varchar(10) NOT NULL,
	usuario varchar(45) NOT NULL,
	passwordUser varchar(45) NOT NULL,
	sucursal_ID int NOT NULL,
	isAdmin BOOLEAN NULL DEFAULT False,
	PRIMARY KEY(usuario_ID),
	CONSTRAINT _sucursal_ID FOREIGN KEY (sucursal_ID) REFERENCES Sucursal (sucursal_ID),
	CONSTRAINT _usuario_ID FOREIGN KEY (usuario_ID) REFERENCES Persona (cedula)
);
 
DROP TABLE IF EXISTS Repartidor;
CREATE TABLE Repartidor(
  cedula varchar(10) NOT NULL,
  matricula_vehiculo varchar(10) NOT NULL,
  telefono varchar(10) NOT NULL,
  sucursal_ID int NOT NULL,
  PRIMARY KEY(cedula),
  CONSTRAINT _1sucursal_ID FOREIGN KEY (sucursal_ID) REFERENCES Sucursal (sucursal_ID),
  CONSTRAINT _1cedula FOREIGN KEY (cedula) REFERENCES Persona (cedula) 
  );
  
DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente(
  cedula varchar(10) NULL DEFAULT "9999999999",
  direccion varchar(120) NULL DEFAULT "CONSUMIDOR FINAL",
  telefono varchar(10) NULL DEFAULT "9999999999",
  UNIQUE KEY cedula_unique (cedula),
  CONSTRAINT _2cedula FOREIGN KEY (cedula) REFERENCES Persona (cedula) 
  );
  
DROP TABLE IF EXISTS Mascota;
CREATE TABLE Mascota(
	mascota_ID int NOT NULL ,
	nombre varchar(20) NOT NULL,
	raza varchar(80) NOT NULL,
	estado varchar(120) NOT NULL,
	dueno_ID varchar(10) NOT NULL,
	PRIMARY KEY(mascota_ID),
	CONSTRAINT dueno FOREIGN KEY (dueno_ID) REFERENCES Persona (cedula) ON DELETE NO ACTION
  );
  -- Mercaderia
    DROP TABLE IF EXISTS Categoria;
  CREATE TABLE Categoria(
	categoria_ID int NOT NULL AUTO_INCREMENT,
    descripcion varchar(70) NULL DEFAULT "SIN DESCRIPCION",
    PRIMARY KEY(categoria_ID)
    );
    
DROP TABLE IF EXISTS Producto;
  CREATE TABLE Producto(
	producto_ID int NOT NULL AUTO_INCREMENT,
    nombre varchar(30) NOT NULL,
    precio_unitario float DEFAULT 0.00,
    descripcion varchar(70) NULL DEFAULT "SIN DESCRIPCION",
    categoria_ID INT NOT NULL,
    PRIMARY KEY(producto_ID),
    CONSTRAINT _categoria FOREIGN KEY (categoria_ID) REFERENCES Categoria (categoria_ID)
    );
    
        DROP TABLE IF EXISTS Stock;
  CREATE TABLE Stock(
	stock_ID int NOT NULL AUTO_INCREMENT,
    producto_ID int NOT NULL,
    stock int DEFAULT 0,
    sucursal_ID int NOT NULL,
    PRIMARY KEY(stock_ID, producto_ID),
    CONSTRAINT _3producto_ID FOREIGN KEY (producto_ID) REFERENCES Producto(producto_ID),
    CONSTRAINT _4sucursal_ID FOREIGN KEY (sucursal_ID) REFERENCES Sucursal(sucursal_ID)
    ); 
    
    DROP TABLE IF EXISTS Servicio;
  CREATE TABLE Servicio(
	servicio_ID int NOT NULL AUTO_INCREMENT,
    precio_unitario float DEFAULT 0.00,
    nombre varchar(30) NOT NULL,
    descripcion varchar(120) NULL DEFAULT "SIN DESCRIPCION",
    PRIMARY KEY(Servicio_ID)
    );
  -- cotizacion 
DROP TABLE IF EXISTS Cotizacion;
  CREATE TABLE Cotizacion(
	cotizacion_ID int NOT NULL AUTO_INCREMENT,
    decha DATE,
    valor float DEFAULT 0.00,
    cliente_ID varchar(6) NOT NULL,
    personal_caja_ID varchar(10) NOT NULL,
    PRIMARY KEY(cotizacion_ID),
    CONSTRAINT _personal_caja_ID FOREIGN KEY (personal_caja_ID) REFERENCES Usuario(usuario_ID) ON DELETE NO ACTION,
    CONSTRAINT _cliente_ID FOREIGN KEY (cliente_ID) REFERENCES Cliente(cedula)
    );
    
 -- componentes de Pedidos 
DROP TABLE IF EXISTS Ruta;
CREATE TABLE Ruta(
	ruta_ID int NOT NULL AUTO_INCREMENT,
    direccion varchar(50) NOT NULL,
    PRIMARY KEY(ruta_id)
    );
    
 DROP TABLE IF EXISTS Pedido;
  CREATE TABLE Pedido(
	pedido_ID int NOT NULL AUTO_INCREMENT,
    remitente_ID int NOT NULL,
    destinatario_ID varchar(10) NOT NULL,
    ruta_ID int,
    PRIMARY KEY(pedido_ID),
    CONSTRAINT  _remitente_ID FOREIGN KEY (remitente_ID) REFERENCES Sucursal (sucursal_ID) ON DELETE NO ACTION,
    CONSTRAINT _destinatario_ID FOREIGN KEY (destinatario_ID) REFERENCES Persona (cedula) ON DELETE NO ACTION,
    CONSTRAINT _ruta_ID FOREIGN KEY (ruta_ID) REFERENCES Ruta(ruta_ID)
    );
    
    DROP TABLE IF EXISTS Pedido_traslado;
  CREATE TABLE Pedido_traslado(
  pedido_traslado_ID int NOT NULL AUTO_INCREMENT,
  jefeBodega_ID varchar(10) NOT NULL,
  PRIMARY KEY(pedido_traslado_ID),
  CONSTRAINT _jefeBodega_ID FOREIGN KEY (jefeBodega_ID) REFERENCES Usuario(usuario_ID)
);
     
DROP TABLE IF EXISTS DetallePedido;
  CREATE TABLE DetallePedido(
	detalle_pedido_ID int NOT NULL AUTO_INCREMENT,
    cantidad int NOT NULL,
    pedido_ID int NOT NULL,
    producto_ID int NOT NULL,
    PRIMARY KEY(detalle_pedido_ID),
    CONSTRAINT _pedido_ID FOREIGN KEY (pedido_ID) REFERENCES Pedido(pedido_ID),
    CONSTRAINT _producto_ID FOREIGN KEY (producto_ID) REFERENCES Producto(producto_ID)
    );
    
    -- componentes de venta
    
DROP TABLE IF EXISTS Forma_Pago;
CREATE TABLE Forma_Pago(
  forma_pago_ID integer(3)  NOT NULL AUTO_INCREMENT ,
  impuesto FLOAT NOT NULL,
  descripcion varchar(120) NOT NULL,
  PRIMARY KEY(forma_pago_ID)
  );
    
DROP TABLE IF EXISTS Venta;
  CREATE TABLE Venta(
	venta_ID INTEGER(10)  NULL AUTO_INCREMENT,
    fecha DATE,
    n_factura int NOT NULL,
    sub_total FLOAT NOT NULL,
    total FLOAT NOT NULL,
    descuento FLOAT DEFAULT 0.00,
    personal_cajas_ID varchar(10) NOT NULL,
    forma_pago_ID int(3) NOT NULL,
    id_cliente varchar(10) NOT NULL,
    UNIQUE KEY(venta_ID),
    CONSTRAINT _personal_cajas_ID FOREIGN KEY (personal_cajas_ID) REFERENCES Usuario(usuario_ID) ON DELETE NO ACTION,
    CONSTRAINT _forma_pago_ID FOREIGN KEY (forma_pago_ID) REFERENCES Forma_Pago(forma_pago_ID),
    CONSTRAINT cliente_ID FOREIGN KEY (id_cliente) REFERENCES Cliente(cedula)
    );
    
DROP TABLE IF EXISTS DetalleVentaProducto;
  CREATE TABLE DetalleVentaProducto(
	detalle_venta_ID int NOT NULL AUTO_INCREMENT,
    cantidad int NOT NULL,
    venta_ID int NOT NULL,
    producto_ID int NOT NULL,
    cotizacion_ID int NOT NULL,
    PRIMARY KEY(detalle_venta_ID),
    CONSTRAINT _venta_ID FOREIGN KEY (venta_ID) REFERENCES Venta(venta_ID),
    CONSTRAINT _2producto_ID FOREIGN KEY (producto_ID) REFERENCES Producto(producto_ID),
    CONSTRAINT _1cotizacion_ID FOREIGN KEY (cotizacion_ID) REFERENCES Cotizacion(cotizacion_ID)
    );
    
DROP TABLE IF EXISTS DetalleVentaServicio;
CREATE TABLE DetalleVentaServicio(
	detalle_venta_ID int NOT NULL AUTO_INCREMENT,
	cantidad int NOT NULL,
    venta_ID int NOT NULL,
    servicio_ID int NOT NULL,
    cotizacion_ID int NOT NULL,
    PRIMARY KEY(detalle_venta_ID),
    CONSTRAINT _1venta_ID FOREIGN KEY (venta_ID) REFERENCES Venta(venta_ID),
    CONSTRAINT _Servicio_ID FOREIGN KEY (servicio_ID) REFERENCES Servicio(servicio_ID),
     CONSTRAINT _2cotizacion_ID FOREIGN KEY (cotizacion_ID) REFERENCES Cotizacion(cotizacion_ID)
    );
    # Creacion de usuario

drop user if exists 'adminDrPelos'@'localhost';
create user 'adminDrPelos'@'localhost' identified by 'admin';
grant select,insert,delete,update on drpeloslocal.* to 'adminDrPelos'@'localhost';
flush privileges;

    select * from cliente;
    