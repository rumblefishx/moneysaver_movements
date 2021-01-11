package com.rumblesoftware.mv.exception;

/**
 * Exception thrown when a date conversion goes wrong
 * @author Cleiton
 *
 */
public class DateConversionException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5900017879316909336L;

	public DateConversionException(String message) {
		super(message);
	}
}
