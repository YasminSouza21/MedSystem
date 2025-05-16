package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.doctor.RequestDoctorInfoDTO;
import com.yasmin.projects.medsystem.api.domain.doctor.WorkingHour;
import com.yasmin.projects.medsystem.api.repository.WorkingHourRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingHourService {

    private final WorkingHourRepository workingHourRepository;

    public WorkingHourService(WorkingHourRepository workingHourRepository){
        this.workingHourRepository = workingHourRepository;
    }

    public List<WorkingHour> saveWorkingHours(List<RequestDoctorInfoDTO.RequestWorkingHourDTO> workingHourDTO){
        if(!workingHourDTO.isEmpty()){
            return workingHourDTO.stream().map(w -> new WorkingHour(w.dayOfTheMount(), w.startHour(), w.endHour())).toList();
        }

        throw new NullPointerException("List working hour is empty");
    }
}
