package com.yasmin.projects.medsystem.api.repository;

import com.yasmin.projects.medsystem.api.domain.patient.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("""
            SELECT p.address FROM Patient p
            WHERE p.id = :id
            """)
    Address getAddress(@Param("id") Integer id);
}
