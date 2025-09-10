package com.yasmin.projects.medsystem.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "working_hours")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkingHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DaysOfTheWeek dayOfTheWeek;

    private LocalDate dayOfTheMount;

    private LocalTime startHour;

    private LocalTime endHour;

    private long hoursPerDay;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @PrePersist
    @PreUpdate
    private void calculateHourPerDayAndSettingDayofTheWeek(){
        this.hoursPerDay = Duration.between(startHour, endHour).toHours();

        DayOfWeek getDay = dayOfTheMount.getDayOfWeek();
        this.dayOfTheWeek = DaysOfTheWeek.valueOf(getDay.name());
    }

}
