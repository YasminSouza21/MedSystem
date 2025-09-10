package com.yasmin.projects.medsystem.api.domain.doctor;

import java.time.DayOfWeek;

public enum DaysOfTheWeek {
    SEGUNDA("MONDAY"),
    TERCA("TUESDAY"),
    QUARTA("WEDNESDAY"),
    QUINTA("THURSDAY"),
    SEXTA("FRIDAY"),
    SABADO("SATURDAY");

    private final String nameDayOfTheWeekInEnglish;

    DaysOfTheWeek(String nameDayOfTheWeekInEnglish){
        this.nameDayOfTheWeekInEnglish = nameDayOfTheWeekInEnglish;
    }

    public static DaysOfTheWeek getDay(DayOfWeek dayOfWeek){
        for(DaysOfTheWeek day : DaysOfTheWeek.values()){
            if(dayOfWeek.name().equals(day.nameDayOfTheWeekInEnglish)){
                return day;
            }
        }
        throw  new RuntimeException("Error in get day");
    }
}
