package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.model.PatientDisasterStatus;

@Repository
public interface PatientDisasterStatusRepository extends JpaRepository<PatientDisasterStatus, Long> {

    PatientDisasterStatus findById(long id);
}
