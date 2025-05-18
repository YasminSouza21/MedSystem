package com.yasmin.projects.medsystem.api.domain.doctor;

public record RequestUpdateDoctorDTO(
        String name,
        String crm,
        String state,
        String email
) {

}
