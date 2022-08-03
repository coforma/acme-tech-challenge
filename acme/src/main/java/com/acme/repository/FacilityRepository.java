package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.model.Facility;
import com.acme.model.StateCode;


@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>  {

}
