package com.rumblesoftware.mv.exception;

public class CustomerNotFound extends ValidationException {

	private static final long serialVersionUID = 1617458568315496123L;
	
	private static final String DEFAULT_ERROR_MESSAGE = "movement.customer.not.found";
	
	public CustomerNotFound() {
		super(DEFAULT_ERROR_MESSAGE);
	}

}
