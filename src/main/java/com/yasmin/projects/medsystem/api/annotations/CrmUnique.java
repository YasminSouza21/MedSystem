package com.yasmin.projects.medsystem.api.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CrmAlreadyRegistered.class)
public @interface CrmUnique {
    String message() default "Este crm já está registrado!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
