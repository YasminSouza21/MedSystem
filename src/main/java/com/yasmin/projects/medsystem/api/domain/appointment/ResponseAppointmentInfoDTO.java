package com.yasmin.projects.medsystem.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseAppointmentInfoDTO(
        Integer id,
        @JsonProperty("patient_name")
        String namePatient,
        @JsonProperty("doctor_name")
        String nameDoctor,
        LocalDate date,
        LocalTime time,
        String diagnostic,
        String summary
) {
    public ResponseAppointmentInfoDTO(Appointment appointment) {
        this(appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDateTime().toLocalDate(),
                appointment.getDateTime().toLocalTime(),
                appointment.getResult().getDiagnostic().isBlank() ? "diagnostico da consulta ainda não estabelecido" : appointment.getResult().getDiagnostic(),
                appointment.getResult().getSummary().isBlank() ? "resumo da consulta ainda não estabelecido" : appointment.getResult().getSummary());
    }
}
