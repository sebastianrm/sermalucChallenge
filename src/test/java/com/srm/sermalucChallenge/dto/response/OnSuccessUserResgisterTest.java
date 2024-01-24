package com.srm.sermalucChallenge.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.srm.sermalucChallenge.dto.entities.LogUserEntity;

class OnSuccessUserResgisterTest {

	private static OnSuccessUserResgister onSuccessUserResgister;
	
	private static Timestamp now;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		now = new Timestamp(System.currentTimeMillis());
		onSuccessUserResgister = new OnSuccessUserResgister("is", "userId", now, now, now, "token", true);	

	}

	@Test
	void testFactoryGetLogUserEntity() {
		LogUserEntity factoryLogUserEntity = onSuccessUserResgister.factoryLogUserEntity();
		
		assertNotNull(factoryLogUserEntity);
		
	}

	@Test
	void testFactorygetOnSuccessUserResgister() {
		OnSuccessUserResgister factoryOnSuccessUserResgister = onSuccessUserResgister.factoryOnSuccessUserResgister();
		
		assertEquals(onSuccessUserResgister, factoryOnSuccessUserResgister);
	}

	@Test
	void testOnSuccessUserResgister() {
		OnSuccessUserResgister onSuccessUserResgister2 = new OnSuccessUserResgister();
		assertNotNull(onSuccessUserResgister2);
	}

	@Test
	void testOnSuccessUserResgisterStringStringTimestampTimestampTimestampStringBoolean() {
		OnSuccessUserResgister onSuccessUserResgister2 = new OnSuccessUserResgister("is", "userId", now, now, now, "token", true);
		assertNotNull(onSuccessUserResgister2);
	}

}
