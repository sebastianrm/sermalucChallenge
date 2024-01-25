package com.srm.sermalucChallenge.dto.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.srm.sermalucChallenge.dto.request.UserRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class UserEntityTest {
	
	
	private static UserEntity userEntity = null;

	private static Validator validator;
	
	
	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		userEntity = new UserEntity("juanaaa@dominio.com", "name", "password", true);
		
	}
	
	@Test
	void testFactoryUserRequest() {
		UserRequest factoryUserRequest = userEntity.factoryUserRequest();
		assertNotNull(factoryUserRequest);
				
	}

	@Test
	void testFactoryUserEntity() {
		UserEntity factoryUserEntity = userEntity.factoryUserEntity();
		assertEquals(userEntity, factoryUserEntity);
	}

	@Test
	void testUserEntity() {
		UserEntity userEntity2 = new UserEntity();
		assertNotNull(userEntity2);
	}

	@Test
	void testUserEntityStringStringStringBoolean() {
		Set<ConstraintViolation<UserEntity>> validate = validator.validate(userEntity);
		
		assertEquals(0,validate.size());
	}

}
