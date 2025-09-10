CREATE TABLE addresses(
    id integer auto_increment primary key,
    cep varchar(8) not null,
    street varchar(200) not null,
    neighborhood varchar(200) not null,
    city varchar(50) not null,
    state varchar(100) not null
);

ALTER TABLE patients ADD COLUMN addresses_id int;

ALTER TABLE patients ADD CONSTRAINT fk_addresses_id FOREIGN KEY (addresses_id) REFERENCES addresses(id);

