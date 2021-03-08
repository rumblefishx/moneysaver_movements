package com.rumblesoftware.mv.business.validations;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

import com.rumblesoftware.mv.exception.RecMovOutOfTimeException;

public class RecurrentMovValidator {
	
	public static void isRecurrentMvInTheSameMonth(Integer recurrentCode,Date mvDate) {
		
		if(recurrentCode == 0)
			return;		
		
		Calendar presentTime = Calendar.getInstance();
		Calendar dateParameter = Calendar.getInstance();
		
		presentTime.setTime(new Date());
		dateParameter.setTime(mvDate);
		
		if(dateParameter.get(YEAR) == presentTime.get(YEAR))
			if(dateParameter.get(MONTH) == presentTime.get(MONTH))
				return;
		
		throw new RecMovOutOfTimeException();
	} 

}
