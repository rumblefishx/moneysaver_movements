package com.rumblesoftware.mv.business.validations;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.exception.EditableMovementException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.MovementType;
import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.repository.RecurrentMovementRepository;

@Component
public class RecurrentMovValidator {
	
	private static final String DFT_MSG = "movement.rec.invalid.month";
	private static final String REC_MV_PROPER_MV_TYPE_MSG = "movement.rec.wrong.mv.type";
	
	
	private static Logger log = LogManager.getLogger(RecurrentMovValidator.class);
	
	public static void NewRecurrentMvInTheSameMonthCheck(Integer recurrentCode,Date mvDate) {
			
		log.debug("[Validation Layer] - Checking date of recurrent movement...");
		
		if(recurrentCode == null || recurrentCode == 0)
			return;		
		
		if(isSameYearAndMonth(new Date(),mvDate) == false)		
			throw new ValidationException(DFT_MSG);
	} 
	
	public static void RecurrentMvnUpdatableCheck(RecurrentMovEntity entity) {		
		if(isSameYearAndMonth(new Date(), entity.getMovementDate()) == false) {
			throw new EditableMovementException();
		}		
	}
	
	public static void isRecurrentAnOutcomeMovement(Integer recurrentCode, Integer movementType) {
		
		MovementType movType = MovementType.getMvTypeByCode(movementType);
		
		if(recurrentCode != null && recurrentCode == 1
		&& movType != MovementType.OUTCOME)
			throw new ValidationException(REC_MV_PROPER_MV_TYPE_MSG);
	}
	
	private static boolean isSameYearAndMonth(Date dtOne,Date dtTwo) {

		Calendar firstDate = Calendar.getInstance();
		Calendar secondDate = Calendar.getInstance();
		
		firstDate.setTime(dtOne);
		secondDate.setTime(dtTwo);
		
		if(secondDate.get(YEAR) == firstDate.get(YEAR))
			if(secondDate.get(MONTH) == firstDate.get(MONTH))
				return true;
		
		return false;
	}

}
