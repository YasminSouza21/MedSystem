package com.yasmin.projects.medsystem.api.domain.appointment;

public record ResponseResultAppointmentDTO(
        Integer appointmentId,
        String diagnostic,
        String summary
) {
    public ResponseResultAppointmentDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getResult().getDiagnostic(), appointment.getResult().getSummary());
    }
}
