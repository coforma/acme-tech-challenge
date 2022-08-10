package com.acme.service;

import com.acme.AcmeApplicationTests;
import com.acme.repository.UserAccountRepository;
import com.acme.request.model.LoginInput;
import com.acme.request.model.LoginOutput;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

@TestMethodOrder(OrderAnnotation.class)
public class UserAccountServiceTest extends AcmeApplicationTests {


	@Autowired
	UserService userService;
	@Autowired
	UserAccountRepository userAccountRepository;

	@Test
	public void testControllerReturnsToken() {
		LoginInput loginInput = new LoginInput();
		loginInput.setUsername("userGovt");

		LoginOutput out = userService
				.login(loginInput);
		Assert.assertNotNull(out);
		Assert.assertNotNull(out.getToken());
	}

}
