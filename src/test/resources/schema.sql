--creacion de las tablas
DROP TABLE IF EXISTS AutomovilOpcional;
DROP TABLE IF EXISTS Automovil;
DROP TABLE IF EXISTS TIPOAUTO;
DROP TABLE IF EXISTS Opcional;

--TipoAuto
create table TIPOAUTO(
	tipoAutoId int IDENTITY(1,1) primary key not null,
	nombre varchar(50),
	cantidad int(11),
	precio decimal
);



--Opcional
create table Opcional(
	opcionalId int IDENTITY(1,1) primary key not null,
	codigo varchar(5),
	nombre varchar(50),
	cantidad decimal,
	precio int
);



--Automovil
create table Automovil(
	automovilId int IDENTITY(1,1) primary key not null,
	tipoAutoId int not null,
	precioFinal int
);

--AutomovilOpcional
create table AUTOMOVILOPCIONAL(
    automovilOpcionalId int IDENTITY(1,1) primary key not null,
    automovilId int not null,    
    opcionalId int not null,
  FOREIGN KEY (automovilId) REFERENCES Automovil(automovilId),
  FOREIGN KEY (opcionalId) REFERENCES Opcional(opcionalId)
);
insert into TipoAuto (cantidad, nombre, precio) values (30, 'sedan', 230000.00);
insert into TipoAuto (cantidad, nombre, precio) values (60, 'familiar', 245000.00);
insert into TipoAuto (cantidad, nombre, precio) values (1,'coupe', 270000.00);

insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Techo corredizo', 'TC',12000.00, 12);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Aire acondicionado', 'AA',20000.00, 20);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Sistemas de frenos', 'ABS',14000.00, 35);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Airbag', 'AB',7000.00, 14);
insert into Opcional ( nombre, codigo, precio, cantidad ) values ('Llantas de aleación', 'LL',12000.00, 0);

--Insert a Automoviles
insert into Automovil ( tipoAutoId, precioFinal ) values ( 1, 262000.00); --1 automovilId
insert into Automovil ( tipoAutoId, precioFinal ) values ( 1, 230000.00); --2 automovilId
insert into Automovil ( tipoAutoId, precioFinal ) values ( 2, 276000.00); --3 automovilId
insert into Automovil ( tipoAutoId, precioFinal ) values ( 3, 270000.00); --4 automovilId
insert into Automovil ( tipoAutoId, precioFinal ) values ( 3, 270000.00); --5 automovilId

--Insert a AutomovilOpcional
insert into AutomovilOpcional ( automovilId, opcionalId ) values (1, 1); --1 automovilId
insert into AutomovilOpcional ( automovilId, opcionalId ) values (1, 2); --1 automovilId
insert into AutomovilOpcional ( automovilId, opcionalId ) values (2, 1); --2 automovilId
insert into AutomovilOpcional ( automovilId, opcionalId ) values (2, 2); --2 automovilId
insert into AutomovilOpcional ( automovilId, opcionalId ) values (2, 3); --2 automovilId











