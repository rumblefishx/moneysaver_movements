package com.rumblesoftware.mv.io.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoneyInStringValidation implements ConstraintValidator<MoneyInStringVal, String>{

	private Integer decimals = 0;

	public void initialize(MoneyInStringVal annotation) {
		this.decimals = annotation.decimals();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		BigDecimal val = null;
		
		try {
			val = new BigDecimal(value.trim());
		} catch(Exception e) {
			//NOTHING
		} finally {
			if(val == null)
				return false;
			
			BigDecimal fractPart = val.remainder(BigDecimal.ONE);
			StringBuilder sb = new StringBuilder(fractPart.toString());
			sb.delete(0, 2);
			
			if(sb.toString().length() > decimals)
				return false;
		}
		
	
		return true;
	}



}
