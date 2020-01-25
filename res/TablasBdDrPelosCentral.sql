drop database if exists DrPelosCentral ;
create database DrPelosCentral ;
use DrPelosCentral ;

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
    sucursal_ID int NOT NULL AUTO_INCREMENT,
    nombre varchar(20) NOT NULL,
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

DROP TABLE IF EXISTS Registro_sesion;
CREATE TABLE Registro_sesion(
	sesion_ID int NOT NULL AUTO_INCREMENT,
	usuario_ID varchar(45) NOT NULL,
	fecha DATE NOT NULL,
	PRIMARY KEY(sesion_ID),
	CONSTRAINT _usuario_sesion_ID FOREIGN KEY (usuario_ID) REFERENCES Usuario (usuario_ID)
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
  
DROP TABLE IF EXISTS Personal_Caja;
CREATE TABLE Personal_Caja(
  cedula varchar(10) NOT NULL,
	area varchar(30) NOT NULL,
	sucursal_ID int NOT NULL,
  PRIMARY KEY(cedula),
  CONSTRAINT _2sucursal_ID FOREIGN KEY (sucursal_ID) REFERENCES Sucursal (sucursal_ID),
  CONSTRAINT _2cedula FOREIGN KEY (cedula) REFERENCES Persona (cedula),
  CONSTRAINT _2cedula_user_ FOREIGN KEY (cedula) REFERENCES Usuario (usuario_ID)
);
  
DROP TABLE IF EXISTS Jefe_Bodega;
CREATE TABLE Jefe_Bodega(
  cedula varchar(10) NOT NULL,
  PRIMARY KEY(cedula),
  CONSTRAINT _cedula_jefe_Bodega FOREIGN KEY (cedula) REFERENCES Persona (cedula),
  CONSTRAINT _cedula_user_jefe_Bodega FOREIGN KEY (cedula) REFERENCES Usuario (usuario_ID)
);

DROP TABLE IF EXISTS Directivos;
CREATE TABLE Directivos(
  cedula varchar(10) NOT NULL,
  PRIMARY KEY(cedula),
  CONSTRAINT _1cedula_directivos FOREIGN KEY (cedula) REFERENCES Persona (cedula),
  CONSTRAINT _10cedula_user_ FOREIGN KEY (cedula) REFERENCES Usuario (usuario_ID) 
);

DROP TABLE IF EXISTS Administrador;
CREATE TABLE Administrador(
  cedula varchar(10) NOT NULL,
  PRIMARY KEY(cedula),
  CONSTRAINT _1cedula_administrador FOREIGN KEY (cedula) REFERENCES Persona (cedula),
  CONSTRAINT _20cedula FOREIGN KEY (cedula) REFERENCES Usuario (usuario_ID)
);
  
DROP TABLE IF EXISTS Cliente;
CREATE TABLE Cliente(
  cedula varchar(10) NULL DEFAULT "9999999999",
  direccion varchar(120) NULL DEFAULT "CONSUMIDOR FINAL",
  telefono varchar(10) NULL DEFAULT "9999999999",
  UNIQUE KEY cedula_unique (cedula),
  CONSTRAINT _cedula_cliente FOREIGN KEY (cedula) REFERENCES Persona (cedula) 
  );
  
DROP TABLE IF EXISTS Mascota;
CREATE TABLE Mascota(
	mascota_ID int NOT NULL AUTO_INCREMENT,
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
    nombre_c varchar(50) NOT NULL,
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
    fecha DATE,
    valor float DEFAULT 0.00,
    cliente_ID varchar(10) NOT NULL,
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
    descripcion varchar(40) NOT NULL,
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
  
insert into Forma_Pago values(123, 17.0,"Efectivo");
    
DROP TABLE IF EXISTS Pago_Efectivo;
CREATE TABLE Pago_Efectivo(
  efectivo_ID integer(3)  NOT NULL AUTO_INCREMENT,
  cantidad_efectivo FLOAT NOT NULL,
  PRIMARY KEY(efectivo_ID),
  CONSTRAINT _pago_efectivo_ID FOREIGN KEY (efectivo_ID) REFERENCES Forma_Pago(forma_pago_ID)
  );
  
DROP TABLE IF EXISTS Pago_PayPal;
CREATE TABLE Pago_PayPal(
  payPal_ID integer(3)  NOT NULL AUTO_INCREMENT ,
  correoElectronico varchar(50) NOT NULL,
  PRIMARY KEY(payPal_ID),
  CONSTRAINT _pago_paypal_ID FOREIGN KEY (payPal_ID) REFERENCES Forma_Pago(forma_pago_ID)
  );
  
  
DROP TABLE IF EXISTS Pago_Tarjeta;
CREATE TABLE Pago_Tarjeta(
  tarjeta_ID integer(3)  NOT NULL AUTO_INCREMENT ,
  num_cuenta varchar(20) NOT NULL,
  PRIMARY KEY(tarjeta_ID),
  CONSTRAINT _pago_tarjeta_ID FOREIGN KEY (tarjeta_ID) REFERENCES Forma_Pago(forma_pago_ID)
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
    pedido_ID int,
    UNIQUE KEY(venta_ID),
    CONSTRAINT _personal_cajas_ID FOREIGN KEY (personal_cajas_ID) REFERENCES Usuario(usuario_ID) ON DELETE NO ACTION,
    CONSTRAINT _forma_pago_ID FOREIGN KEY (forma_pago_ID) REFERENCES Forma_Pago(forma_pago_ID),
    CONSTRAINT cliente_ID FOREIGN KEY (id_cliente) REFERENCES Cliente(cedula),
    CONSTRAINT pedido_ID FOREIGN KEY (pedido_ID) REFERENCES Pedido(pedido_ID)
);
    
DROP TABLE IF EXISTS DetalleVentaProducto;
  CREATE TABLE DetalleVentaProducto(
	detalle_venta_ID int NOT NULL AUTO_INCREMENT,
    cantidad int NOT NULL,
    venta_ID int,
    producto_ID int NOT NULL,
    cotizacion_ID int,
    PRIMARY KEY(detalle_venta_ID),
    CONSTRAINT _venta_ID FOREIGN KEY (venta_ID) REFERENCES Venta(venta_ID),
    CONSTRAINT _2producto_ID FOREIGN KEY (producto_ID) REFERENCES Producto(producto_ID),
    CONSTRAINT _1cotizacion_ID FOREIGN KEY (cotizacion_ID) REFERENCES Cotizacion(cotizacion_ID)
    );
    
DROP TABLE IF EXISTS DetalleVentaServicio;
CREATE TABLE DetalleVentaServicio(
	detalle_venta_ID int NOT NULL AUTO_INCREMENT,
	cantidad int NOT NULL,
    venta_ID int,
    servicio_ID int NOT NULL,
    cotizacion_ID int,
    PRIMARY KEY(detalle_venta_ID),
    CONSTRAINT _1venta_ID FOREIGN KEY (venta_ID) REFERENCES Venta(venta_ID),
    CONSTRAINT _Servicio_ID FOREIGN KEY (servicio_ID) REFERENCES Servicio(servicio_ID),
     CONSTRAINT _2cotizacion_ID FOREIGN KEY (cotizacion_ID) REFERENCES Cotizacion(cotizacion_ID)
    );
    
 -- inserts
 -- categoria
    insert into categoria values(default,"Alimentos","aplica a todos los productos alimentarios");
    insert into categoria values(default,"Higiene","aplica a todos los productos especializados en higiene animal");
    insert into categoria values(default,"Accesorios","aplica a todos los accesorios utiles para mascotas");
    insert into categoria values(default,"Juguetes","aplica a todos los productos de entretenimiento para mascotas");
    insert into categoria values(default,"Medicinas","aplica a todos los productos especializados en medicina animal");
 -- productos 
    insert into producto  values(default,"Croqueta Perro",13.0,"Croquetas sabor pollo 2kg",1);
    insert into producto values(default,"Croqueta Perro",5.9,"Croquetas sabor carne y vegetales 1kg",1);
    insert into producto values(default,"Croqueta Gato",10.7,"Croquetas sabor carne 1kg",1);
    insert into producto values(default,"Shampoo Perro",10.64,"Shampoo antiséptico 380ml",2);
    insert into producto values(default,"Shampoo",15.99,"Shampoo antialérgico para Mascotas 250ml",2);
    insert into producto values(default,"Cepillo Masajeador",2.89,"Cepillo de caucho rojo",2);
    insert into producto values(default,"Plato redondo",4.84,"Plato antideslizante naranja",3);
    insert into producto values(default,"Bebedero",12.0,"Bebedero para mascotas",3);
    insert into producto values(default,"Cama",29.23,"Cama tipo sillon",3);
    insert into producto values(default,"Juguete volador",29.58,"Juguete volador para perro",4);
    insert into producto values(default,"Ratón de cuerda",8.99,"Ratón de cuerda para gato",4);
    insert into producto values(default,"Desparasitante",5.89,"Antiparasitario de espectro total",5);
	insert into producto values(default,"Liquido emulcionante",2.30,"Pulguicida y garrapaticida",5);
-- insert servicios

    insert into servicio  values(default,17.0,"Peluquería","Raza pequeño");
	insert into servicio  values(default,22.0,"Peluquería","Raza mediana");
    insert into servicio values(default,25.0,"Peluquería","Raza grande");
    insert into servicio values(default,230.0,"Adiestramiento","Adiestramiento básico");
    insert into servicio values(default,5.0,"Traslado raza pequeña","Traslado dentro de Guayaquil");
    
-- insert sucursales
	insert into sucursal values(default,"Centro 1", "José Mascote 400 y Padre Solano",true);
    insert into sucursal values(default,"Centro 2", "Rumichaca Nº 2502 entre Febres Cordero y Cuenca",true);
    insert into sucursal values(default,"Norte", "Alborada XI Etapa Mz 45 Villa 5",false);

-- insert trabajadores
	insert into Persona values("0975368545","Maria","Romero");
    insert into Persona values("0945343504","Jorge","Martinez");
    insert into Persona values("0935843645","Hector","Ruiz");
    insert into Persona values("0924940396","Fernanda","Ramirez");
    insert into Persona values("0996903483","Jose","Alvarez");
    insert into Persona values("0934920534","Andrea","Medina");
	insert into Persona values("0964784725","Carlos","Morales");
    insert into Persona values("0934829524","Alejandro","Molina");
	insert into Persona values("0953482343","Lucas","Dominguez");
	insert into Persona values("0947875245","Julio","Castillo");
    
    insert into Usuario values("0975368545","mromero","caja",1,false);
    insert into Usuario values("0945343504","jmartinez","caja",2,false);
    insert into Usuario values("0935843645","hruiz","caja",3,false);
    insert into Usuario values("0924940396","framirez","caja",2,false);
	insert into Usuario values("0996903483","jalvarez","jefebodega",1,false);	
    insert into Usuario values("0934920534","amedina","admin",1,true);
	insert into Usuario values("0964784725","cmorales","directivo",1,false);
	insert into Usuario values("0934829524","amolina","repartidor",1,false);
	insert into Usuario values("0953482343","ldominguez","repartidor",2,false);
	insert into Usuario values("0947875245","jcastillo","repartidor",3,false);

    insert into Administrador values("0934920534");

	insert into Jefe_Bodega values("0996903483");
    
    insert into Directivos values("0964784725");

    insert into Personal_Caja values("0975368545","Cobranza", 1);
    insert into Personal_Caja values("0945343504","Cobranza", 2);
    insert into Personal_Caja values("0924940396","Atencion Cliente", 2);
	insert into Personal_Caja values("0935843645","Cobranza", 3);
    
    insert into repartidor values("0934829524", "GEE-385","0977530446",1);
	insert into repartidor values("0953482343", "GJL-195","0834955124",2);
    insert into repartidor values("0947875245", "PEQ-439","0985655654",3);

-- insert cliente
	insert into Persona values("0990999841","Eduardo","Gonzalez");
	insert into Persona values("0927389243","Mariana","Ortiz");
	insert into Persona values("0994854756","Jose","Castro");
    insert into Persona values("0987478445","Alex","Gomez");
    insert into Persona values("0987743422","Alejandra","Morales");
	
    insert into Cliente values("0990999841","Guayacanes","042859384");
    insert into Cliente values("0927389243","Sur","016548654");
    insert into Cliente values("0994854756","Atarazana","24949343");
    insert into Cliente values("0987478445","Samanes","254856158");
    insert into Cliente values("0987743422","Acuarelas del rio","2564865");
    
    insert into Mascota values(default,"Coffe","Mestizo","Sucursal","0990999841");
    insert into Mascota values(default,"Filomeno","Mestizo","Domicilio","0990999841");
    insert into Mascota values(default,"Cuy","Mestizo","Translado a domicilio","0990999841");
    insert into Mascota values(default,"Gato","Mestizo","Translado a sucursal","0990999841");
	insert into Mascota values(default, "Blanquita","Mestizo","Domicilio","0927389243");
	insert into Mascota values(default, "Leo","Mestizo","Traslado a sucursal","0994854756");
	insert into Mascota values(default, "Laika","Mestizo","Domicilio","0987478445");
	insert into Mascota values(default, "Jack","Mestizo","Sucursal","0987743422");

	insert into Forma_Pago values(200,0,"Efectivo");
    insert into Forma_Pago values(201,4.2,"Tarjeta");
    insert into Forma_Pago values(202,5,"Paypal");
    insert into Forma_Pago values(203,6,"Efectivo");
    
    insert into Pago_Efectivo values(200,6);
    insert into Pago_Efectivo values(203,6);
    
    insert into Venta values(default,"2020-01-02",02302,4.0,10,3.4,"0975368545",200,"0927389243", null);
    insert into Pago_Tarjeta values(201, "5543-3434-5434-8557");
    
    insert into Pago_PayPal values(202,"amorales@correo.com");
    
    insert into Venta values(default,"2020-01-02",000-2302,4.0,10,6,"0975368545",200,"0927389243", null);
    insert into Venta values(default,"2020-01-02",000-2303,4.0,10,10.5,"0945343504",201,"0994854756", null);
    insert into Venta values(default,"2019-11-10",000-024,4.0,10,24.9,"0945343504",202,"0987743422", null);
    insert into Venta values(default,"2019-09-11",000-2339,4.0,10,6,"0924940396",203,"0927389243", null);
	
    insert into Cotizacion values(default, "2020-01-10",10.0,"0994854756","0924940396");
    insert into Cotizacion values(default, "2019-10-14",25.5,"0994854756","0935843645");
		
	insert into Ruta values(default, "Gómez Rendón 3318 E/ La 11ava y La 10ma");
    insert into Ruta values(default, "Av. Francisco de Orellana y calle Justino Cornejo");
    insert into Ruta values(default, "Cdla Garzota Mz 130 Villa 12 Esq");
    insert into Ruta values(default, "Cdla Guayacanes Mz 63 Villa 34 Esq");
    
-- views
-- views producto
drop view if exists V_Productos;
CREATE VIEW V_Productos
AS SELECT  p.producto_ID,p.nombre, p.precio_unitario, p.descripcion, c.nombre_c
FROM Producto as p
join Categoria as c on c.categoria_ID=p.categoria_ID;

-- view servicios
drop view if exists V_Servicios;
CREATE VIEW V_Servicios
AS SELECT  s.servicio_ID,s.nombre, s.precio_unitario, s.descripcion
FROM Servicio as s;

-- view ventas
drop view if exists V_Ventas;
CREATE VIEW V_Ventas
AS SELECT Venta.*,cliente.direccion, cliente.telefono,persona.nombre,
persona.apellido,Forma_Pago.descripcion as pago_descripcion ,Usuario.usuario ,Personal_Caja.*  FROM Venta
JOIN Cliente on venta.id_cliente = Cliente.cedula
JOIN Persona on Cliente.cedula = Persona.cedula
JOIN Forma_Pago on Forma_Pago.forma_pago_ID = Venta.forma_pago_ID
JOIN Personal_Caja on Venta.personal_cajas_ID = Personal_Caja.cedula
JOIN Usuario on Personal_Caja.cedula = Usuario.usuario_ID;

-- view cotizacion
drop view if exists V_Cotizacion;
CREATE VIEW V_Cotizacion
AS SELECT Cotizacion.*,cliente.direccion, cliente.telefono,persona.nombre,
persona.apellido, Personal_Caja.cedula  FROM Cotizacion
JOIN Cliente on cotizacion.cliente_ID = Cliente.cedula
JOIN Persona on Cliente.cedula = Persona.cedula
JOIN Personal_Caja on cotizacion.personal_caja_ID = Personal_Caja.cedula
JOIN Usuario on Personal_Caja.cedula = Usuario.usuario_ID;


    # Creacion de usuario

drop user if exists 'adminDrPelos'@'localhost';
create user 'adminDrPelos'@'localhost' identified by 'admin';
grant select,insert,delete,update on drpeloscentral.* to 'adminDrPelos'@'localhost';
flush privileges;