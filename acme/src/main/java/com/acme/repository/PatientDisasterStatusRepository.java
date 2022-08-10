package com.acme.repository;

import java.util.List;

import org.springframework.data.domain.Example;
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


	@Query(value = "SELECT DISTINCT patientId, disasterId FROM " +
			"(SELECT pds FROM PatientDisasterStatus pds" +
			"JOIN pds.patient p " +
			"JOIN pds.pateintStatus ps" +
			"WHERE p.facility.npi = :facilityNpi " +
				"AND pds.disasterId = :disasterId" +
			"ORDER BY pds.date DESC) tmp",
			nativeQuery = true)
	List<PatientDisasterStatus> findDisasterSummary( @Param("disasterId") Long disasterId,
													 @Param("facilityNpi") Long facilityNpi);
//													 @Param("timeFrame") String timeFrame,
//													 @Param("stateId") Integer stateId,
//													 @Param("statusId") Integer statusId);

}
