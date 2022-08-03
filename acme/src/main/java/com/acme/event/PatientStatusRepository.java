package com.acme.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientStatusRepository extends JpaRepository<PatientDisaster, Long> {

    PatientDisaster findById(long id);
}
