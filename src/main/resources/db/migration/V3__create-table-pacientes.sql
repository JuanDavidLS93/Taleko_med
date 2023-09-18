create table pacientes(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documentoIdentidad varchar(50) not null unique,
    telefono varchar(20) not null,
    nomenclatura varchar(150) not null,
    barrio varchar(50) not null,
    ciudad varchar(50) not null,
    departamento varchar(100) not null,
    primary key(id)
);