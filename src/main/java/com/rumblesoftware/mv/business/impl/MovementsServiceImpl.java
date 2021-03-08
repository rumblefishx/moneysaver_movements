package com.rumblesoftware.mv.business.impl;

import static com.rumblesoftware.mv.business.validations.RecurrentMovValidator.isRecurrentMvInTheSameMonth;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.business.validations.CategoryExistanceValidator;
import com.rumblesoftware.mv.business.validations.MovementExistanceValidator;
import com.rumblesoftware.mv.business.validations.UserExistanceValidator;
import com.rumblesoftware.mv.exception.DataNotFoundException;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.PersistenceInternalFailureException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.CandidateToValidationData;
import com.rumblesoftware.mv.io.IOConverter;
import com.rumblesoftware.mv.io.MovementType;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;
import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.repository.MovementsRepository;
import com.rumblesoftware.mv.repository.RecurrentMovementRepository;
import com.rumblesoftware.mv.utils.DateUtils;

@Service
public class MovementsServiceImpl implements MovemenstService{

	@Autowired
	private IOConverter converter;
	
	@Autowired
	private MovementsRepository repository;
	
	@Autowired
	private RecurrentMovementRepository rmRepository;
	
	@Autowired
	private CategoryExistanceValidator catVal;
	
	@Autowired
	private UserExistanceValidator custVal;
	
	@Autowired
	private MovementExistanceValidator movVal;
	
	private Logger log = LogManager.getLogger(MovementsServiceImpl.class);
	
	@Autowired
	private DateUtils dtUtils;
	
	@Override
	public MovementOutputDTO addMovement(MovementInputDTO input) {		
		
		log.debug("[Service Layer] - addMovement - starting validations...");
		
		isRecurrentMvInTheSameMonth(input.getRecurrentSt(),dtUtils.castStringToDate(input.getmDate()));
		
		custVal.setNextVal(catVal);
		custVal.validate(new CandidateToValidationData(input.getCustomerId(),input.getCategoryId()));
		
		log.debug("[Service Layer] - addMovement - validation process finished!");
		
		MovementEntity entity = converter.castToEntity(input);		
		
		log.debug("[Service Layer] - addMovement - saving data in the database...");
		try {
			PersistMovement(entity,input.getRecurrentSt() == 1);
		} catch(Throwable e) {
			log.error("[Service Layer] - addMovement - failure during persistence : " + e.getMessage());
			throw new PersistenceInternalFailureException();
		}
		
		return converter.castToMovementOutputDTO(entity);
	}

	@Override
	public MovementOutputDTO updMovement(MovementPatchDTO input) throws InternalValidationErrorException, ValidationException {
		
		log.debug("[Service Layer] - updMovement - starting validations...");
		
		catVal.setNextVal(movVal);
		custVal.setNextVal(catVal);
		custVal.validate(new CandidateToValidationData(input.getCustomerId(),input.getCategoryId()));
		
		log.debug("[Service Layer] - updMovement - validation process finished!");
		
		MovementEntity entity =	
				repository.findEntityByCatAndCustIds(
						input.getCategoryId(), 
						input.getCustomerId(), 
						input.getMovementId()).get();
		
		entity = converter.transferDataToUpdate(entity, input);
		
		log.debug("[Service Layer] - updMovement - saving data in the database...");
		
		try {
			PersistMovement(entity,input.getRecurrentSt() == 1);
		} catch(Exception e) {
			log.error("[Service Layer] - updMovement - failure during persistence : " + e.getMessage());
			throw new PersistenceInternalFailureException();
		}
		
		return converter.castToMovementOutputDTO(entity);
	}



	@Override
	public MovementOutputDTO delMovement(Long customerId, Long categoryId, Long MovementId)
			throws InternalValidationErrorException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO findMovement(Long customerId, Long categoryId, Long MovementId) {
		
		log.debug("[Service Layer] - Searching data...");
		
		Optional<MovementEntity> result = 
				repository.findEntityByCatAndCustIds(categoryId, customerId, MovementId);
		
		if(result.isEmpty()) {
			log.debug("[Service Layer] - Data hasnt been found");
			throw new DataNotFoundException();
		}
		
		return converter.castToMovementOutputDTO(result.get());
	}

	@Override
	public MovementOutputDTO MovementsFromCustomer(Long customerId, Long categoryId, Long MovementId, MovementType type,
			Date dtInit, Date dtEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(rollbackFor = {Throwable.class})
	private void PersistMovement(MovementEntity entity,boolean isRecurrent) {
		try {
			repository.save(entity);
		
			if(isRecurrent) {
				RecurrentMovEntity rm = converter.MovEntityToRecurrent(entity);
				rmRepository.save(rm);
			}
			
		} catch(Exception e) {
			log.error("[Service Layer] - PersistMovement - failure during persistence : " + e.getMessage());
			throw new PersistenceInternalFailureException();
		}
	}

}
