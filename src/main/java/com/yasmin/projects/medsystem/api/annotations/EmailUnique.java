package com.yasmin.projects.medsystem.api.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAlreadyRegistered.class)
public @interface EmailUnique {
    String message() default "Este email já foi registrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
