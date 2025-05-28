package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.annotations.EmailUnique;
import jakarta.validation.constraints.*;

public record RequestPatientInfoDTO(
        @NotBlank
        String name,
        @NotNull
        @Min(value = 14, message = "Idade mínima para cadastrar no sistema é 14 anos")
        Integer age,
        @NotBlank
        @Email
        @EmailUnique
        String email,
        @NotBlank
        @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
        @Pattern(regexp = "^\\d{11}$",  message = "O CPF está com o formato errado, apenas dígitos!")
        String cpf,
        @NotNull
        Double weight,
        @NotNull
        Double height,
        @NotBlank
        @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 dígitos")
        @Pattern(regexp = "^\\d{8}$",  message = "O CEP está com o formato errado, apenas dígitos!")
        String cep
) {
}
