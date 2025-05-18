package com.yasmin.projects.medsystem.api.domain.doctor;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.yasmin.projects.medsystem.api.annotations.CrmUnique;
import com.yasmin.projects.medsystem.api.annotations.EmailUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RequestDoctorInfoDTO(
        @NotBlank
        String name,
        @NotBlank
        @CrmUnique
        String crm,
        @NotBlank
        String state,
        @NotBlank
        @EmailUnique
        @Email
        String email,
        @NotNull
        @NotEmpty
        List<String> specialities,
        @NotNull
        @NotEmpty
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
    ) {
    }
}
