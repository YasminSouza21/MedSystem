package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.doctor.Speciality;
import com.yasmin.projects.medsystem.api.repository.SpecialityRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<Speciality> getSpecialities(List<String> specialitiesNames) {
        List<Speciality> specialitiesExisting = specialityRepository.findAll();
        return specialitiesExisting.stream().filter(s -> specialitiesNames.stream().anyMatch(name -> name.equals(s.getName().name()))).toList();
    }
}

