package com.acme.repository;

import com.acme.model.Facility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.model.Disaster;
import com.acme.model.StateCode;

import java.util.List;


@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long>  {

}
