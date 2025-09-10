package com.yasmin.projects.medsystem.api.annotations;

import com.yasmin.projects.medsystem.api.repository.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailAlreadyRegistered implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !doctorRepository.existsByEmail(email);
    }
}
