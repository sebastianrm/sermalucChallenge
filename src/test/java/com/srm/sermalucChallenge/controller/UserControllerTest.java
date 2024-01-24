
package com.srm.sermalucChallenge.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.srm.sermalucChallenge.dto.request.PhoneRequest;
import com.srm.sermalucChallenge.dto.request.UserRequest;

/**
 * Sermaluc Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @LocalServerPort
    int randomServerPort;
	
	/**
	 * Insert Correct user Registrer
	 * Test method for {@link com.srm.nisumChallenge.controller.UserController#addNewUser(com.srm.nisumChallenge.dto.request.UserRequest)}.
	 */
	@Test
	void testAddNewUser_OK() {
		
		UserRequest userRequest = new UserRequest("juan@dominio.com","Juan Rodriguez","hunter2", null);
		
		PhoneRequest phonesRequest = new PhoneRequest("1234567","01","056");
		
		userRequest.setPhonesRequest(new ArrayList<>());
		userRequest.getPhonesRequest().add(phonesRequest);
		
		ResponseEntity<String> response = testRestTemplate
				.postForEntity("http://localhost:"+randomServerPort+"/api/v1/users/add",userRequest, String.class);
		
		
		assertNotNull(response.getBody());
		
	}

}
