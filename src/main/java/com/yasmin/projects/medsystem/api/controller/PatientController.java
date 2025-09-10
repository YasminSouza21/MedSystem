package com.yasmin.projects.medsystem.api.controller;

import com.yasmin.projects.medsystem.api.domain.patient.RequestPatientInfoDTO;
import com.yasmin.projects.medsystem.api.domain.patient.RequestUpdatePatientDTO;
import com.yasmin.projects.medsystem.api.domain.patient.ResponsePatientInfoDTO;
import com.yasmin.projects.medsystem.api.domain.patient.address.RequestUpdateAddressDTO;
import com.yasmin.projects.medsystem.api.domain.patient.address.ResponseAddressInfoDTO;
import com.yasmin.projects.medsystem.api.service.AddressService;
import com.yasmin.projects.medsystem.api.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientService patientService;
    private final AddressService addressService;

    public PatientController(PatientService patientService, AddressService addressService) {
        this.patientService = patientService;
        this.addressService = addressService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePatientInfoDTO> createPatient(@RequestBody @Valid RequestPatientInfoDTO patientInfoDTO, UriComponentsBuilder uriBuilder) {
        ResponsePatientInfoDTO patientDTO = patientService.create(patientInfoDTO);
        URI uri = uriBuilder.path("/patient/{id}").buildAndExpand(patientDTO.id()).toUri();

        return ResponseEntity.created(uri).body(patientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientInfoDTO> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ResponsePatientInfoDTO>> getAllPatients(Pageable pageable) {
        return ResponseEntity.ok(patientService.getAll(pageable));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ResponsePatientInfoDTO> getPatientByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(patientService.getByCpf(cpf));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponsePatientInfoDTO> updatePatient(@RequestBody @Valid RequestUpdatePatientDTO updatePatientDTO) {
        return ResponseEntity.ok(patientService.update(updatePatientDTO));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePatientById(@PathVariable Integer id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/address")
    @Transactional
    public ResponseEntity<ResponseAddressInfoDTO> updateAddress(@PathVariable Integer id, @RequestBody @Valid RequestUpdateAddressDTO updateAddressDTO) {
        return ResponseEntity.ok(addressService.update(id, updateAddressDTO));
    }


}
