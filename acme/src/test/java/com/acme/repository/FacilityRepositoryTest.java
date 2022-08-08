package com.acme.repository;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

	static Long facilityId = null;
	@Autowired
	FacilityRepository facilityRepository;
	
	@Autowired
	StateCodeRepository stateCodeRepository;

	
	@Order(1)  
	@Test
	public void testSave() {
		Facility facility =  new Facility();
		facility.setFacilityName("HeadeQaurters");
		facility.setIsOpen(true);
		facility.setNpi("21212121212L");
		
		StateCode stateCode = stateCodeRepository.findById(45L).orElse(null);
		Assert.assertNotNull(stateCode);
		
		facility.setStateCode(stateCode);
		
		facility = facilityRepository.save(facility);
		Assert.assertNotNull(facility);
		Assert.assertNotNull(facility.getId());
		facilityId = facility.getId();
		
	}
	
	@Order(2)  
	@Test
	public void testGet() {
		Facility facility = facilityRepository.findById(facilityId).orElse(null);
		Assert.assertNotNull(facility);
		
		Assert.assertEquals(facilityId, facility.getId());
		Assert.assertEquals("HeadeQaurters", facility.getFacilityName());
		Assert.assertEquals("21212121212L", facility.getNpi());
		
		
	}
	
	@Order(3)  
	@Test
	public void testDelete() {
		facilityRepository.deleteById(facilityId);
		
	}
	
	@AfterAll
	@BeforeAll
	public static void common() {
		facilityId = null;
		
	}
}
