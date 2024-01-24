/**
 * 
 */
package com.srm.sermalucChallenge.Exceptions;

/**
 * Sermaluc Challenge
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
public class CustomNoValidPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomNoValidPasswordException(String message) {
		super(message);
	}
	

}
