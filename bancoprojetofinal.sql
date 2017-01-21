create database maonaroda;

use maonaroda

create table tag(
	id int auto_increment primary key,
	descricao varchar(256),
	idoferta int
);

create table favorito(
	id int auto_increment primary key,
	idoferta int,
	idusuario int
); 

create table mensagem(
	id int auto_increment primary key,
	idremetente int,
	iddestinatario int,
	mensagem varchar(256),
	datahora timestamp,
	lida varchar(3)
);

create table recomendacao(
	id int auto_increment primary key,
	idavaliacao int,
	idavaliador int,
	nota int,
	mensagem varchar(256),
	datahora datetime
);

create table usuario(
	id int auto_increment primary key,
	nome varchar(256),
	foto varchar(256),
	email varchar(256),
	senha varchar(256),
	telefonecelular varchar(25),
	telefonefixo varchar(25),
	datadenascimento varchar(15),
	tipoderota varchar(15),
	endereco varchar(256)
);

create table oferta(
	id int auto_increment primary key,
	titulo varchar(100),
	descricao varchar(256),
	manha varchar(5),
	tarde varchar(5),
	noite varchar(5),
	origem varchar(256),
	destino varchar(256),
	tipooferta varchar(6),
	idusuario int,
	remunerado varchar(30),
	status varchar(30),
	foto varchar(256),
	nota decimal(10,2)
);


create table trocadesenha(
	id int auto_increment primary key,
	email varchar(256),
	url varchar(256)
);

create table contato(
	id int auto_increment primary key,
	email varchar(256),
	mensagem varchar(256),
	lida varchar(3),
	datahora datetime
);

create table erro(
	id int auto_increment primary key,
	email varchar(256),
	erro varchar(256),
	lida varchar(3),
	datahora datetime
);


/*PostGres*/

create table tag(
	id serial,
	descricao varchar(256),
	idoferta int
);

create table favorito(
	id serial,
	idoferta int,
	idusuario int
); 

create table mensagem(
	id serial,
	idremetente int,
	iddestinatario int,
	mensagem varchar(256),
	datahora timestamp,
	lida varchar(3)
);

create table recomendacao(
	id serial,
	idavaliacao int,
	idavaliador int,
	nota int,
	mensagem varchar(256),
	datahora timestamp
);

create table usuario(
	id serial,
	nome varchar(256),
	foto varchar(256),
	email varchar(256),
	senha varchar(256),
	telefonecelular varchar(25),
	telefonefixo varchar(25),
	datadenascimento varchar(15),
	tipoderota varchar(15),
	endereco varchar(256)
);

create table oferta(
	id serial,
	titulo varchar(100),
	descricao varchar(256),
	manha varchar(5),
	tarde varchar(5),
	noite varchar(5),
	origem varchar(256),
	destino varchar(256),
	tipooferta varchar(6),
	idusuario int,
	remunerado varchar(30),
	status varchar(30),
	foto varchar(256),
	nota decimal(10,2)
);


create table trocadesenha(
	id serial,
	email varchar(256),
	url varchar(256)
);

create table contato(
	id serial,
	email varchar(256),
	mensagem varchar(256),
	lida varchar(3),
	datahora timestamp
);

create table erro(
	id serial,
	email varchar(256),
	erro varchar(256),
	lida varchar(3),
	datahora timestamp
);