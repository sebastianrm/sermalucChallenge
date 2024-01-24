package com.srm.sermalucChallenge.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.srm.sermalucChallenge.dto.ParentUser;
import com.srm.sermalucChallenge.dto.entities.PhoneEntity;
import com.srm.sermalucChallenge.dto.entities.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Sermaluc Challenge user's
 *
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */

public class UserRequest extends ParentUser {

	@Schema(description = "User phones")
	@JsonProperty("phones")
	private List<PhoneRequest> phonesRequest;

	/**
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @param isactive
	 */
	public UserRequest(
			@Email(message = "{notvalid.email}") String email,
			@NotBlank(message = "{notblank.name}") String name,
			@NotBlank(message = "{notblank.password}") String password, 
			Boolean isactive) {
		super(email, name, password, isactive);
	}

	public UserRequest() {
		super();
	}

	public List<PhoneRequest> getPhonesRequest() {
		return phonesRequest;
	}

	public void setPhonesRequest(List<PhoneRequest> phonesRequest) {
		this.phonesRequest = phonesRequest;
	}

	@Override
	public UserRequest factoryUserRequest() {
		return this;
	}

	@Override
	public UserEntity factoryUserEntity() {

		UserEntity userEntity = new UserEntity(this.email, this.name, this.password, this.isActive);

		if (this.phonesRequest != null) {
			userEntity.setPhoneEntities(new ArrayList<PhoneEntity>());
			this.phonesRequest.forEach((phoneRequest) -> {
				userEntity.getPhoneEntities().add(phoneRequest.factoryPhoneEntity());
			});
		}
		return userEntity;
	}

}
