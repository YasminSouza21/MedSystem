package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.appointment.*;
import com.yasmin.projects.medsystem.api.domain.doctor.Doctor;
import com.yasmin.projects.medsystem.api.domain.doctor.ResponseDoctorInfoDTO;
import com.yasmin.projects.medsystem.api.domain.doctor.SpecialitiesNames;
import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import com.yasmin.projects.medsystem.api.repository.AppointmentRepository;
import com.yasmin.projects.medsystem.api.validation.Validation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final List<Validation> validations;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, DoctorService doctorService, List<Validation> validations) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.validations = validations;
    }

    public ResponseAppointmentInfoDTO create(RequestAppointmentInfoDTO appointmentInfoDTO) {

        if (!dateTimesAppointments().isEmpty()) {
            validations.forEach(v -> v.isValid(appointmentInfoDTO.dateTime(), dateTimesAppointments()));
        }

        Patient patient = patientService.findById(appointmentInfoDTO.patientId());
        Doctor doctor = doctorService.findById(appointmentInfoDTO.doctorId());

        return new ResponseAppointmentInfoDTO(appointmentRepository.save(new Appointment(appointmentInfoDTO.dateTime(), patient, doctor)));
    }

    public Page<ResponseDoctorInfoDTO> findDoctorsActives(RequestAppointmentDoctorsActiveDTO appointmentDoctorsActiveDTO, Pageable page) {
        SpecialitiesNames specialityName = SpecialitiesNames.valueOf(appointmentDoctorsActiveDTO.speciality());
        Page<Doctor> doctorsActives = appointmentRepository.findDoctorsActiveOnThisDateTime(appointmentDoctorsActiveDTO.dateTime(),
                appointmentDoctorsActiveDTO.dateTime().toLocalDate(),
                specialityName,
                appointmentDoctorsActiveDTO.dateTime().toLocalTime(),
                page);

        if(doctorsActives.isEmpty()){
            throw new EntityNotFoundException("Não possui médicos ativos");
        }

        return doctorsActives.map(ResponseDoctorInfoDTO::new);
    }

    public ResponseResultAppointmentDTO defineResult(RequestResultAppointmentDTO resultAppointmentDTO, Integer id) {
        Appointment appointment = appointmentRepository.getReferenceById(id);

        return new ResponseResultAppointmentDTO(appointmentRepository.save(appointment.updateResult(resultAppointmentDTO)));
    }


    public Page<ResponseAppointmentInfoDTO> getAllByPatient(Integer id, Pageable page) {
        Patient patient = patientService.findById(id);
        Page<Appointment> appointmentsPatient = appointmentRepository.findAllByPatient(patient, page);

        if(appointmentsPatient.isEmpty()){
            throw new EntityNotFoundException("Não possui consultas no histórico");
        }

        return appointmentsPatient.map(ResponseAppointmentInfoDTO::new);
    }

    public Page<ResponseAppointmentInfoDTO> getAllByDoctor(Integer id, Pageable page) {
        Doctor doctor = doctorService.findById(id);
        Page<Appointment> appointmentsDoctor = appointmentRepository.findAllByDoctor(doctor, page);

        if(appointmentsDoctor.isEmpty()){
            throw new EntityNotFoundException("Não possui consultas no histórico");
        }

        return appointmentsDoctor.map(ResponseAppointmentInfoDTO::new);
    }

    public ResponseAppointmentInfoDTO getById(Integer id) {
        return new ResponseAppointmentInfoDTO(appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar está consulta")));
    }

    public List<LocalDateTime> dateTimesAppointments() {
        return appointmentRepository.findAllDateTime();
    }

    public void deletedByTheDoctor(Integer id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Não foi possível fazer o cancelamento desta consulta");
        }
    }

    public void deletedByThePatient(Integer id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Não foi possível fazer o cancelamento desta consulta");
        }
    }

    public ResponseAppointmentInfoDTO update(RequestUpdateAppointmentDTO updateAppointmentDTO) {
        Appointment appointment = appointmentRepository.getReferenceById(updateAppointmentDTO.id());
        return new ResponseAppointmentInfoDTO(appointmentRepository.save(appointment.updateDateTime(updateAppointmentDTO.dateTime())));
    }
}
