package com.rumblesoftware.mv.exception;

public class RecMovOutOfTimeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 674654079862212466L;
	public static final String DFT_MSG = "movement.rec.invalid.month";
	
	public RecMovOutOfTimeException() {
		super(DFT_MSG);
	}

	
}
