package com.srm.sermalucChallenge.services;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srm.sermalucChallenge.dto.entities.LogUserEntity;
import com.srm.sermalucChallenge.dto.entities.UserEntity;
import com.srm.sermalucChallenge.repository.LogUserRepository;
import com.srm.sermalucChallenge.utils.JwtUtils;

import jakarta.validation.Valid;

/**
 * Sermaluc Challenge
 *
 * For Log Services
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class LogUserService {

	private static final Logger LOG = LogManager.getLogger(LogUserService.class);

	@Autowired
	LogUserRepository logUserRepository;

	@Autowired
	private JwtUtils jwtUtils;

	/**
	 * Registrer User Log form UserEntity
	 * 
	 * @param userEntity
	 * @return LogUserEntity
	 */
	public LogUserEntity registrerUserLog(@Valid UserEntity userEntity) {

		String generateJwtToken = jwtUtils.generateJwtToken(userEntity.getEmail());
		LogUserEntity logUserEntity = new LogUserEntity(generateJwtToken, new Timestamp(System.currentTimeMillis()),
				userEntity);
		LogUserEntity saveLogUserEntity = saveLogUserEntity(logUserEntity);

		return saveLogUserEntity;
	}

	/**
	 * Save LogUserEntity
	 * 
	 * @param logUserEntity
	 * @return
	 */
	public LogUserEntity saveLogUserEntity(@Valid LogUserEntity logUserEntity) {

		LogUserEntity save = null;
		if (logUserEntity != null) {
			try {
				save = logUserRepository.save(logUserEntity);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		return save;

	}
}
