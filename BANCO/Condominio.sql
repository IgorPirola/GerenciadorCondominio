create database condominio;

use condominio;

create table Residencia (
	id int primary key auto_increment not null,
    id_prop int,
    rua varchar(100),
    numero int,
    cep varchar(10),
    em_dia boolean,
    foreign key (id_prop) references Proprietario(id)
);

create table Moradores (
	id int primary key auto_increment not null,
	id_res int,
    nome varchar(100),
    idade int,
    cpf varchar(20),
    rg varchar(20),
    foreign key (id_res) references Residencias(id)
);

create table Proprietario (
	id int primary key auto_increment not null,
    nome varchar(100),
    idade int,
    cpf varchar(20),
    rg varchar(20),
);

create table Debitos (
	id int primary key auto_increment not null,
    id_res int,
    valor double,
    mes int,
    ano int,
    foreign key (id_res) references Residencias(id)
);