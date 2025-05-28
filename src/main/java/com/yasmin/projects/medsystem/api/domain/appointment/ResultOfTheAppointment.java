package com.yasmin.projects.medsystem.api.domain.appointment;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultOfTheAppointment {
    @Lob
    private String diagnostic;
    @Lob
    private String summary;

}
