package com.yasmin.projects.medsystem.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.yasmin.projects.medsystem.api.annotations.OpeningHour;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestAppointmentInfoDTO(

        @JsonAlias("patient_id")
        @NotNull
        Integer patientId,

        @JsonAlias("doctor_id")
        @NotNull
        Integer doctorId,

        @JsonAlias("date_time")
        @Future
        @NotNull
        @OpeningHour
        LocalDateTime dateTime
) {
}
