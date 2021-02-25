package com.rumblesoftware.mv.exception;

public class MovementNotFoundException extends ValidationException {
	
	private static final String DFT_MSG = "ms.movements.mv.notfound";

	/**
	 * 
	 */
	private static final long serialVersionUID = 4457178338591858709L;

	public MovementNotFoundException() {
		super(DFT_MSG);
	}
}
