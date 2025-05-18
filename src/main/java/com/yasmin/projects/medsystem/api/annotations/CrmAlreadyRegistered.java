package com.yasmin.projects.medsystem.api.annotations;

import com.yasmin.projects.medsystem.api.repository.DoctorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrmAlreadyRegistered implements ConstraintValidator<CrmUnique, String> {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public boolean isValid(String crm, ConstraintValidatorContext constraintValidatorContext) {
        return !doctorRepository.existsByCrm(crm);
    }
}
