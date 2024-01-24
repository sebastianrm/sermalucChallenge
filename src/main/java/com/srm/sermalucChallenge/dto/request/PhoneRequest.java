package com.srm.sermalucChallenge.dto.request;

import com.srm.sermalucChallenge.dto.ParentPhone;
import com.srm.sermalucChallenge.dto.entities.PhoneEntity;

import jakarta.validation.constraints.NotBlank;

/**
 * Sermaluc Challenge
 * 
 * Represents Phone number from request
 * 
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 * 
 */
public class PhoneRequest extends ParentPhone {

	public PhoneRequest() {
		super();
	}

	public PhoneRequest(@NotBlank(message = "{notblank.number}") String number,
			@NotBlank(message = "{notblank.citycode}") String citycode,
			@NotBlank(message = "{notblank.contrycode}") String countryCode) {
		super(number, citycode, countryCode);
	}

	@Override
	public PhoneRequest factoryPhonesRequest() {

		return this;
	}

	@Override
	public PhoneEntity factoryPhoneEntity() {

		return new PhoneEntity(number, cityCode, countryCode);

	}

}
