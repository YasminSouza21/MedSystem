package com.yasmin.projects.medsystem.api.controller;

import com.yasmin.projects.medsystem.api.domain.appointment.*;
import com.yasmin.projects.medsystem.api.domain.doctor.ResponseDoctorInfoDTO;
import com.yasmin.projects.medsystem.api.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAppointmentInfoDTO> createAppointment(@RequestBody @Valid RequestAppointmentInfoDTO appointmentInfoDTO, UriComponentsBuilder uriBuilder){
        ResponseAppointmentInfoDTO appointment = appointmentService.create(appointmentInfoDTO);
        URI uri = uriBuilder.path("/appointments/{id}").buildAndExpand(appointment.id()).toUri();
        return ResponseEntity.created(uri).body(appointment);
    }

    @PostMapping("/doctors/actives")
    @Transactional
    public ResponseEntity<Page<ResponseDoctorInfoDTO>> getAllDoctorsActives(@RequestBody @Valid RequestAppointmentDoctorsActiveDTO appointmentDoctorsActiveDTO, Pageable page){
        return ResponseEntity.ok(appointmentService.findDoctorsActives(appointmentDoctorsActiveDTO, page));
    }

    @PostMapping("/result/{id}")
    @Transactional
    public ResponseEntity<ResponseResultAppointmentDTO> defineResultAppointment(@PathVariable Integer id, @RequestBody @Valid RequestResultAppointmentDTO resultAppointmentDTO){
        return ResponseEntity.ok(appointmentService.defineResult(resultAppointmentDTO, id));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Page<ResponseAppointmentInfoDTO>> getAllAppointmentsByPatient(@PathVariable Integer id, Pageable page){
        return ResponseEntity.ok(appointmentService.getAllByPatient(id, page));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Page<ResponseAppointmentInfoDTO>> getAllAppointmentsByDoctor(@PathVariable Integer id, Pageable page){
        return ResponseEntity.ok(appointmentService.getAllByDoctor(id, page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAppointmentInfoDTO> getAppointmentById(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseAppointmentInfoDTO> updateDateTime(@RequestBody RequestUpdateAppointmentDTO updateAppointmentDTO){
        return ResponseEntity.ok(appointmentService.update(updateAppointmentDTO));
    }

    @DeleteMapping("/patient/{id}")
    @Transactional
    public ResponseEntity<ResponseAppointmentInfoDTO> appointmentDeletedByThePatient(@PathVariable Integer id){
        appointmentService.deletedByThePatient(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/doctor/{id}")
    @Transactional
    public ResponseEntity<ResponseAppointmentInfoDTO> appointmentDeletedByTheDoctor(@PathVariable Integer id) {
        appointmentService.deletedByTheDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
