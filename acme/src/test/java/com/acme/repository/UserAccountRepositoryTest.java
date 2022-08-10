package com.acme.repository;

import com.acme.repository.interfaces.UserAccountRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.AcmeApplicationTests;
import com.acme.model.UserAccount;

public class UserAccountRepositoryTest extends AcmeApplicationTests {

	@Autowired
    UserAccountRepository userAccountRepository;

	@Test
	public void testGet() {
		UserAccount user = userAccountRepository.getUserByName("userEhr");
		Assert.assertNotNull(user);
		
		Assert.assertEquals("userEhr", user.getName());
		Assert.assertNotNull(user.getFacility());
		
	}
}
