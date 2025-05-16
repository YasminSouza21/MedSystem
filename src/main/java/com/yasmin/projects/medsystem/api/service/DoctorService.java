package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.doctor.*;
import com.yasmin.projects.medsystem.api.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityService specialityService;
    private final WorkingHourService workingHourService;

    public DoctorService(DoctorRepository doctorRepository, SpecialityService specialityService, WorkingHourService workingHourService){
        this.doctorRepository = doctorRepository;
        this.specialityService = specialityService;
        this.workingHourService = workingHourService;
    }

    public ResponseDoctorInfoDTO saveDoctor(RequestDoctorInfoDTO doctorInfoDTO){
        Doctor doctor;
        List<Speciality> specialities = specialityService.getSpecialities(doctorInfoDTO.specialities());
        List<WorkingHour> workingHours =  workingHourService.saveWorkingHours(doctorInfoDTO.workingHours());

        doctor = doctorRepository.save(new Doctor(doctorInfoDTO, specialities, workingHours));

        for(WorkingHour workingHourElement : workingHours){
            workingHourElement.setDoctor(doctor);
        }

        return new ResponseDoctorInfoDTO(doctor);
    }

    public Page<ResponseDoctorInfoDTO> getAllDoctors(Pageable pageable){
        return doctorRepository.findAll(pageable).map(ResponseDoctorInfoDTO::new);
    }

    public ResponseDoctorInfoDTO getDoctorById(Integer id){
        return doctorRepository.findById(id).map(ResponseDoctorInfoDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
}
