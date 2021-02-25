package com.rumblesoftware.mv.business.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.CandidateToValidationData;
import com.rumblesoftware.mv.io.IOConverter;
import com.rumblesoftware.mv.io.MovementType;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;
import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.repository.MovementsRepository;

@Service
public class MovementsServiceImpl implements MovemenstService{

	@Autowired
	private IOConverter converter;
	
	@Autowired
	private MovementsRepository repository;
	
	@Autowired
	private CategoryExistanceValidator catVal;
	
	@Autowired
	private UserExistanceValidator custVal;
	
	private Logger log = LogManager.getLogger(MovementsServiceImpl.class);
	
	@Override
	public MovementOutputDTO addMovement(MovementInputDTO input) {		
		
		log.debug("[Service Layer] - addMovement - starting validations...");
		
		custVal.setNextVal(catVal);
		custVal.validate(new CandidateToValidationData(input.getCustomerId(),input.getCategoryId()));
		
		log.debug("[Service Layer] - addMovement - validation process finished!");
		
		MovementEntity entity = converter.castToEntity(input);	
		
		
		log.debug("[Service Layer] - addMovement - saving data in the database...");
		entity = repository.save(entity);
		
		return converter.castToMovementOutputDTO(entity);
	}

	@Override
	public MovementOutputDTO updMovement(MovementPatchDTO input) throws InternalValidationErrorException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO findMovement(Long customerId, Long MovementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO MovementsFromCustomer(Long customerId, Long MovementId, MovementType type, Date dtInit,
			Date dtEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO delMovement(Long customerId, Long MovementId) {
		// TODO Auto-generated method stub
		return null;
	}


}
