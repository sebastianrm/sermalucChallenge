package com.srm.sermalucChallenge.dto.response;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Sermaluc Challenge
 * 
 * OnError Response
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@Schema(description = "On Error messaje Object")
public class ErrorResponse {
	
	@Schema(description = "on Error messaje")
	private String message;
	
	@Schema(description = "on Error messaje")
	private Map<String, String> errors;
	
	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	/**
	 * 
	 * @param message
	 * @param errors
	 */
	public ErrorResponse(String message, Map<String, String> errors) {
		super();
		this.message = message;
		this.errors = errors;
	}


	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
