package com.srm.sermalucChallenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srm.sermalucChallenge.dto.entities.UserEntity;
import com.srm.sermalucChallenge.dto.request.UserRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Sermaluc Challenge
 * 
 * For use Factory Patron design in UserRequest, UserEntity
 * 
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "USERS", schema = "usersdb", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public abstract class ParentUser {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@JsonIgnore
	private String id;
	
	@Schema(description = "User mail", example = "juan@dominio.com")
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@dominio.com*$" ,message = "{pattern.email}")
	@NotBlank(message = "{notblank.email}")
	@Email(message = "{notvalid.email}")
	@Column(unique=true)
	protected String email;

	@Schema(description = "User Name", example = "Juan Rodriguez")
	@NotBlank(message = "{notblank.name}")
	protected String name;

	@Schema(description = "User password", example = "hunter2")
	@NotBlank(message = "{notblank.password}")
	protected String password;

	@Column(columnDefinition = "boolean default true")
	@JsonIgnore
	protected Boolean isActive = true;

	public ParentUser() {
		super();
	}

	/**
	 * 
	 * @param email
	 * @param name
	 * @param password
	 * @param isactive
	 */
	public ParentUser(
			@Email(message = "{notvalid.email}") String email,
			@NotBlank(message = "{notblank.name}") String name,
			@NotBlank(message = "{notblank.password}") String password, Boolean isactive) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.isActive = isactive;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public abstract UserRequest factoryUserRequest();

	public abstract UserEntity factoryUserEntity();

}
