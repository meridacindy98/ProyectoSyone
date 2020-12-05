--creacion de las tablas
--TipoAuto
create table TipoAuto(
	tipoAutoId int identity(1,1) primary key not null,
	nombre varchar(50),
	cantidad decimal,
	precio int
)

--Opcional
create table Opcional(
	opcionalId int identity(1,1) primary key not null,
	codigo varchar(5),
	nombre varchar(50),
	cantidad decimal,
	precio int
)

--Automovil
create table Automovil(
	automovilId int identity(1,1) primary key not null,
	precioFinal int
)

--AutomovilDetalle
create table AutomovilDetalle(
	automovilDetalleId int identity(1,1) primary key not null,
	automovilId int foreign key references Automovil(automovilId) not null,
	tipoAutoId int foreign key references TipoAuto(tipoAutoId) not null,
	opcionalId int foreign key references Opcional(opcionalId) not null
)