package com.yasmin.projects.medsystem.api.domain.patient;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CepDataDTO(
        @JsonAlias("logradouro")
        String street,
        @JsonAlias("bairro")
        String neighborhood,
        @JsonAlias("localidade")
        String city,
        @JsonAlias("estado")
        String state
) {
}
