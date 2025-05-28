package com.yasmin.projects.medsystem.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;

public record RequestResultAppointmentDTO(
        @NotBlank
        String diagnostic,
        @NotBlank
        String summary
) {
}
