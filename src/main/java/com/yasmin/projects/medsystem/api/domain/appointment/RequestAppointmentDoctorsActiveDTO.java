package com.yasmin.projects.medsystem.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record DateTimeDTO(
        @JsonAlias("date_time")
        LocalDateTime dateTime
) {
}
