package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.appointment.Appointment;
import com.yasmin.projects.medsystem.api.domain.doctor.Doctor;
import com.yasmin.projects.medsystem.api.domain.doctor.SpecialitiesNames;
import com.yasmin.projects.medsystem.api.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.isActive = true
            AND d NOT IN(
               SELECT d2 FROM Doctor d2
               JOIN d2.appointments a
               WHERE a.dateTime = :dateTime
            )
            AND d IN(
               SELECT d3 FROM Doctor d3
               JOIN d3.specialities s
               WHERE s.name = :speciality
            )
            AND d IN(
               SELECT d4 FROM Doctor d4
               JOIN d4.workingHours w
               WHERE w.dayOfTheMount = :date
               AND :time BETWEEN w.startHour AND w.endHour
            )
            """)
    Page<Doctor> findDoctorsActiveOnThisDateTime(@Param("dateTime") LocalDateTime dateTime,
                                                 @Param("date") LocalDate date,
                                                 @Param("speciality") SpecialitiesNames speciality,
                                                 @Param("time") LocalTime time,
                                                 Pageable page);

    Page<Appointment> findAllByPatient(Patient patient, Pageable page);

    Page<Appointment> findAllByDoctor(Doctor doctor, Pageable page);

    @Query("""
            SELECT a.dateTime FROM Appointment a
            """)
    List<LocalDateTime> findAllDateTime();
}
