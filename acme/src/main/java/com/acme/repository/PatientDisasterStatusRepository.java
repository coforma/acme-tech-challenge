package com.acme.repository;

import java.util.List;

import com.acme.common.DisasterSummaryResult;
import com.acme.request.model.DisasterSummaryOutput;
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

	@Query(value = "SELECT status, count(*) as total FROM " +
		"(SELECT * FROM " +
			"(SELECT pds.id, pds.patientId, pds.disasterId, pds.date, pds.statusId, ps.status, f.npi, f.stateCode " +
				"FROM PatientDisasterStatus pds " +
				"JOIN Patient as p ON pds.patientId = p.id " +
				"JOIN Facility as f ON p.facilityNpi = f.npi " +
				"JOIN PatientStatus as ps ON pds.statusId = ps.id " +
				"WHERE pds.disasterId = ?1 " +
					"AND (?2 IS NULL OR f.npi = ?2) " +
					"AND (?4 IS NULL OR f.stateCode = ?4) " +
					"AND (?5 IS NULL OR ps.status = ?5) " +
					"AND (?3 IS NULL OR pds.date <= ?3) " + // TODO: TIMEFRAME LOGIC
				") tmp " +
			"WHERE id IN (SELECT MAX(id) FROM PatientDisasterStatus GROUP BY patientId)) tmp2 " +
		"GROUP BY status",
		nativeQuery = true)
	List<DisasterSummaryResult> findDisasterSummary(Long disasterId,
													Long facilityNpi,
													String timeFrame,
													Integer stateId,
													String status);

}
