create database Paleteria;
use Paleteria;
create schema empleado;

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
	nombre varchar(30) not null,
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
	references empleado.Categoria(idCategoria)
);

create table empleado.Venta(
	idVenta bigInt identity(1,1) not null,
	idCliente bigint not null,
	montoTotal real not null,
	fechaVenta date not null,

	constraint pkVenta primary key (idVenta),
	constraint fkCliente foreign key (idCliente)
	references empleado.Cliente(idCliente)
);

create table empleado.Stock(
	idStock bigint identity(1,1) not null,
	idProducto bigint not null,
	idSucursal bigint not null,
	existencias int not null,

	constraint pkStock primary key (idStock),
	constraint fkProducto foreign key (idProducto)
	references empleado.Producto(idProducto),
	constraint fkSucursal foreign key (idSucursal)
	references empleado.Sucursal(idSucursal)
);

create table empleado.DetalleVenta(
	idVenta bigint not null,
	idStock bigint not null,
	unidades int not null,
	subTotal real not null,
	
	constraint fkVenta foreign key (idVenta)
	references empleado.Venta(idVenta),
	constraint fkStock foreign key (idStock)
	references empleado.Stock(idStock)

);

create table empleado.Inventario(
	idInventario bigint identity(1,1) not null,
	idSucursal bigint not null,
	fechaRecepcion date not null,
	totalRecepcion int not null,
	constraint pkIventario primary key (idInventario),
	constraint fkSucursalI foreign key (idSucursal)
	references empleado.Sucursal(idSucursal)
);

create table empleado.InventarioProducto(
	idInventario bigint not null,
	idProducto bigint not null,
	cantidadRecibida int not null,
	
	constraint fkInventario foreign key (idInventario)
	references empleado.Inventario(idInventario),
	constraint fkProduto foreign key (idProducto)
	references empleado.Producto(idProducto)

);


create rule rlTipoCliente as @tipoCliente in ('Mayoreo','Menudeo');

create rule rlMayorCero AS @range> 0;
create rule rlPosiritvos AS @range>= 0;

create rule rlTamaños as @tamaño in ('Pequeño','Mediano','Grande');



exec sp_bindrule 'rlTipoCliente','empleado.Cliente.TipoCliente';
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.subTotal';
exec sp_bindrule 'rlMayorCero','empleado.Producto.precio';
exec sp_bindrule 'rlMayorCero','empleado.Categoria.tamaño';
exec sp_bindrule 'rlMayorCero','empleado.InventarioProducto.cantidadRecibida';
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.unidades';
exec sp_bindrule 'rlPositvos','empleado.Stock.existencias';
exec sp_bindrule 'rlPositvos','empleado.Venta.montoTotal';
exec sp_bindrule 'rlTamaños','empleado.Categoria.tamaño';


CREATE TRIGGER empleado.descuentoventa
ON empleado.Venta AFTER INSERT AS
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



CREATE TRIGGER empleado.reduceStock
ON empleado.DetalleVenta AFTER INSERT AS
	BEGIN
	SET NOCOUNT ON

	DECLARE @idStock AS BIGINT
	DECLARE @existenciasactualizadas AS INT
	DECLARE @unidadesvendidas AS INT

	SELECT @idStock = idStock FROM inserted
	SELECT @unidadesvendidas FROM inserted

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

--------------------Inserciones de Prueba-------------------------------
insert into empleado.Categoria values ('Crema','Pequeño');
insert into empleado.Categoria values ('Agua','Pequeño');
insert into empleado.Cliente values ('Ricardo Moreno','4443099965','Mayoreo',10);
insert into empleado.Cliente values ('Roberto Franco','4445555990','Menudeo',0);
insert into empleado.Cliente values ('Andrey Alonso','4444292590','Mayoreo',30);
insert into empleado.Cliente values ('Mauricio Aleman','4441357636','Menudeo',0);
insert into empleado.Cliente values ('Cliente no registrado','4445555990','Menudeo',0);
insert into empleado.Producto values (1,18.5,'Fresa');
insert into empleado.Producto values (2,20.5,'Limon');
insert into empleado.Producto values (1,15.0,'Chocolate');
insert into empleado.Sucursal values('Picis 118, Capricornio','8189547','12:00-17:00');
insert into empleado.Sucursal values('Italia 52, Providencia','8189852','10:00-19:00');
insert into empleado.Stock values(1,1,10);
insert into empleado.Stock values(2,1,10);
insert into empleado.Stock values(3,1,10);
insert into empleado.Stock values(1,2,10);
insert into empleado.Stock values(2,2,10);
insert into empleado.Stock values(3,2,10);


--------------------Inserciones de Prueba Triggers de Stock-------------------------------

select * from empleado.Inventario
insert into empleado.Inventario values(1,getdate(),10);
select * from empleado.InventarioProducto
insert into empleado.InventarioProducto values(1,1,10);
select * from empleado.Stock s inner join empleado.Sucursal su on su.idSucursal = s.idSucursal  where su.idSucursal = 1