package com.yasmin.projects.medsystem.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String crm;

    private String state;

    @Column(unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "doctors_specialities",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private List<Speciality> specialities;

    private boolean isActive;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<WorkingHour> workingHours;

    public Doctor(RequestDoctorInfoDTO doctorInfoDTO, List<Speciality> specialities, List<WorkingHour> workingHours) {
        this.name = doctorInfoDTO.name();
        this.crm = doctorInfoDTO.crm();
        this.state = doctorInfoDTO.state();
        this.email = doctorInfoDTO.email();
        this.specialities = specialities;
        this.workingHours = workingHours;
        this.isActive = true;
    }

    public Doctor update(RequestUpdateDoctorDTO doctorInfoDTO){
        if(doctorInfoDTO.name() != null){
            this.name = doctorInfoDTO.name();
        }

        if(doctorInfoDTO.crm() != null){
            this.crm = doctorInfoDTO.crm();
        }

        if(doctorInfoDTO.state() != null){
            this.state = doctorInfoDTO.state();
        }

        if(doctorInfoDTO.email() != null){
            this.email = doctorInfoDTO.email();
        }

        return this;
    }
}
