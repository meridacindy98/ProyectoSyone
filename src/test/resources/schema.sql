--creacion de las tablas
DROP TABLE IF EXISTS AutomovilOpcional;
DROP TABLE IF EXISTS Automovil;
DROP TABLE IF EXISTS TipoAuto;
DROP TABLE IF EXISTS Opcional;

--TipoAuto
create table TipoAuto(
tipoAutoId int identity(1,1) primary key not null,
nombre varchar(50),
cantidad int(11),
precio decimal);


--Opcional
create table Opcional(
	opcionalId int identity(1,1) primary key not null,
	codigo varchar(5),
	nombre varchar(50),
	cantidad decimal,
	precio int
);

--Automovil
create table Automovil(
	automovilId int identity(1,1) primary key not null,
	precioFinal int
);

--AutomovilOpcional
create table AutomovilOpcional(
    automovilOpcionalId int AUTO_INCREMENT primary key not null,
    automovilId int not null,
    tipoAutoId int not null,
    opcionalId int not null,
  FOREIGN KEY (automovilId) REFERENCES AutomovilOpcional(automovilOpcionalId),
  FOREIGN KEY (tipoAutoId) REFERENCES TipoAuto(tipoAutoId ),
  FOREIGN KEY (opcionalId) REFERENCES Opcional(opcionalId)
);

insert into TipoAuto (cantidad, nombre, precio) values (30, 'sedan', 230000);
insert into TipoAuto (cantidad, nombre, precio) values (60, 'familiar', 245000);
insert into TipoAuto (cantidad, nombre, precio) values ( 30,'coupe', 270000);

insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Techo corredizo', 'TC',12000, 12);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Aire acondicionado', 'AA',20000, 20);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Sistemas de frenos', 'ABS',14000, 35);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Airbag', 'AB',7000, 14);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Llantas de aleación', 'LL',12000, 6);