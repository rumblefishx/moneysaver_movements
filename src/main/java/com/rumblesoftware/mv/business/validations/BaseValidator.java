package com.rumblesoftware.mv.business.validations;

import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.ValidationException;

@Component
public abstract class BaseValidator<O> {

	protected BaseValidator<O> nextValidator;
	
	public void setNextVal(BaseValidator<O> val) {
		this.nextValidator = val;
	}
	
	public abstract void validate(O input) throws InternalValidationErrorException, ValidationException;
}
