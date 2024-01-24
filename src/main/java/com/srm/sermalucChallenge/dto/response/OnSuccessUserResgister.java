/**
 * 
 */
package com.srm.sermalucChallenge.dto.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.srm.sermalucChallenge.dto.ParentUserLog;
import com.srm.sermalucChallenge.dto.entities.LogUserEntity;

/**
 * Sermaluc Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@JsonIgnoreProperties({ "logId", "modified" })
public class OnSuccessUserResgister extends ParentUserLog {

	
	
	public OnSuccessUserResgister() {
		super();
		// TODO Auto-generated constructor stub
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
	public OnSuccessUserResgister(String logId, String userId, Timestamp created, Timestamp modified,
			Timestamp lastLogin, String token, Boolean isActive) {
		super(logId, userId, created, modified, lastLogin, token, isActive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LogUserEntity factoryLogUserEntity() {

		LogUserEntity logUserEntity = new LogUserEntity(this.logId, this.userId, this.created, this.modified, this.lastLogin, this.token, this.isActive);
		
		return logUserEntity;
	}

	@Override
	public OnSuccessUserResgister factoryOnSuccessUserResgister() {

		
		return this;
	}

}
