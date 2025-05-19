package com.yasmin.projects.medsystem.api.domain.appointment;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

@Embeddable
public class ResultOfTheAppointment {
    @Lob
    private String diagnostic;
    @Lob
    private String summary;
}
