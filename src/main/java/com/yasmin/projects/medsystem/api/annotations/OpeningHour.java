package com.yasmin.projects.medsystem.api.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OpeningHourImpl.class)
public @interface OpeningHour {
    String message() default "O horário de funcionamente de SEG-SEX é das 7:00-12:00/13:00-18:00 e de SÁB é das 7:00-12:00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
