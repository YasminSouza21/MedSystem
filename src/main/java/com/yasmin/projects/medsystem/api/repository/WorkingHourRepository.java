package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.doctor.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingHourRepository extends JpaRepository<WorkingHour, Integer> {
}
