package com.acme.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.Facility;
import com.acme.model.StateCode;

@TestMethodOrder(OrderAnnotation.class)
public class FacilityRepositoryTest extends AcmeApplicationTests {

	@Autowired
	FacilityRepository facilityRepository;

	@Ignore
	@Order(1)  
	@Test
	public void testSave() {
		Facility facility =  new Facility();
		facility.setFacilityName("HeadeQaurters");
		facility.setIsOpen(true);
		facility.setNpi("npi1234");
		
		StateCode stateCode = new StateCode();
		stateCode.setId(1L);
		stateCode.setName("ACCEPTED");
		stateCode.setCode("ACC");
		facility.setStateCode(stateCode);
		
		facility = facilityRepository.save(facility);
		Assert.assertNotNull(facility);
		Assert.assertNotNull(facility.getId());
		
	}
	
	@Order(2)  
	@Test
	public void testGet() {
		List<Facility> facilities = facilityRepository.findAll();
		Assert.assertNotNull(facilities);
		Assert.assertFalse("empty", facilities.isEmpty());
		Assert.assertEquals(1L, facilities.get(0).getId().longValue());
		Assert.assertEquals("HeadeQaurters", facilities.get(0).getFacilityName());
		Assert.assertEquals("npi1234", facilities.get(0).getNpi());
		
		Assert.assertTrue(facilities.get(0).getIsOpen());
		StateCode statecode = facilities.get(0).getStateCode();
		Assert.assertNotNull(statecode);
		Assert.assertEquals("ACC", statecode.getCode());
		Assert.assertEquals("ACCEPTED", statecode.getName());
	}
}
