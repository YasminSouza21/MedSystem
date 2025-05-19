package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.domain.appointment.Appointment;
import com.yasmin.projects.medsystem.api.domain.patient.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String cpf;
    private Double weight;
    private Double height;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addresses_id")
    private Address address;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient(RequestPatientInfoDTO patientInfoDTO, Address address) {
        this.name = patientInfoDTO.name();
        this.age = patientInfoDTO.age();
        this.email = patientInfoDTO.email();
        this.cpf = patientInfoDTO.cpf();
        this.weight = patientInfoDTO.weight();
        this.height = patientInfoDTO.height();
        this.address = address;
    }

    public Patient updateData(RequestUpdatePatientDTO updatePatientDTO){
        if(updatePatientDTO.name() != null){
            this.name = updatePatientDTO.name();
        }
        if (updatePatientDTO.age() != null) {
            this.age = updatePatientDTO.age();
        }
        if (updatePatientDTO.email() != null) {
            this.email = updatePatientDTO.email();
        }
        if (updatePatientDTO.cpf() != null) {
            this.cpf = updatePatientDTO.cpf();
        }
        if (updatePatientDTO.weight() != null) {
            this.weight = updatePatientDTO.weight();
        }
        if (updatePatientDTO.height() != null) {
            this.height = updatePatientDTO.height();
        }

        return this;
    }
}
