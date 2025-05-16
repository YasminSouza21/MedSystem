package com.yasmin.projects.medsystem.api.domain.doctor;

import java.util.List;

public record ResponseDoctorInfoDTO(
        Integer id,
        String name,
        String crm,
        String state,
        String email,
        List<String> specialities
) {
    public ResponseDoctorInfoDTO(Doctor doctor) {
        this(doctor.getId(),
                doctor.getName(),
                doctor.getCrm(),
                doctor.getState(),
                doctor.getEmail(),
                doctor.getSpecialities().stream()
                    .map(speciality -> speciality.getName().name())
                    .toList());
    }
}
