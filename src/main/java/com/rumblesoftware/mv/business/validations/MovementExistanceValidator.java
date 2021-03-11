package com.rumblesoftware.mv.business.validations;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.MovementNotFoundException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.CandidateToValidationData;
import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.repository.MovementsRepository;

@Component
public class MovementExistanceValidator extends BaseValidator<CandidateToValidationData>{
	
	private Logger log = LogManager.getLogger(MovementExistanceValidator.class);

	@Autowired
	private MovementsRepository repository;
	
	@Override
	public void validate(CandidateToValidationData input) throws InternalValidationErrorException, ValidationException {
		log.debug("[Validation Layer] Checking if the movement really exists...");
		
		Optional<MovementEntity> mov = null;
		
		try {
			mov = 	repository.findEntityByLogicalIds(
							input.getCategoryId(), 
							input.getCustomerId(), 
							input.getMovementId());
		} catch(Exception e) {
			log.error("[Validation Layer] Failure during movement existance validation.");
			throw new InternalValidationErrorException();
		}
		
		if(mov.isEmpty()) {
			log.debug("[Validation Layer] Moevement hasn't been found");
			throw new MovementNotFoundException();
		}
		
		if(nextValidator != null)
			nextValidator.validate(input);
	}

}
