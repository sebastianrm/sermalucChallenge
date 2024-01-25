package com.srm.sermalucChallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.srm.sermalucChallenge.Exceptions.CustomDataConstraintException;
import com.srm.sermalucChallenge.dto.entities.LogUserEntity;
import com.srm.sermalucChallenge.dto.entities.UserEntity;
import com.srm.sermalucChallenge.dto.request.UserRequest;
import com.srm.sermalucChallenge.dto.response.OnSuccessUserResgister;
import com.srm.sermalucChallenge.repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.validation.Valid;

/**
 * Sermaluc Challenge
 * 
 * Contains logic for user's
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class UserService {
	
	private static final Logger LOG = LogManager.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	LogUserService logUserService;
	
	  private MessageSource messageSource = null;

	  @Autowired
	  public void RestI18nController(MessageSource messageSource) {
	    this.messageSource = messageSource;
	  }

	/**
	 * save new user From userRequest
	 * 
	 * @param userRequest
	 * @return OnsuccesUserRegister || Exception
	 */
	public OnSuccessUserResgister registerUser(@Valid UserRequest userRequest) throws CustomDataConstraintException {

		UserEntity UserEntity = userRequest.factoryUserEntity();

		if (userEntityExist(UserEntity)) {
			
			
			@SuppressWarnings("null")
			String message = messageSource.getMessage("error.user.exist", new Object[]{}, null);
			
			throw new CustomDataConstraintException(message,null,null);
		}

		UserEntity saveUserEntity = saveUserEntity(UserEntity);

		LogUserEntity logUserEntity = logUserService.registrerUserLog(saveUserEntity);

		return logUserEntity.factoryOnSuccessUserResgister();

	}

	/**
	 * @param userEntity
	 */
	private boolean userEntityExist(UserEntity userEntity) {

		return !userRepository.findByEmail(userEntity.getEmail()).isEmpty();
			
		
	}

	/**
	 * Save User Entity
	 * 
	 * @param userEntity
	 * @return
	 */
	private UserEntity saveUserEntity(@Valid UserEntity userEntity) {

		UserEntity save = null;
		if (userEntity != null) {
			try {
				save = userRepository.save(userEntity);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		return save;

	}

}
