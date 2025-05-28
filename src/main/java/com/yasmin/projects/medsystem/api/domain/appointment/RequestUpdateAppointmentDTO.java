package com.yasmin.projects.medsystem.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasmin.projects.medsystem.api.annotations.OpeningHour;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestUpdateAppointmentDTO(
        @NotNull
        Integer id,
        @NotNull
        @JsonProperty("date_time")
        @OpeningHour
        LocalDateTime dateTime
) {
}
