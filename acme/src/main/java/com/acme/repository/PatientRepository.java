package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.model.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
     
    
}