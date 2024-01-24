package com.srm.sermalucChallenge.dto.entities;

import java.util.Collection;

import com.srm.sermalucChallenge.dto.ParentPhone;
import com.srm.sermalucChallenge.dto.request.PhoneRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Sermaluc Challenge
 * 
 * Represent one phone in data base raltioned with many users
 * 
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Entity
public class PhoneEntity extends ParentPhone {

	@ManyToMany(mappedBy = "phoneEntities")
	private Collection<UserEntity> userEntities;

	
	public PhoneEntity() {
		super();
	}

	/**
	 * 
	 * @param number
	 * @param cityCode
	 * @param countryCode
	 */
	public PhoneEntity(@NotBlank(message = "{notblank.number}") String number,
			@NotBlank(message = "{notblank.citycode}") String cityCode,
			@NotBlank(message = "{notblank.contrycode}") String countryCode) {
		super(number, cityCode, countryCode);
	}

	public Collection<UserEntity> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Collection<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}

	@Override
	public PhoneRequest factoryPhonesRequest() {
		
		PhoneRequest phoneRequest = new PhoneRequest(number, cityCode, countryCode);
		
		return phoneRequest;
	}

	@Override
	public PhoneEntity factoryPhoneEntity() {
		return this;
	}

}
