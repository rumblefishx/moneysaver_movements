package com.rumblesoftware.mv.business.validations;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.exception.PersistenceInternalFailureException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.CandidateToValidationData;
import com.rumblesoftware.mv.io.MovementType;
import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.repository.RecurrentMovementRepository;

@Component
public class RecurrentMovValidator {
	
	private static final String DFT_MSG = "movement.rec.invalid.month";
	private static final String REC_MV_PROPER_MV_TYPE_MSG = "movement.rec.wrong.mv.type";
	
	@Autowired
	private RecurrentMovementRepository repository;
	
	private static Logger log = LogManager.getLogger(RecurrentMovValidator.class);
	
	public static void isRecurrentMvInTheSameMonth(Integer recurrentCode,Date mvDate) {
			
		log.debug("[Validation Layer] - Checking date of recurrent movement...");
		
		if(recurrentCode == 0)
			return;		
		
		Calendar presentTime = Calendar.getInstance();
		Calendar dateParameter = Calendar.getInstance();
		
		presentTime.setTime(new Date());
		dateParameter.setTime(mvDate);
		
		if(dateParameter.get(YEAR) == presentTime.get(YEAR))
			if(dateParameter.get(MONTH) == presentTime.get(MONTH))
				return;
		
		throw new ValidationException(DFT_MSG);
	} 
	
	public static void isRecurrentAnOutcomeMovement(Integer recurrentCode, Integer movementType) {
		
		MovementType movType = MovementType.getMvTypeByCode(movementType);
		
		if(recurrentCode == 1
		&& movType != MovementType.OUTCOME)
			throw new ValidationException(REC_MV_PROPER_MV_TYPE_MSG);
	}
	
	public boolean wasMovementRecurrent(CandidateToValidationData data) {
		
		log.debug("[Validation Layer] - Checking if movement was recurrent...");
		Optional<RecurrentMovEntity> rec  = null;
		
		try {
			rec = repository.findByLogicalIds(
							data.getCategoryId(), 
							data.getCustomerId(), 
							data.getMovementId());
			
		} catch(Exception e) {
			log.error("[Validation Layer] - Failure during recurrent movement validation");
			throw new PersistenceInternalFailureException();
		}
		
		return rec.isPresent();
	}

}
