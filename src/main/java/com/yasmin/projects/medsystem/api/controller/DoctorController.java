package com.yasmin.projects.medsystem.api.controller;

import com.yasmin.projects.medsystem.api.domain.doctor.*;
import com.yasmin.projects.medsystem.api.service.DoctorService;
import com.yasmin.projects.medsystem.api.service.SpecialityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final SpecialityService specialityService;

    public DoctorController(DoctorService doctorService, SpecialityService specialityService) {
        this.doctorService = doctorService;
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseDoctorInfoDTO>> getAllDoctors(Pageable pageable){
        return ResponseEntity.ok(doctorService.getAllDoctors(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDoctorInfoDTO> getDoctorById(@PathVariable Integer id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDoctorInfoDTO> createDoctor(@RequestBody @Valid RequestDoctorInfoDTO doctorInfoDTO, UriComponentsBuilder uriBuilder){
        ResponseDoctorInfoDTO responseDoctor = doctorService.saveDoctor(doctorInfoDTO);

        URI uri = uriBuilder.path("doctor/{id}").buildAndExpand(responseDoctor.id()).toUri();

        return ResponseEntity.created(uri).body(responseDoctor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDoctorInfoDTO> updateDoctor(@PathVariable Integer id, @RequestBody @Valid RequestUpdateDoctorDTO requestUpdateDoctorDTO){
        return ResponseEntity.ok(doctorService.updateDoctor(requestUpdateDoctorDTO, id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseDoctorInfoDTO> deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/specialities")
    public ResponseEntity<List<ResponseSpecialities>> getAllSpecialities(){
        return ResponseEntity.ok(specialityService.getAll());
    }

    @GetMapping("/specialities/{specialityName}")
    public ResponseEntity<List<ResponseDoctorInfoDTO>> getAllSpecialities(@PathVariable String specialityName){
        return ResponseEntity.ok(specialityService.getAllDoctorBySpecialities(specialityName));
    }
}
