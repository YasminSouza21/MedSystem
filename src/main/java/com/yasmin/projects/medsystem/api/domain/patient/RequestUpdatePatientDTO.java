package com.yasmin.projects.medsystem.api.domain.patient;

import com.yasmin.projects.medsystem.api.annotations.EmailUnique;
import jakarta.validation.constraints.*;

public record RequestUpdatePatientDTO(
        @NotNull
        Integer id,
        String name,
        @Min(value = 14, message = "Idade mínima para cadastrar no sistema é 14 anos")
        Integer age,
        @Email
        @EmailUnique
        String email,
        Double weight,
        Double height
) {
}
