package com.yasmin.projects.medsystem.api.service;

import com.yasmin.projects.medsystem.api.domain.patient.address.Address;
import com.yasmin.projects.medsystem.api.domain.patient.address.GetCepDataByApi;
import com.yasmin.projects.medsystem.api.domain.patient.address.RequestUpdateAddressDTO;
import com.yasmin.projects.medsystem.api.domain.patient.address.ResponseAddressInfoDTO;
import com.yasmin.projects.medsystem.api.repository.AddressRepository;
import com.yasmin.projects.medsystem.api.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final GetCepDataByApi getCepDataByApi;
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    public AddressService(GetCepDataByApi getCepDataByApi, AddressRepository addressRepository, PatientRepository patientRepository) {
        this.getCepDataByApi = getCepDataByApi;
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
    }

    public Address getAddress(String cep){
        return new Address(getCepDataByApi.getData(cep), cep);
    }

    public ResponseAddressInfoDTO update(Integer id, RequestUpdateAddressDTO updateAddressDTO){
        if (!patientRepository.existsById(id)){
            throw new EntityNotFoundException("Paciente não encontrado!");
        }
        Address addressCurrent = addressRepository.getAddress(id);
        Address addressNew = getAddress(updateAddressDTO.cep());

        return new ResponseAddressInfoDTO(addressCurrent.update(addressNew));
    }
}
