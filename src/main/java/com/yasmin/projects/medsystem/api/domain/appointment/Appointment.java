package com.yasmin.projects.medsystem.api.domain.appointment;

import com.yasmin.projects.medsystem.api.domain.doctor.Doctor;
import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Embedded
    private ResultOfTheAppointment result;

    public Appointment(LocalDateTime dateTime, Patient patient, Doctor doctor) {
        this.dateTime = dateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.result = new ResultOfTheAppointment("", "");
    }

    public Appointment updateResult(RequestResultAppointmentDTO resultAppointmentDTO){
        if(!resultAppointmentDTO.diagnostic().isBlank()){
            result.setDiagnostic(resultAppointmentDTO.diagnostic());
        }

        if(!resultAppointmentDTO.summary().isBlank()){
            result.setSummary(resultAppointmentDTO.summary());
        }

        return this;
    }

    public Appointment updateDateTime(LocalDateTime dateTime){
        if(dateTime != null){
            this.dateTime = dateTime;
        }

        return this;
    }
}
