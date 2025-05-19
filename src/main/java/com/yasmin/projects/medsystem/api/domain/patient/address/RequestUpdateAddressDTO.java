package com.yasmin.projects.medsystem.api.domain.patient.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestUpdateAddressDTO(
        @NotBlank
        @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 dígitos")
        @Pattern(regexp = "^\\d{8}$",  message = "O CEP está com o formato errado, apenas dígitos!")
        String cep
) {
}
