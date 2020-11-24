CREATE DATABASE "Paleteria"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE SCHEMA empleado
--Tablas--		
CREATE TABLE empleado.Sucursal(
	idSucursal BIGSERIAL NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	telefono VARCHAR(13) NOT NULL,
	horario  VARCHAR(11) NOT NULL,

	CONSTRAINT PK_Sucursal PRIMARY KEY(idSucursal)
);

CREATE TABLE empleado.Cliente(
	
	idCliente BIGSERIAL NOT NULL,
	nombreCliente VARCHAR(50) NOT NULL,
	telefono VARCHAR(13) NOT NULL,
	tipoCliente VARCHAR(7) CHECK(tipoCliente = 'Mayoreo' or tipoCliente = 'Menudeo'),
	descuento REAL NOT NULL,

	CONSTRAINT PK_Cliente PRIMARY KEY(idCliente)
);

CREATE TABLE empleado.Categoria(
	
	idCategoria BIGSERIAL NOT NULL,
	nombreCategoria VARCHAR(30) NOT NULL,
	tamaño VARCHAR(7) NOT NULL CHECK(tamaño = 'Pequeño' OR tamaño = 'Mediano' OR tamaño = 'Grande'),
	
	CONSTRAINT PK_Categoria PRIMARY KEY(idCategoria)
);

CREATE TABLE empleado.Producto(
	idProducto BIGSERIAL NOT NULL,
	idCategoria BIGINT NOT NULL,
	precio REAL NOT NULL  CHECK(precio > 0),
	sabor VARCHAR(20) NOT NULL,

	CONSTRAINT PK_Producto PRIMARY KEY (idProducto),
	CONSTRAINT FK_Categoria FOREIGN KEY (idCategoria)
	REFERENCES empleado.Categoria(idCategoria) ON DELETE CASCADE
);

CREATE TABLE empleado.Venta(
	idVenta BIGSERIAL NOT NULL,
	idCliente BIGINT NOT NULL,
	montoTotal REAL NOT NULL CHECK(montoTotal >= 0),
	fechaVenta DATE NOT NULL,

	CONSTRAINT PK_Venta PRIMARY KEY (idVenta),
	CONSTRAINT FK_Cliente FOREIGN KEY (idCliente)
	REFERENCES empleado.Cliente(idCliente) ON DELETE CASCADE
);

CREATE TABLE empleado.Stock(
	idStock BIGSERIAL NOT NULL,
	idProducto BIGINT NOT NULL,
	idSucursal BIGINT NOT NULL,
	existencias INT NOT NULL CHECK(existencias >= 0),

	CONSTRAINT PK_Stock PRIMARY KEY (idStock),
	CONSTRAINT FK_Producto FOREIGN KEY (idProducto)
	REFERENCES empleado.Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Sucursal FOREIGN KEY (idSucursal)
	REFERENCES empleado.Sucursal(idSucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE empleado.DetalleVenta(
	idVenta BIGINT NOT NULL,
	idStock BIGINT NOT NULL,
	unidades INT NOT NULL CHECK(unidades > 0),
	subTotal REAL NOT NULL CHECK(subTotal > 0),
	
	CONSTRAINT FK_Venta FOREIGN KEY (idVenta)
	REFERENCES empleado.Venta(idVenta) ON DELETE CASCADE,
	CONSTRAINT FK_Stock FOREIGN KEY (idStock)
	REFERENCES empleado.Stock(idStock) ON DELETE SET NULL

);

CREATE TABLE empleado.Inventario(
	idInventario BIGSERIAL NOT NULL,
	idSucursal BIGINT NOT NULL,
	fechaRecepcion DATE NOT NULL,
	
	CONSTRAINT PK_Iventario PRIMARY KEY (idInventario),
	CONSTRAINT FK_SucursalI FOREIGN KEY (idSucursal)
	REFERENCES empleado.Sucursal(idSucursal) ON DELETE CASCADE
);

CREATE TABLE empleado.InventarioProducto(
	idInventario BIGINT NOT NULL,
	idProducto BIGINT NOT NULL,
	cantidadRecibida INT NOT NULL CHECK(cantidadRecibida > 0),
	
	CONSTRAINT FK_Inventario FOREIGN KEY (idInventario)
	REFERENCES empleado.Inventario(idInventario) ON DELETE CASCADE,
	CONSTRAINT FK_Produto FOREIGN KEY (idProducto)
	REFERENCES empleado.Producto(idProducto) ON DELETE SET NULL
);