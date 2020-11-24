use master;
drop database Paleteria

create database Paleteria;
use Paleteria;
create schema empleado;

--Tablas--
create table empleado.Sucursal(
	idSucursal bigint identity (1,1) not null,
	direccion varchar(50) not null,
	telefono varchar(13) not null,
	horario  varchar(11) not null,

	constraint pkSucursal primary key(idSucursal)
);
create table empleado.Cliente(
	idCliente bigint identity(1,1) not null,
	nombreCliente varchar(50) not null,
	telefono varchar(13) not null,
	tipoCliente varchar(7),
	descuento real not null,

	constraint pkCliente primary key(idCliente)
);
create table empleado.Categoria(
	idCategoria bigint identity(1,1) not null,
	nombreCategoria varchar(30) not null,
	tamaño varchar(7) not null,
	
	constraint pkCategoria primary key(idCategoria),
);
create table empleado.Producto(
	idProducto bigint identity(1,1) not null,
	idCategoria bigint not null,
	precio real not null,
	sabor varchar(20) not null,

	constraint pkProducto primary key (idProducto),
	constraint fkCategoria foreign key (idCategoria)
	references empleado.Categoria(idCategoria) on delete cascade
);
create table empleado.Venta(
	idVenta bigInt identity(1,1) not null,
	idCliente bigint not null,
	montoTotal real not null,
	fechaVenta date not null,

	constraint pkVenta primary key (idVenta),
	constraint fkCliente foreign key (idCliente)
	references empleado.Cliente(idCliente) on delete cascade
);
create table empleado.Stock(
	idStock bigint identity(1,1) not null,
	idProducto bigint not null,
	idSucursal bigint not null,
	existencias int not null,

	constraint pkStock primary key (idStock),
	constraint fkProducto foreign key (idProducto)
	references empleado.Producto(idProducto) on delete cascade,
	constraint fkSucursal foreign key (idSucursal)
	references empleado.Sucursal(idSucursal) on delete cascade
);
create table empleado.DetalleVenta(
	idVenta bigint not null,
	idStock bigint,
	unidades int not null,
	subTotal real not null,
	
	constraint fkVenta foreign key (idVenta)
	references empleado.Venta(idVenta) on delete cascade ,
	constraint fkStock foreign key (idStock)
	references empleado.Stock(idStock) on delete set null

);
create table empleado.Inventario(
	idInventario bigint identity(1,1) not null,
	idSucursal bigint not null,
	fechaRecepcion date not null,
	constraint pkIventario primary key (idInventario),
	constraint fkSucursalI foreign key (idSucursal)
	references empleado.Sucursal(idSucursal) on delete cascade
);
create table empleado.InventarioProducto(
	idInventario bigint not null,
	idProducto bigint,
	cantidadRecibida int not null,
	
	constraint fkInventario foreign key (idInventario)
	references empleado.Inventario(idInventario) on delete cascade,
	constraint fkProduto foreign key (idProducto)
	references empleado.Producto(idProducto) on delete set null

);

--Reglas--
create rule rlTipoCliente as @tipoCliente in ('Mayoreo','Menudeo');
create rule rlMayorCero AS @range> 0;
create rule rlPosititvos AS @range>= 0;
create rule rlTamaños as @tamaño in ('Pequeño','Mediano','Grande');

--Asginación de reglas--
exec sp_bindrule 'rlTipoCliente','empleado.Cliente.TipoCliente';
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.subTotal';
exec sp_bindrule 'rlMayorCero','empleado.Producto.precio';
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.unidades';
exec sp_bindrule 'rlMayorCero','empleado.InventarioProducto.cantidadRecibida';
exec sp_bindrule 'rlPosititvos','empleado.Stock.existencias';
exec sp_bindrule 'rlPosititvos','empleado.Venta.montoTotal';
exec sp_bindrule 'rlTamaños','empleado.Categoria.tamaño';

