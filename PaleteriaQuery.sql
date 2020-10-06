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


create rule rlTipoCliente as @tipoCliente in(
	'Mayoreo','Menudeo'
);

exec sp_bindrule 'rlTipoCliente','empleado.Cliente.TipoCliente';

create rule rlMayorCero AS @range> 0
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.subTotal';
exec sp_bindrule 'rlMayorCero','empleado.Producto.precio';
exec sp_bindrule 'rlMayorCero','empleado.Categoria.tamaño';
exec sp_bindrule 'rlMayorCero','empleado.InventarioProducto.cantidadRecibida';
exec sp_bindrule 'rlMayorCero','empleado.DetalleVenta.unidades';



create rule rlPosiritvos AS @range>= 0
exec sp_bindrule 'rlMayorCero','empleado.Stock.existencias';
exec sp_bindrule 'rlMayorCero','empleado.Venta.montoTotal';
