package com.rumblesoftware.mv.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664112088454623493L;
	private static final String DFT_ERROR_MSG = "movement.data.not.found.msg";
	
	public DataNotFoundException() {
		super(DFT_ERROR_MSG);
	}
}
