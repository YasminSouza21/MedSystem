CREATE TABLE appointments(
    id integer auto_increment primary key,
    date_time timestamp not null,
    patient_id integer not null,
    doctor_id integer not null,
    diagnostic text not null,
    summary text not null,

    FOREIGN KEY(doctor_id) REFERENCES doctors(id),
    FOREIGN KEY(patient_id) REFERENCES patients(id)
)