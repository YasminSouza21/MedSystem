CREATE TABLE addresses(
    id integer auto_increment primary key,
    street varchar(200) not null,
    neighborhood varchar(200) not null,
    city varchar(50) not null,
    state varchar(100) not null,
    patient_id integer not null,

    FOREIGN KEY (patient_id) REFERENCES patients(id)
);

ALTER TABLE patients ADD COLUMN addresses_id int;

ALTER TABLE patients ADD CONSTRAINT fk_addresses_id FOREIGN KEY (addresses_id) REFERENCES addresses(id);

