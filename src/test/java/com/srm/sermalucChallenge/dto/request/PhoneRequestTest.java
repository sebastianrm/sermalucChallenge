package com.srm.sermalucChallenge.dto.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.srm.sermalucChallenge.dto.entities.PhoneEntity;

class PhoneRequestTest {
	
	private final PhoneRequest phoneRequest = new PhoneRequest("number", "cityCode", "countryCode");

	@Test
	void testFactoryPhonesRequest() {
		
		PhoneRequest factoryPhonesRequest = phoneRequest.factoryPhonesRequest();
		
		
		assertEquals(phoneRequest, factoryPhonesRequest);
	}

	@Test
	void testFactoryPhoneEntity() {
		PhoneEntity factoryPhoneEntity = phoneRequest.factoryPhoneEntity();
		
		assertNotNull(factoryPhoneEntity);
	}

	@Test
	void testPhoneRequest() {
		PhoneRequest phoneRequest2 = new PhoneRequest();
		
		assertNotNull(phoneRequest2);
		
	}

	@Test
	void testPhoneRequestStringStringString() {
		PhoneRequest phoneRequest2 = new PhoneRequest("number", "cityCode", "countryCode");
		assertNotNull(phoneRequest2);
	}

}
