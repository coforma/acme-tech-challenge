package com.acme.repository;

import com.acme.repository.interfaces.StateCodeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.StateCode;

public class StateCodeRepositoryTest extends AcmeApplicationTests {

	@Autowired
    StateCodeRepository stateCodeRepository;


	
	@Test
	public void testGet() {
		StateCode stateCode = stateCodeRepository.findById(45L).orElse(null);
		Assert.assertNotNull(stateCode);
		Assert.assertEquals(45L, stateCode.getId().longValue());
		
	}
}
