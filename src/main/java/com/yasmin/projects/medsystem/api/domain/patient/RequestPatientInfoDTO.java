package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.annotations.EmailUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        String cpf,
        @NotNull
        Double weight,
        @NotNull
        Double height
) {
}
