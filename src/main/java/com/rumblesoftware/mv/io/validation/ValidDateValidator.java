package com.rumblesoftware.mv.io.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String>  {
	
	private static final String VALID_DATE_REGEX = "((\\b(0[1-9]|[12][0-9]|3[0-1])\\b\\/\\b(0[1|3|5|7|8]|1[0|2])\\b\\/\\d{4})|(\\b(0[1-9]|1[0-9]|2[0-8])\\b\\/02\\/\\d{4})|(\\b(0[1-9]|[12][0-9]|3[0])\\b\\/\\b(0[4|6|9]|1[0|2])\\b\\/\\d{4}))";

	//(\b(0[1-9]|[12][0-9]|3[0-1])\b\/\b(0[1|3|5|7|8]|1[0|2])\b\/\d{4}) - 31 Days months
	//(\b(0[1-9]|1[0-9]|2[0-8])\b\/02\/\d{4}) --- February
	//(\b(0[1-9]|[12][0-9]|3[0])\b\/\b(0[4|6|9]|1[0|2])\b\/\d{4}) - 30 Days months

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value != null) {
			if(value.trim().matches(VALID_DATE_REGEX) == false)
				return false;
			}
		
		return true;
	}

}
