create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(50) not null unique,
    especialidad varchar(100) not null,
    nomenclatura varchar(150) not null,
    barrio varchar(50) not null,
    ciudad varchar(50) not null,
    departamento varchar(100) not null,
    primary key(id)
);