package com.rumblesoftware.mv.exception;

public class EditableMovementException extends ValidationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4154835570729506074L;
	private static final String DFT_MSG = "movement.update.not.allowed";

	public EditableMovementException() {
		super(DFT_MSG);
	}
}