---Triggers---
CREATE TRIGGER empleado.reduceStock
ON empleado.DetalleVenta AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idStock AS BIGINT
	DECLARE @existenciasactualizadas AS INT
	DECLARE @unidadesvendidas AS INT

	SELECT @idStock = idStock FROM inserted
	SELECT @unidadesvendidas = unidades FROM inserted

	SELECT @existenciasactualizadas = (SELECT existencias FROM empleado.Stock WHERE idStock = @idStock) - @unidadesvendidas

	UPDATE empleado.Stock SET existencias = @existenciasactualizadas WHERE idStock = @idStock
END;

CREATE TRIGGER empleado.Asignasubtotal
ON empleado.DetalleVenta AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idStock AS BIGINT
	DECLARE @Subtotal AS REAL
	DECLARE @Unidades AS INT
	DECLARE @Precio AS REAL

	SELECT @Unidades = unidades FROM inserted
	SELECT @idStock = idStock FROM inserted
	
	SELECT @Precio = (SELECT Producto.precio FROM Producto INNER JOIN Stock ON Stock.idProducto = Producto.idProducto WHERE Stock.idStock = @idStock)
	
	SELECT @Subtotal = @Precio * @Unidades
	
	UPDATE empleado.DetalleVenta SET subTotal = @Subtotal WHERE idStock = @idStock
END;

CREATE TRIGGER empleado.SumaTotalVenta
ON empleado.DetalleVenta AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @Suma AS REAL
	DECLARE @idventa AS BIGINT

    SELECT @idventa = idVenta FROM inserted
	SELECT @Suma = (SELECT SUM(empleado.DetalleVenta.subTotal) FROM empleado.DetalleVenta INNER JOIN empleado.Venta ON empleado.DetalleVenta.idVenta = empleado.Venta.idVenta WHERE empleado.Venta.idVenta = @idventa)
	
	UPDATE empleado.Venta SET montoTotal = @Suma WHERE idVenta = @idventa
END;

CREATE TRIGGER empleado.Reabastecimiento--Listo
ON empleado.InventarioProducto AFTER INSERT AS
	BEGIN 
	SET NOCOUNT ON

	DECLARE @idStock AS BIGINT
	DECLARE @idProducto AS BIGINT
	DECLARE @nuevostock AS INT
	declare @idSucursal as bigint
	declare @idInventario as bigint
	
	select @idInventario = idInventario from inserted
	select @idSucursal = idSucursal from empleado.Inventario where @idInventario = idInventario
	SELECT @idProducto = idProducto FROM inserted
	SELECT @idStock = idStock FROM empleado.Stock WHERE @idProducto = idProducto and  @idSucursal = idSucursal
	SELECT @nuevostock = (SELECT cantidadRecibida FROM inserted) + (SELECT existencias FROM empleado.Stock WHERE @idStock = idStock)

	UPDATE empleado.Stock SET existencias = @nuevostock WHERE idStock = @idStock
END;

CREATE TRIGGER empleado.descuentoventa
ON empleado.Venta FOR UPDATE AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idVenta AS BIGINT
	DECLARE @idCliente AS BIGINT
	DECLARE @totalcDescuento AS REAL
	DECLARE @totalDeVenta AS REAL

	SELECT @idVenta = idVenta FROM inserted
	SELECT @idCliente = idCliente FROM inserted
	SELECT @totalDeVenta = montoTotal FROM inserted

	IF ((SELECT descuento FROM empleado.Cliente WHERE idCliente = @idCliente) != 0 )
	BEGIN
		
		SELECT @totalcDescuento =  @totalDeVenta - (@totalDeVenta * ((SELECT descuento FROM empleado.Cliente WHERE idCliente = @idCliente)/100))
		UPDATE empleado.Venta SET montoTotal = @totalcDescuento
		WHERE idVenta = @idVenta
	END
END;

