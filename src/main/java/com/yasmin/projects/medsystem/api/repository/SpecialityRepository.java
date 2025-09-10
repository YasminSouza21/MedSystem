package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.doctor.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
}
