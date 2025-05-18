package com.yasmin.projects.medsystem.api.domain.patient;

public record ResponsePatientInfoDTO(
        String id,
        String name,
        Integer age,
        String email,
        String cpf,
        Double weight,
        Double height
) {
}
