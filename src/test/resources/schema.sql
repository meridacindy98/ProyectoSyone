--creacion de las tablas
DROP TABLE IF EXISTS AutomovilOpcional;
DROP TABLE IF EXISTS Automovil;
DROP TABLE IF EXISTS TIPOAUTO;
DROP TABLE IF EXISTS Opcional;

--TipoAuto
create table TIPOAUTO(
	tipo_Auto_Id int IDENTITY(1,1) primary key not null,
	nombre varchar(50),
	precio decimal
);



--Opcional
create table Opcional(
	opcional_Id int IDENTITY(1,1) primary key not null,
	codigo varchar(5),
	nombre varchar(50),
	precio int
);



--Automovil
create table Automovil(
	automovil_Id int IDENTITY(1,1) primary key not null,
	tipo_Auto_Id int not null,
	precio_Final int
);

--AutomovilOpcional
create table AUTOMOVILOPCIONAL(
    automovil_Opcional_Id int IDENTITY(1,1) primary key not null,
    automovil_Id int not null,    
    opcional_Id int not null,
  FOREIGN KEY (automovil_Id) REFERENCES Automovil(automovil_Id),
  FOREIGN KEY (opcional_Id) REFERENCES Opcional(opcional_Id)
);


--Insert TipoAuto
insert into TipoAuto ( nombre, precio) values ( 'sedan', 230000.00);
insert into TipoAuto ( nombre, precio) values ( 'familiar', 245000.00);
insert into TipoAuto ( nombre, precio) values ('coupe', 270000.00);

--Insert Opcional
insert into Opcional ( nombre, codigo, precio ) values ('Techo corredizo', 'TC',12000.00);
insert into Opcional ( nombre, codigo, precio ) values ('Aire acondicionado', 'AA',20000.00);
insert into Opcional ( nombre, codigo, precio ) values ('Sistemas de frenos', 'ABS',14000.00);
insert into Opcional ( nombre, codigo, precio ) values ('Airbag', 'AB',7000.00, 14);
insert into Opcional ( nombre, codigo, precio ) values ('Llantas de aleación', 'LL',12000.00);

--Insert a Automoviles
insert into Automovil ( tipo_Auto_Id, precio_Final ) values ( 1, 262000.00); --1 automovilId
insert into Automovil ( tipo_Auto_Id, precio_Final ) values ( 1, 230000.00); --2 automovilId
insert into Automovil ( tipo_Auto_Id, precio_Final ) values ( 2, 276000.00); --3 automovilId
insert into Automovil ( tipo_Auto_Id, precio_Final ) values ( 3, 270000.00); --4 automovilId
insert into Automovil ( tipo_Auto_Id, precio_Final ) values ( 3, 270000.00); --5 automovilId

--Insert a AutomovilOpcional
insert into AutomovilOpcional ( automovil_Id, opcional_Id ) values (1, 1); --1 automovilId
insert into AutomovilOpcional ( automovil_Id, opcional_Id ) values (1, 2); --1 automovilId
insert into AutomovilOpcional ( automovil_Id, opcional_Id ) values (2, 1); --2 automovilId
insert into AutomovilOpcional ( automovil_Id, opcional_Id ) values (2, 2); --2 automovilId
insert into AutomovilOpcional ( automovil_Id, opcional_Id ) values (2, 3); --2 automovilId











