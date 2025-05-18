package com.yasmin.projects.medsystem.api.domain.doctor;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "specialities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SpecialitiesNames name;

    @ManyToMany(mappedBy = "specialities")
    private List<Doctor> doctors;

}
