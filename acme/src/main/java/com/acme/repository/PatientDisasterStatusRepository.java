package com.acme.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.model.PatientDisasterStatus;

@Repository
public interface PatientDisasterStatusRepository extends JpaRepository<PatientDisasterStatus, Long> {

	@Query("SELECT pds FROM PatientDisasterStatus pds JOIN pds.patient p WHERE p.facility.npi = :facilityNpi and p.patientIdFromFacility = :patientIdFromFacility "
	+ " ORDER BY pds.date DESC")
	List<PatientDisasterStatus> findLatestByFacilityAndPatientFacilityId(@Param("facilityNpi") String facilityNpi,
			@Param("patientIdFromFacility") String patientIdFromFacility, Pageable pageable);

}
