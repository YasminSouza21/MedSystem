package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
