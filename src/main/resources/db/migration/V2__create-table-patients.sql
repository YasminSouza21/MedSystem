CREATE TABLE patients(
    id integer auto_increment primary key,
    name varchar(200) not null,
    age int not null,
    email varchar(150) not null unique,
    cpf varchar(11) not null unique,
    weight double not null,
    height double not null
);