package com.srm.sermalucChallenge.dto.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.srm.sermalucChallenge.dto.request.PhoneRequest;

class PhoneEntityTest {

	private final PhoneEntity phoneEntity = new PhoneEntity("number", "CITYcODE", "countryCode");

	@Test
	void testFactoryPhonesRequest() {

		PhoneRequest factoryPhonesRequest = phoneEntity.factoryPhonesRequest();

		assertNotNull(factoryPhonesRequest);
	}

	@Test
	void testFactoryPhoneEntity() {
		
		PhoneEntity factoryPhoneEntity = phoneEntity.factoryPhoneEntity();
		
		assertEquals(phoneEntity, factoryPhoneEntity);
	}

	@Test
	void testPhoneEntity() {

		PhoneEntity phoneEntity2 = new PhoneEntity();
		
		assertNotNull(phoneEntity2);
	
	}

	@Test
	void testPhoneEntityStringStringString() {
		
		PhoneEntity phoneEntitys2= new PhoneEntity("number", "CITYcODE", "countryCode");
		
		assertNotNull(phoneEntitys2);
				
	}

}
