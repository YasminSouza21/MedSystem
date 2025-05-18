package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    boolean existsByEmail(String email);

    boolean existsByCrm(String crm);
}
