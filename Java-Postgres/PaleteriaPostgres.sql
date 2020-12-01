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
	tipoCliente VARCHAR(7),
	descuento REAL NOT NULL,

	CONSTRAINT PK_Cliente PRIMARY KEY(idCliente)
);

CREATE TABLE empleado.Categoria(
	
	idCategoria BIGSERIAL NOT NULL,
	nombreCategoria VARCHAR(30) NOT NULL,
	tamaño VARCHAR(7) NOT NULL,
	
	CONSTRAINT PK_Categoria PRIMARY KEY(idCategoria)
);

CREATE TABLE empleado.Producto(
	idProducto BIGSERIAL NOT NULL,
	idCategoria BIGINT NOT NULL,
	precio REAL NOT NULL,
	sabor VARCHAR(20) NOT NULL,

	CONSTRAINT PK_Producto PRIMARY KEY (idProducto),
	CONSTRAINT FK_Categoria FOREIGN KEY (idCategoria) REFERENCES empleado.Categoria(idCategoria)
);

CREATE TABLE empleado.Venta(
	idVenta BIGSERIAL NOT NULL,
	idCliente BIGINT NOT NULL,
	montoTotal REAL NOT NULL,
	fechaVenta DATE NOT NULL,

	CONSTRAINT PK_Venta PRIMARY KEY (idVenta),
	CONSTRAINT FK_Cliente FOREIGN KEY (idCliente) REFERENCES empleado.Cliente(idCliente)
);

