create database tcc;
use tcc;

create table produtos(
	id int primary key auto_increment,
    produto varchar(50),
    descricao varchar(50),
    quantidade int
);

create table usuarios (
	idusu int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(15) not null unique, -- unique não permite valores duplicados (exemplo: não vai conseguir criar um login com o nome de usuario)
    senha varchar(255) not null,
    perfil varchar(50) not null
);

select * from usuarios;

insert into usuarios(usuario,login,senha,perfil)
values ('Administrador','admin',md5('admin'),'admin');

insert into produtos(produto,descricao,quantidade) values ("Coca Cola", "Latinha", 3);

create table lixo(
		idlixo int primary key auto_increment,
        tipolixo varchar(50) not null,
        nome varchar(50) not null
);

alter table lixo add column fotolixo longblob after caminhoimg;


create table lixeira(
		idlixeira int primary key auto_increment,
        tipolixeira varchar(50) not null,
        cor varchar(50) not null
);

show tables;

select * from lixo;