CREATE TRIGGER empleado.nuevoProducto
ON empleado.Producto AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idProducto AS BIGINT
	DECLARE @numerosocursales AS INT

	SELECT @idProducto = idProducto FROM inserted
	DECLARE @cnt INT = 1;


	SELECT @numerosocursales = (SELECT COUNT(*) FROM empleado.Sucursal)

	WHILE @cnt <= @numerosocursales
	BEGIN
		IF(EXISTS(SELECT empleado.Sucursal.idSucursal FROM empleado.Sucursal WHERE empleado.Sucursal.idSucursal = @cnt))
			INSERT INTO empleado.Stock (idProducto, idSucursal, existencias) VALUES(@idProducto, @cnt, 0);
		ELSE
			SELECT @numerosocursales = @numerosocursales + 1;
		SET @cnt = @cnt + 1;
	END;
END;

CREATE TRIGGER empleado.relacion
ON empleado.Sucursal AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idSucursal AS BIGINT
	DECLARE @numeroproductos AS INT
	DECLARE @cnt INT = 1;

	SELECT @idSucursal = idSucursal FROM inserted
	SELECT @numeroproductos = (SELECT COUNT(*) FROM empleado.Producto)

	WHILE @cnt <= @numeroproductos
	BEGIN
		IF(EXISTS(SELECT empleado.Producto.idProducto FROM empleado.Producto WHERE empleado.Producto.idProducto = @cnt))	
			INSERT INTO empleado.Stock (idProducto, idSucursal, existencias) VALUES(@cnt, @idSucursal, 0);
		ELSE
			SELECT @numeroproductos = @numeroproductos + 1;
		
		SET @cnt = @cnt + 1;
	END;
END;

--------------------Inserciones de Prueba-------------------------------
--Categorias--
insert into empleado.Categoria values ('Crema','Pequeño');
insert into empleado.Categoria values ('Agua','Pequeño');
--Clientes--
insert into empleado.Cliente values ('Ricardo Moreno','4443099965','Mayoreo',50);
insert into empleado.Cliente values ('Roberto Franco','4445555990','Menudeo',10);
insert into empleado.Cliente values ('Andrey Alonso','4444292590','Mayoreo',30);
insert into empleado.Cliente values ('Mauricio Aleman','4441357636','Menudeo',5);
insert into empleado.Cliente values ('Cliente no registrado','4445555990','Menudeo',0);
--Sucursales--
insert into empleado.Sucursal values('Picis 118, Capricornio','8189547','12:00-17:00');
insert into empleado.Sucursal values('Italia 52, Providencia','8189852','10:00-19:00');
--Productos--
insert into empleado.Producto values (1,18.5,'Fresa');
insert into empleado.Producto values (2,20.5,'Limon');
insert into empleado.Producto values (1,15.0,'Chocolate');
--Inventarios--
insert into empleado.Inventario values(1,getdate());
insert into empleado.InventarioProducto values(1,1,30);
insert into empleado.InventarioProducto values(1,2,30);
insert into empleado.InventarioProducto values(1,3,30);
insert into empleado.Inventario values(2,getdate());
insert into empleado.InventarioProducto values(2,1,30);
insert into empleado.InventarioProducto values(2,2,30);
insert into empleado.InventarioProducto values(2,3,30);
--Ventas--
insert into empleado.Venta(idCliente,montoTotal,fechaVenta) values(1,0.0,getdate());
insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal)values(1,1,2,1);
insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal)values(1,3,2,1);
insert into empleado.Venta(idCliente,montoTotal,fechaVenta) values(2,0.0,getdate());
insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal)values(2,2,2,1);
insert into empleado.DetalleVenta(idVenta,idStock,unidades,subTotal)values(2,4,2,1);
--------------------Inserciones de Prueba Triggers de Stock-------------------------------

select * from empleado.Categoria
select * from empleado.Cliente
select * from empleado.Inventario
select * from empleado.InventarioProducto
select * from empleado.Producto
select * from empleado.Stock 
select * from empleado.Sucursal
select * from empleado.DetalleVenta
select * from empleado.Venta