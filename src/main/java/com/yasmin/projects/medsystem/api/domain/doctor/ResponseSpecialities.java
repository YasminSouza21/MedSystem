package com.yasmin.projects.medsystem.api.domain.doctor;

public record ResponseSpecialities(
        String specialityName
) {
    public ResponseSpecialities(Speciality speciality){
        this(speciality.getName().name());
    }
}
