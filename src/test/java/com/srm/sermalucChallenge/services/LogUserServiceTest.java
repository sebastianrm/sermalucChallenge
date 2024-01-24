package com.srm.sermalucChallenge.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.srm.sermalucChallenge.dto.entities.LogUserEntity;
import com.srm.sermalucChallenge.dto.entities.PhoneEntity;
import com.srm.sermalucChallenge.dto.entities.UserEntity;

@ActiveProfiles("test")
@SpringBootTest
class LogUserServiceTest {
	
	@Autowired
	LogUserService logUserService;
	
	private static UserEntity userEntity = new UserEntity();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		userEntity = new UserEntity("email", "name", "password", true);
		
		PhoneEntity phoneEntity = new PhoneEntity("number", "cityCode", "countryCode");
		ArrayList<PhoneEntity> arrayList = new ArrayList<PhoneEntity>();
		arrayList.add(phoneEntity);
		userEntity.setPhoneEntities(arrayList);
		
	}

	@Test
	void testRegistrerUserLog() {
		LogUserEntity registrerUserLog = logUserService.registrerUserLog(userEntity);
		
		assertNotNull(registrerUserLog);
	}

}
