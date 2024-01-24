package com.srm.sermalucChallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.srm.sermalucChallenge.dto.entities.PhoneEntity;
import com.srm.sermalucChallenge.dto.request.PhoneRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Sermaluc Challenge
 * 
 * For use Factory Patron design in PhoneRequest, PhoneEntity
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Entity
@Table(name = "phones", schema = "usersdb")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ParentPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@JsonIgnore
	private Long id;

	@Schema(description = "Phone number", example = "1234567")
	@NotBlank(message = "{notblank.number}")
	protected String number;
	
	@Schema(description = "City code", example = "1")
	@NotBlank(message = "{notblank.citycode}")
	@Column(name = "city_code")
	@JsonProperty("citycode")
	protected String cityCode;
	
	@Schema(description = "Country code", example = "57")
	@NotBlank(message = "{notblank.contrycode}")
	@Column(name = "country_code")
	@JsonProperty("countrycode")
	protected String countryCode;
	
	public ParentPhone() {
		super();
	}
	
	
	/**
	 * 
	 * @param id
	 * @param number
	 * @param cityCode
	 * @param countryCode
	 */
	public ParentPhone(Long id, @NotBlank(message = "{notblank.number}") String number,
			@NotBlank(message = "{notblank.citycode}") String cityCode,
			@NotBlank(message = "{notblank.contrycode}") String countryCode) {
		super();
		this.id = id;
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}


	
	public ParentPhone(@NotBlank(message = "{notblank.number}") String number,
			@NotBlank(message = "{notblank.citycode}") String cityCode,
			@NotBlank(message = "{notblank.contrycode}") String countryCode) {
		super();
		this.number = number;
		this.cityCode = cityCode;
		this.countryCode = countryCode;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public abstract PhoneRequest factoryPhonesRequest();
	public abstract PhoneEntity factoryPhoneEntity();
}