package com.yasmin.projects.medsystem.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RequestDoctorInfoDTO(
        @NotBlank
        String name,
        @NotBlank
        String crm,
        @NotBlank
        String state,
        @NotBlank
        String email,
        @NotNull
        List<String> specialities,
        @NotNull
        @JsonAlias("working_hours")
        List<RequestWorkingHourDTO> workingHours
        ) {

        public record RequestWorkingHourDTO(
                @NotNull
                @JsonAlias("day_of_the_mount")
                LocalDate dayOfTheMount,
                @NotNull
                @JsonAlias("start_hour")
                LocalTime startHour,
                @NotNull
                @JsonAlias("end_hour")
                LocalTime endHour
        ){
        }
}
