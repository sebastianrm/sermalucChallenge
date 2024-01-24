package com.srm.sermalucChallenge.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.srm.sermalucChallenge.dto.request.PhoneRequest;
import com.srm.sermalucChallenge.dto.request.UserRequest;
import com.srm.sermalucChallenge.dto.response.OnSuccessUserResgister;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {
	
	@Autowired
	UserService userService;

	private static UserRequest userRequest=null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		userRequest = new UserRequest("juan@dominio.com", "name", "password", true);
		
		PhoneRequest phoneRequest = new PhoneRequest("1234567", "1", "57");
		ArrayList<PhoneRequest> arrayList = new ArrayList<PhoneRequest>();
		arrayList.add(phoneRequest);
		userRequest.setPhonesRequest(arrayList);
		
	}
	
	@Test
	void testRegisterUser() {
		OnSuccessUserResgister registerUser = userService.registerUser(userRequest);
		
		assertNotNull(registerUser);
	}

}
