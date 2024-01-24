package com.srm.sermalucChallenge.dto.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.srm.sermalucChallenge.dto.response.OnSuccessUserResgister;

class LogUserEntityTest {

	private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	@Test
	void testFactoryGetLogUserEntity() {
		

		OnSuccessUserResgister onSuccessUserResgister = new OnSuccessUserResgister(null, "userId", timestamp, timestamp,
				timestamp, "token", true);
		
		LogUserEntity factoryGetLogUserEntity = onSuccessUserResgister.factoryLogUserEntity();
		
		assertNotNull(factoryGetLogUserEntity);
		
	}

	@Test
	void testFactorygetOnSuccessUserResgister() {
		
		LogUserEntity logUserEntity = new LogUserEntity(null, "userId", timestamp, timestamp,
				timestamp, "token", true);
		
		OnSuccessUserResgister factorygetOnSuccessUserResgister = logUserEntity.factoryOnSuccessUserResgister();
		
		assertNotNull(factorygetOnSuccessUserResgister);
	}

	@Test
	void testLogUserEntity() {
		LogUserEntity logUserEntity = new LogUserEntity();
		
		assertNotNull(logUserEntity);
	}

	@Test
	void testLogUserEntityStringStringTimestampTimestampTimestampStringBoolean() {
		
		LogUserEntity logUserEntity = new LogUserEntity(null, "userId", timestamp, timestamp,
				timestamp, "token", true);
		
		assertNotNull(logUserEntity);
	}

	@Test
	void testLogUserEntityStringTimestampUserEntity() {
		
		UserEntity userEntity = new UserEntity("email", "name", "password", true);
		
		LogUserEntity logUserEntity = new LogUserEntity("token", timestamp,userEntity);
		
		assertNotNull(logUserEntity);
	}

}
