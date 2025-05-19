package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.domain.patient.address.ResponseAddressInfoDTO;

public record ResponsePatientInfoDTO(
        Integer id,
        String name,
        Integer age,
        String email,
        String cpf,
        Double weight,
        Double height,
        ResponseAddressInfoDTO address
) {
    public ResponsePatientInfoDTO(Patient patient) {
        this(patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getWeight(),
                patient.getHeight(),
                new ResponseAddressInfoDTO(patient.getAddress()));
    }
}
