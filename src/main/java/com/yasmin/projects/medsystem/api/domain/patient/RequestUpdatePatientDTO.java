package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.annotations.EmailUnique;
import jakarta.validation.constraints.*;

public record RequestUpdatePatientDTO(
        @NotNull
        Integer id,
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
        @NotBlank
        @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
        @Pattern(regexp = "^\\d{11}$",  message = "O CPF está com o formato errado, apenas dígitos!")
        String cpf,
        @NotNull
        Double weight,
        @NotNull
        Double height
) {
}
