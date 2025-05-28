package com.yasmin.projects.medsystem.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.yasmin.projects.medsystem.api.annotations.OpeningHour;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestAppointmentDoctorsActiveDTO(
        @JsonAlias("date_time")
        @NotNull
        @Future
        @OpeningHour
        LocalDateTime dateTime,
        @NotBlank
        String speciality
) {
}
