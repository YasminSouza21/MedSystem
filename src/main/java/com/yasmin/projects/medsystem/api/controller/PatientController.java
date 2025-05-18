package com.yasmin.projects.medsystem.api.controller;

import com.yasmin.projects.medsystem.api.domain.patient.GetCepDataByApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    private GetCepDataByApi getCepDataByApi;


    @PostMapping("/{cep}")
    public void getAddress(@PathVariable String cep){
        getCepDataByApi.getData(cep);
    }
}
