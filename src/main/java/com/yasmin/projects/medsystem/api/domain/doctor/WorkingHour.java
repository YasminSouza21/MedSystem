package com.yasmin.projects.medsystem.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public WorkingHour(LocalDate dayOfTheMount, LocalTime startHour, LocalTime endHour) {
        this.dayOfTheMount = dayOfTheMount;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @PrePersist
    @PreUpdate
    private void calculateHourPerDayAndSettingDayofTheWeek(){
        this.hoursPerDay = Duration.between(startHour, endHour).toHours();

        DayOfWeek getDay = dayOfTheMount.getDayOfWeek();
        this.dayOfTheWeek = DaysOfTheWeek.getDay(getDay);
    }

}
