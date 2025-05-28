package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.doctor.*;
import com.yasmin.projects.medsystem.api.repository.SpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<ResponseDoctorInfoDTO> getAllDoctorBySpecialities(String speciality){
        if(!speciality.isBlank()){
            List<ResponseDoctorInfoDTO> doctorInfoDTOS = specialityRepository
                    .findAllDoctorsBySpeciality(SpecialitiesNames.valueOf(speciality))
                    .stream()
                    .map(ResponseDoctorInfoDTO::new)
                    .toList();

            if(doctorInfoDTOS.isEmpty()){
                throw new EntityNotFoundException("Não foi possivel achar doutores com esta especialidade!!");
            }

            return doctorInfoDTOS;
        }
        throw new IllegalArgumentException("Especialidade tem que estar preenchida corretamente!!");
    }

    public List<ResponseSpecialities> getAll(){
        return specialityRepository.findAll().stream().map(ResponseSpecialities::new).toList();
    }
}

