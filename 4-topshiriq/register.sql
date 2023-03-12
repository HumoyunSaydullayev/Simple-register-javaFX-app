create database register;
use register;

create table user(
	id int auto_increment,
	name varchar(255),
    surname varchar(255),
    date date,
    gender varchar(255),
    password varchar(255),
    email varchar(255),
    primary key(id)
);
select * from user;