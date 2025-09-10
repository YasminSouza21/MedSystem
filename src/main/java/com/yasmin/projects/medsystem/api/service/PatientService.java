package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import com.yasmin.projects.medsystem.api.domain.patient.RequestPatientInfoDTO;
import com.yasmin.projects.medsystem.api.domain.patient.RequestUpdatePatientDTO;
import com.yasmin.projects.medsystem.api.domain.patient.ResponsePatientInfoDTO;
import com.yasmin.projects.medsystem.api.domain.patient.address.Address;
import com.yasmin.projects.medsystem.api.domain.patient.address.GetCepDataByApi;
import com.yasmin.projects.medsystem.api.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AddressService addressService;

    public PatientService(PatientRepository patientRepository, GetCepDataByApi getCepDataByApi, AddressService addressService) {
        this.patientRepository = patientRepository;
        this.addressService = addressService;
    }

    public ResponsePatientInfoDTO create(RequestPatientInfoDTO patientInfoDTO) {
        Address address = addressService.getAddress(patientInfoDTO.cep());
        Patient patient = new Patient(patientInfoDTO, address);
        address.setPatient(patient);

        return new ResponsePatientInfoDTO(patientRepository.save(patient));
    }

    public Page<ResponsePatientInfoDTO> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable).map(ResponsePatientInfoDTO::new);
    }

    public ResponsePatientInfoDTO getById(Integer id) {
        if (!patientRepository.existsById(id)){
            throw new EntityNotFoundException("Paciente não encontrado!");
        }
        return new ResponsePatientInfoDTO(patientRepository.findById(id).orElseThrow());
    }

    public ResponsePatientInfoDTO getByCpf(String cpf) {
        if (!patientRepository.existsByCpf(cpf)){
            throw new EntityNotFoundException("Paciente não encontrado!");
        }
        return new ResponsePatientInfoDTO(patientRepository.getByCpf(cpf));
    }

    public ResponsePatientInfoDTO update(RequestUpdatePatientDTO updatePatientDTO) {
        if (!patientRepository.existsById(updatePatientDTO.id())) {
            throw new EntityNotFoundException("Paciente não encontrado!");
        }

        Patient patient = patientRepository.getReferenceById(updatePatientDTO.id());
        return new ResponsePatientInfoDTO(patientRepository.save(patient));
    }

    public void delete(Integer id) {
        if (!patientRepository.existsById(id)){
            throw new EntityNotFoundException("Paciente não encontrado!");
        }
        patientRepository.deleteById(id);
    }
}
