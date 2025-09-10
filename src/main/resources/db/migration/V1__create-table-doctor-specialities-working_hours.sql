CREATE TABLE doctors(
    id integer auto_increment primary key,
    name varchar(100) not null,
    crm varchar(6) not null unique,
    state varchar(2) not null,
    email varchar(150) not null unique,
    is_active tinyint not null
);

CREATE TABLE specialities(
    id integer auto_increment primary key,
    name varchar(50) not null
);

INSERT INTO specialities(
    name
) VALUES ('CLINICO_GERAL'),
('ORTOPEDISTA'),
('DENTISTA'),
('GINECOLOGISTA'),
('PEDIATRA'),
('DERMATOLOGISTA'),
('CARDIOLOGISTA'),
('ENDOCRINOLOGISTA');

CREATE TABLE doctors_specialities(
    doctor_id integer not null,
    speciality_id integer not null,

    FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    FOREIGN KEY (speciality_id) REFERENCES  specialities(id)
);

CREATE TABLE working_hours(
    id integer auto_increment primary key,
    day_of_the_week varchar(50) not null,
    day_of_the_mount date not null,
    start_hour time not null,
    end_hour time not null,
    hours_per_day int not null,
    doctor_id integer ,

    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
