--creacion de las tablas
DROP TABLE IF EXISTS TipoAuto;

--TipoAuto
create table TipoAuto(
tipoAutoId INT(11) primary key AUTO_INCREMENT not null,
nombre varchar(50),
cantidad INT(11),
precio DECIMAL);


--Opcional
--create table Opcional(
--	opcionalId int identity(1,1) primary key not null,
--	codigo varchar(5),
--	nombre varchar(50),
--	cantidad decimal,
--	precio int
--)
--
----Automovil
--create table Automovil(
--	automovilId int identity(1,1) primary key not null,
--	precioFinal int
--)
--
----AutomovilDetalle
--create table AutomovilOpcional(
--	automovilOpcionalId int identity(1,1) primary key not null,
--	automovilId int foreign key references Automovil(automovilId) not null,
--	tipoAutoId int foreign key references TipoAuto(tipoAutoId) not null,
--	opcionalId int foreign key references Opcional(opcionalId) not null
--)
--
--insert into Tipo_Auto values (30, 'sedan', 230000)
--insert into Tipo_Auto values (60, 'familiar', 245000)
--insert into Tipo_Auto values ( 30,'coupe', 270000)
--
--insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Techo corredizo', 'TC',12000, 12)
--insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Aire acondicionado', 'AA',20000, 20)
--insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Sistemas de frenos', 'ABS',14000, 35)
--insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Airbag', 'AB',7000, 14)
--insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Llantas de aleación', 'LL',12000, 6)