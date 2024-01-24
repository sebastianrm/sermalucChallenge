/**
 * 
 */
package com.srm.sermalucChallenge.Exceptions;

import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;


/**
 * Sermaluc Challenge
 * 
 * @author Sebastian Eduardo Retamal Morales
 * @since 2024-01-24
 * @version 0.0.1-SNAPSHOT
 */
public class CustomDataConstraintException extends ConstraintViolationException {

	private static final long serialVersionUID = 1L;

	public CustomDataConstraintException(String message, SQLException root, String sql, String constraintName) {
		super(message, root, sql, constraintName);
	}

	public CustomDataConstraintException(String message, SQLException root, String constraintName) {
		super(message, root, constraintName);
	}

}