package com.rumblesoftware.mv.exception;

public class InternalValidationErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641747536333521072L;

	private static final String DEFAULT_ERROR_MSG_CODE = "movement.internal.error.message";
	
	public InternalValidationErrorException() {
		super(DEFAULT_ERROR_MSG_CODE);
	}
}
