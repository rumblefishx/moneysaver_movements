package com.rumblesoftware.mv.exception;

public class CategoryNotFoundException extends ValidationException {
	
	private static final String DFT_MSG = "ms.categories.id.notfound";

	/**
	 * 
	 */
	private static final long serialVersionUID = 4479313767123265103L;

	public CategoryNotFoundException() {
		super(DFT_MSG);
	}
}
