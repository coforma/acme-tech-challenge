package com.acme.repository.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.model.Facility;


@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>  {

	@Query("SELECT f FROM Facility f WHERE f.npi = :facilityNpi ")
	List<Facility> findFacilityByNpi(@Param("facilityNpi") String facilityNpi, Pageable pageable);

}
