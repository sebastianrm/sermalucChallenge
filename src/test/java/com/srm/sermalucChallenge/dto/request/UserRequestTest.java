package com.srm.sermalucChallenge.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.srm.sermalucChallenge.dto.entities.UserEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@TestPropertySource("classpath:i18n/messages.properties")
class UserRequestTest {
	
	private static UserRequest userRequest = null;
	
	private static Validator validator;
	
	@BeforeAll
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		userRequest = new UserRequest("email","name","password",true);
		
	}

	@Test
	void testFactoryUserRequest() {
		UserRequest factoryUserRequest = userRequest.factoryUserRequest();
		assertEquals(userRequest, factoryUserRequest);
	}

	@Test
	void testFactoryUserEntity() {
		UserEntity factoryUserEntity = userRequest.factoryUserEntity();
		assertNotNull(factoryUserEntity);
	}

	@Test
	void testUserRequestStringStringStringBoolean() {
		UserRequest userRequest = new UserRequest("email","name","password",true);
		assertNotNull(userRequest);
	}

	@Test
	void testUserRequest() {
		UserRequest userRequest = new UserRequest();
		assertNotNull(userRequest);
	}
	
	
	/**
	 * Para name Null Valido la cantidad de mensajes de error
	 */
	@Test
	void testUserRequest_NullEmailPasswordListOfPhonesRequest() {

		UserRequest userRequest = new UserRequest(null, "NAME", "password", null);

		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

		assertEquals(violations.size(), 1);
	}

	/**
	 * Para name Empty Valido la cantidad de mensajes de error
	 */
	@Test
	void testUserRequest_EmptyEmailPasswordListOfPhonesRequest() {

		UserRequest userRequest = new UserRequest("", "NAME", "password", null);

		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

		assertEquals(2,violations.size());
	}

}
