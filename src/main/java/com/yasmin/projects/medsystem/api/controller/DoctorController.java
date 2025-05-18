package com.yasmin.projects.medsystem.api.controller;

import com.yasmin.projects.medsystem.api.domain.doctor.RequestDoctorInfoDTO;
import com.yasmin.projects.medsystem.api.domain.doctor.RequestUpdateDoctorDTO;
import com.yasmin.projects.medsystem.api.domain.doctor.ResponseDoctorInfoDTO;
import com.yasmin.projects.medsystem.api.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
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
}
