package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
     
	@Query("SELECT p FROM Patient p WHERE p.facility.npi = :facilityNpi and p.patientIdFromFacility = :patientIdFromFacility")
	Patient findByFacilityAndPatientFacilityId(@Param("facilityNpi") String facilityNpi,
			@Param("patientIdFromFacility") String patientIdFromFacility);

    
}