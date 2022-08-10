//package com.acme.service;
//
//import com.acme.AcmeApplicationTests;
//import com.acme.model.PatientDisasterStatus;
//import com.acme.request.model.DisasterSummaryInput;
//import com.acme.request.model.LoginInput;
//import org.junit.Assert;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@TestMethodOrder(OrderAnnotation.class)
//public class PatientDisasterStatusServiceTest extends AcmeApplicationTests {
//
//
//	@Autowired
//    AuthService userService;
//	@Autowired
//	PatientDisasterStatusService patientDisasterStatusService;
//
//	@Be
//	@Test
//	public void testGetDisasterSummary() {
//		LoginInput loginInput = new LoginInput();
//		loginInput.setUsername("userGovt");
//
//		DisasterSummaryInput in = new DisasterSummaryInput();
//		in.setDisasterId(1001L);
//
//		List<PatientDisasterStatus> out = patientDisasterStatusService.getDisasterSummary(in);
//		Assert.assertNotNull(out);
//		Assert.assertEquals(out.size(), 1);
//	}
//
//}