CREATE TABLE empleado.Stock(
	idStock BIGSERIAL NOT NULL,
	idProducto BIGINT NOT NULL,
	idSucursal BIGINT NOT NULL,
	existencias INT NOT NULL,

	CONSTRAINT PK_Stock PRIMARY KEY (idStock),
	CONSTRAINT FK_Producto FOREIGN KEY (idProducto) REFERENCES empleado.Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FK_Sucursal FOREIGN KEY (idSucursal) REFERENCES empleado.Sucursal(idSucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE empleado.DetalleVenta(
	idVenta BIGINT NOT NULL,
	idStock BIGINT NOT NULL,
	unidades INT NOT NULL,
	subTotal REAL NOT NULL,
	
	CONSTRAINT FK_Venta FOREIGN KEY (idVenta) REFERENCES empleado.Venta(idVenta) ,
	CONSTRAINT FK_Stock FOREIGN KEY (idStock) REFERENCES empleado.Stock(idStock)

);

CREATE TABLE empleado.Inventario(
	
	idInventario BIGSERIAL NOT NULL,
	idSucursal BIGINT NOT NULL,
	fechaRecepcion DATE NOT NULL,
	
	CONSTRAINT PK_Iventario PRIMARY KEY (idInventario),
	CONSTRAINT FK_SucursalI FOREIGN KEY (idSucursal) REFERENCES empleado.Sucursal(idSucursal)
);

CREATE TABLE empleado.InventarioProducto(
	
	idInventario BIGINT NOT NULL,
	idProducto BIGINT NOT NULL,
	cantidadRecibida INT NOT NULL,
	
	CONSTRAINT FK_Inventario FOREIGN KEY (idInventario) REFERENCES empleado.Inventario(idInventario),
	CONSTRAINT FK_Produto FOREIGN KEY (idProducto) REFERENCES empleado.Producto(idProducto)

);


ALTER TABLE empleado.Cliente 
ADD CONSTRAINT cliente_check 
CHECK (
	tipoCliente = 'Mayoreo' OR tipoCliente = 'Menudeo'
);

ALTER TABLE empleado.DetalleVenta
ADD CONSTRAINT detalleventa_check 
CHECK (
	unidades > 0
);


ALTER TABLE empleado.Sucursal
ADD CONSTRAINT socursal_unique
UNIQUE 
(
	direccion
);


CREATE FUNCTION empleado.reduceStock() RETURNS TRIGGER AS $$
DECLARE
	idStocku BIGINT;
	existenciasactualizadas INT;
	unidadesvendidas INT;
	BEGIN 
	
		idStocku = NEW.idStock;
		unidadesvendidas = NEW.unidades;
		
		existenciasactualizadas = (SELECT existencias FROM empleado.Stock WHERE idStock = idStocku) - unidadesvendidas;
		
		UPDATE empleado.Stock SET existencias = existenciasactualizadas WHERE idStock = idStocku;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reduceStock
AFTER INSERT 
	ON empleado.DetalleVenta FOR EACH ROW
	EXECUTE PROCEDURE empleado.reduceStock();


CREATE FUNCTION empleado.Asignasubtotal() RETURNS TRIGGER AS $$
DECLARE
	idStocku BIGINT;
	Subtotal REAL;
	Unidades INT;
	Precio REAL;
	BEGIN 
	
		idStocku = NEW.idStock;
		Unidades = NEW.unidades;
		
		Precio = (SELECT Producto.precio FROM Producto INNER JOIN Stock 
				  ON Stock.idProducto = Producto.idProducto WHERE Stock.idStock = idStocku);
		
		Subtotal = Precio * Unidades;
		
		UPDATE empleado.DetalleVenta SET subTotal = @Subtotal WHERE idStock = idStocku;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Asignasubtotal
AFTER INSERT 
	ON empleado.DetalleVenta FOR EACH ROW
	EXECUTE PROCEDURE empleado.Asignasubtotal();
	

CREATE FUNCTION empleado.SumaTotalVenta() RETURNS TRIGGER AS $$
DECLARE
	idVentau BIGINT;
	Suma REAL;
	BEGIN 
	
		idVentau = NEW.idVenta;
		
		Suma = (SELECT SUM(empleado.DetalleVenta.subTotal) FROM empleado.DetalleVenta INNER JOIN empleado.Venta 
				ON empleado.DetalleVenta.idVenta = empleado.Venta.idVenta WHERE empleado.Venta.idVenta = idVentau);
		
		
		UPDATE empleado.Venta SET montoTotal = Suma WHERE idVenta = idVentau;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER SumaTotalVenta
AFTER INSERT 
	ON empleado.DetalleVenta FOR EACH ROW
	EXECUTE PROCEDURE empleado.SumaTotalVenta();



CREATE FUNCTION empleado.Reabastecimiento() RETURNS TRIGGER AS $$
DECLARE
	idStocku BIGINT;
	idProductou BIGINT;
	nuevostock INT;
	idSucursalu BIGINT;
	idInventariou BIGINT;
	BEGIN 
	
		idInventariou = NEW.idInventario;
		idStocku = idSucursal FROM empleado.Inventario WHERE idInventario = idInventariou;
		idProductou = NEW.idProducto;
		idSucursalu = idStock FROM empleado.Stock
		WHERE idProducto = idProductou AND idSucursal = idSucursalu;
		
		nuevostock = (NEW.cantidadRecibida)
		+ (SELECT existencias FROM empleado.Stock WHERE idStock = idStocku);
		
		UPDATE empleado.Stock SET existencias = nuevostock WHERE idStock = idStocku;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Reabastecimiento
AFTER INSERT 
	ON empleado.InventarioProducto FOR EACH ROW
	EXECUTE PROCEDURE empleado.Reabastecimiento();



CREATE FUNCTION empleado.descuentoventa() RETURNS TRIGGER AS $$
DECLARE
	idVentau BIGINT;
	idClienteu BIGINT;
	totalcDescuento REAL;
	totalDeVenta REAL;
	BEGIN 
	
		idVentau = NEW.idVenta;
		idClienteu = NEW.idCliente;
		totalDeVenta = NEW.montoTotal;
		
		IF ((SELECT descuento FROM empleado.Cliente WHERE idCliente = idClienteU) != 0) THEN
			totalcDescuento =  @totalDeVenta 
			- (@totalDeVenta * ((SELECT descuento FROM empleado.Cliente WHERE idCliente = idClienteu)/100));
			UPDATE empleado.Venta SET montoTotal = totalcDescuento WHERE idVenta = idVentau;
		END IF;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER descuentoventa
AFTER UPDATE
	ON empleado.Venta FOR EACH ROW
	EXECUTE PROCEDURE empleado.descuentoventa();


CREATE or replace FUNCTION empleado.nuevoProducto() RETURNS TRIGGER AS $$
DECLARE
	idProductou BIGINT;
	numerosocursales INT;
	cnt INT = 1;
	BEGIN 
	
		idProductou = NEW.idProducto;
		
		numerosocursales = (SELECT COUNT(*) FROM empleado.Sucursal);
		
		WHILE cnt <= numerosocursales LOOP
   			IF(EXISTS(SELECT empleado.Sucursal.idSucursal FROM empleado.Sucursal WHERE empleado.Sucursal.idSucursal = cnt)) THEN
				INSERT INTO empleado.Stock (idProducto, idSucursal, existencias) 
				VALUES(idProductou, cnt, 0);
			ELSE
				SELECT numerosocursales = numerosocursales + 1;
			END IF;
			cnt = cnt + 1;
		END LOOP;
		
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER nuevoProducto
AFTER INSERT
	ON empleado.Producto FOR EACH ROW
	EXECUTE PROCEDURE empleado.nuevoProducto();




CREATE FUNCTION empleado.relacion() RETURNS TRIGGER AS $$
DECLARE
	idSucursalu BIGINT;
	numeroproductos INT;
	cnt INT = 1;
	BEGIN 
	
		idSucursalu = NEW.idSucursal;
		
		numeroproductos = (SELECT COUNT(*) FROM empleado.Producto);
		
		WHILE cnt <= numeroproductos LOOP
   			IF(EXISTS(SELECT empleado.Producto.idProducto FROM empleado.Producto WHERE empleado.Producto.idProducto = cnt)) THEN
				INSERT INTO empleado.Stock (idProducto, idSucursal, existencias) VALUES(cnt, idSucursalu, 0);
			ELSE
				SELECT numeroproductos = numeroproductos + 1;
			END IF;
			cnt = cnt + 1;
		END LOOP;
		
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER relacion
AFTER INSERT
	ON empleado.Sucursal FOR EACH ROW
	EXECUTE PROCEDURE empleado.relacion();
	
	
	
insert into empleado.Categoria values (1,'Crema','Pequeño');
insert into empleado.Categoria values (2,'Agua','Pequeño');

insert into empleado.Cliente values (1,'Ricardo Moreno','4443099965','Mayoreo',50);
insert into empleado.Cliente values ('Roberto Franco','4445555990','Menudeo',10);
insert into empleado.Cliente values ('Andrey Alonso','4444292590','Mayoreo',30);
insert into empleado.Cliente values ('Mauricio Aleman','4441357636','Menudeo',5);
insert into empleado.Cliente values ('Cliente no registrado','4445555990','Menudeo',0);

insert into empleado.Sucursal values(1,'Picis 118, Capricornio','8189547','12:00-17:00');
insert into empleado.Sucursal values(2,'Italia 52, Providencia','8189852','10:00-19:00');
insert into empleado.Sucursal values(3,'Italia 57, Providencia','8189852','10:00-19:00');


insert into empleado.Producto values (1,1,18.5,'Fresa');
insert into empleado.Producto values (2,2,20.5,'Limon');
insert into empleado.Producto values (3,1,15.0,'Chocolate');



select * from empleado.Categoria
select * from empleado.Cliente
select * from empleado.Inventario
select * from empleado.InventarioProducto
select * from empleado.Producto
select * from empleado.Stock 
select * from empleado.Sucursal
select * from empleado.DetalleVenta
select * from empleado.Venta
