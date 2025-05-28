package com.yasmin.projects.medsystem.api.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OpeningHourImpl implements ConstraintValidator<OpeningHour, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext constraintValidatorContext) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        if (!dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            LocalTime time = dateTime.toLocalTime();

            if (!dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return time.isAfter(LocalTime.of(6, 59)) && time.isBefore(LocalTime.of(12, 0))
                        || time.isAfter(LocalTime.of(12, 59)) && time.isBefore(LocalTime.of(18, 0));
            }

            return time.isAfter(LocalTime.of(6, 59)) && time.isBefore(LocalTime.of(12, 0));
        }

        return false;
    }
}
