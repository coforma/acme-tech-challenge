package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.model.PatientStatus;


@Repository
public interface PatientStatusRepository extends JpaRepository<PatientStatus, Long>  {

}
