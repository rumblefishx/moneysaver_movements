package com.rumblesoftware.mv.exception;

public class PersistenceInternalFailureException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1385659111566652639L;
	private static final String DFT_MSG = "movement.internal.error.message";
	
	public PersistenceInternalFailureException() {
		super(DFT_MSG);
	}

}
