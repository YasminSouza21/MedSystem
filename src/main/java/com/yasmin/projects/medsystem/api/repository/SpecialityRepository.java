package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.doctor.Doctor;
import com.yasmin.projects.medsystem.api.domain.doctor.SpecialitiesNames;
import com.yasmin.projects.medsystem.api.domain.doctor.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

    @Query("""
            SELECT d FROM Doctor d
            JOIN d.specialities s
            WHERE s.name = :specialityName
            """)
    List<Doctor> findAllDoctorsBySpeciality(@Param("specialityName") SpecialitiesNames specialityName);
}
