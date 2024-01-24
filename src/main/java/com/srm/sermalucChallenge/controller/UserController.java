package com.srm.sermalucChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.sermalucChallenge.Exceptions.CustomNoValidPasswordException;
import com.srm.sermalucChallenge.dto.ParentUserLog;
import com.srm.sermalucChallenge.dto.request.UserRequest;
import com.srm.sermalucChallenge.dto.response.ErrorResponse;
import com.srm.sermalucChallenge.services.UserService;
import com.srm.sermalucChallenge.utils.AESEncryptionDecryptionUtil;
import com.srm.sermalucChallenge.utils.RegExPatterValidatorUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Sermaluc Challenge
 * 
 * Handle the User EndPoint (CRUD)
 * 
 * @author Sebastian Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User API", description = "the user Crud Controller API ")
public class UserController {

	@Autowired
	AESEncryptionDecryptionUtil AesEncryptionDecryptionService;

	@Autowired
	UserService userService;

	@Autowired
	RegExPatterValidatorUtils regExPatterValidatorUtils;

	private MessageSource messageSource = null;

	@Autowired
	public void RestI18nController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Operation(summary = "Register new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successfull", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ParentUserLog.class)) }),
			@ApiResponse(responseCode = "400", description = "BAD REQUEST", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),

			@ApiResponse(responseCode = "409", description = "CONFLICT - the register Exist", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<?> addNewUser(@Valid @RequestBody UserRequest userRequest) {

//		verifica si contraseña cumple con la expresion regular

		if (!regExPatterValidatorUtils.validPasswordPattern(userRequest.getPassword())) {
			
			@SuppressWarnings("null")
			String message = messageSource.getMessage("pattern.password", new Object[]{}, null);
			
			throw new CustomNoValidPasswordException(message);
		}

		/**
		 * Primer paso Encripta Password
		 */
		userRequest = encryptPassword(userRequest);

		return ResponseEntity.ok(userService.registerUser(userRequest));

	}

	/**
	 * Encryt password
	 * 
	 * @param userRequest
	 * @return
	 */
	private UserRequest encryptPassword(UserRequest userRequest) {

		userRequest.setPassword(AesEncryptionDecryptionService.encrypt(userRequest.getPassword()));

		return userRequest;
	}

}
