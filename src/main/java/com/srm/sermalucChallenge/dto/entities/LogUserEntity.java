package com.srm.sermalucChallenge.dto.entities;

import java.sql.Timestamp;

import com.srm.sermalucChallenge.dto.ParentUserLog;
import com.srm.sermalucChallenge.dto.response.OnSuccessUserResgister;

import jakarta.persistence.Entity;

/**
 * Sermaluc Challenge
 * 
 * Represent the activity for a user in the bank
 * 
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Entity
public class LogUserEntity extends ParentUserLog {

	public LogUserEntity() {
		super();
	}

	/**
	 * 
	 * @param logId
	 * @param userId
	 * @param created
	 * @param modified
	 * @param lastLogin
	 * @param token
	 * @param isActive
	 */
	public LogUserEntity(String logId, String userId, Timestamp created, Timestamp modified, Timestamp lastLogin,
			String token, Boolean isActive) {
		super(logId, userId, created, modified, lastLogin, token, isActive);
	}

	/**
	 * @param generateJwtToken
	 * @param timestamp
	 * @param userEntity
	 */
	public LogUserEntity(String token, Timestamp timestamp, UserEntity userEntity) {

		this.setUserId(userEntity.getId());
		this.setCreated(timestamp);
		this.setModified(null);
		this.setLastLogin(timestamp);
		this.setToken(token);
		this.setIsActive(userEntity.getIsActive());
	}

	@Override
	public LogUserEntity factoryLogUserEntity() {
		return this;
	}

	@Override
	public OnSuccessUserResgister factoryOnSuccessUserResgister() {

		OnSuccessUserResgister onSuccessUserResgister = new OnSuccessUserResgister(this.getLogId(), this.getUserId(),
				this.getCreated(), this.getModified(), this.getLastLogin(), this.getToken(), this.getIsActive());

		return onSuccessUserResgister;
	}

}