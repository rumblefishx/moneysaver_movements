package com.rumblesoftware.mv.business.impl;

import static com.rumblesoftware.mv.business.validations.RecurrentMovValidator.NewRecurrentMvInTheSameMonthCheck;
import static com.rumblesoftware.mv.business.validations.RecurrentMovValidator.RecurrentMvnUpdatableCheck;
import static com.rumblesoftware.mv.business.validations.RecurrentMovValidator.isRecurrentAnOutcomeMovement;

import java.awt.print.Printable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.business.validations.CategoryExistanceValidator;
import com.rumblesoftware.mv.business.validations.UserExistanceValidator;
import com.rumblesoftware.mv.exception.DataNotFoundException;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.MovementNotFoundException;
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
	private EntityManager em;
	
	private Logger log = LogManager.getLogger(MovementsServiceImpl.class);
	
	@Autowired
	private DateUtils dtUtils;
	
	private static final String MSG_REC_CANNOT_BE_DELETED = "movement.recurrent.cannot.be.deleted";
	
	@Override
	public MovementOutputDTO addMovement(MovementInputDTO input) {		
		RecurrentMovEntity recMov = null;
		
		log.debug("[Service Layer] - addMovement - starting validations...");
		
		//  STEP - 1 Execute validations
		NewRecurrentMvInTheSameMonthCheck(input.getRecurrentSt(),dtUtils.castStringToDate(input.getmDate()));		
		isRecurrentAnOutcomeMovement(input.getRecurrentSt(), input.getmType());
		
		custVal.setNextVal(catVal);
		custVal.validate(new CandidateToValidationData(input.getCustomerId(),input.getCategoryId()));
		log.debug("[Service Layer] - addMovement - validation process finished!");
		
		//STEP 2 - Persist Data
		log.debug("[Service Layer] - addMovement - saving data in the database...");
		MovementEntity entity = converter.castToEntity(input);				
		
		if(input.getRecurrentSt() == 1) 
			recMov = converter.MovEntityToRecurrent(entity);
		
		try {
			entity = PersistMovement(entity,recMov);
		} catch(Throwable e) {
			log.error("[Service Layer] - addMovement - failure during persistence : " + e.getMessage());
			throw new PersistenceInternalFailureException();
		}
		
		return converter.castToMovementOutputDTO(entity,input.getRecurrentSt());
	}

	@Override
	public MovementOutputDTO updMovement(MovementPatchDTO input) throws InternalValidationErrorException, ValidationException {
		
		log.debug("[Service Layer] - updMovement - searching movement...");
		
		NewRecurrentMvInTheSameMonthCheck(input.getRecurrentSt(),dtUtils.castStringToDate(input.getmDate()));		
		isRecurrentAnOutcomeMovement(input.getRecurrentSt(), input.getmType());
		
		MovementOutputDTO output = null;
		
		try {
			output = updatePersistenceTransactionalFlow(input);
		} catch(MovementNotFoundException e) {
			throw e;
		} catch(Exception e) {
			log.error(e.getMessage());
			throw new PersistenceInternalFailureException();
		}
		
		return output;
	}

	@Override
	public MovementOutputDTO delMovement(Long customerId,  Long movementID)
			throws InternalValidationErrorException, ValidationException {	
		
		try {
			deleteTransactionalFlow(customerId, movementID);
		} catch(ValidationException e) {
			throw e;
		} catch(Exception e) {
			throw new PersistenceInternalFailureException();
		}
			
		
		return null;
	}

	@Transactional
	private void deleteTransactionalFlow(Long customerId, Long movementID) {
		MovementEntity movToUpdate = null;
		RecurrentMovEntity recMov = null;		
		
		movToUpdate =	
				repository.findEntityByCustAndMovId(customerId,movementID).orElse(null);
			
			if(movToUpdate == null)
				throw new MovementNotFoundException();
				
			if(movToUpdate.getReplicationSource() != null)
				recMov = 
						rmRepository.findByCustAndMovIds(
						movToUpdate.getCustomerId(), 
						movToUpdate.getReplicationSource()).orElse(null);	
			
			if(recMov != null)
				throw new ValidationException(MSG_REC_CANNOT_BE_DELETED);
			
			repository.delete(movToUpdate);
	}
	
	@Transactional(rollbackFor = {Throwable.class})
	private MovementOutputDTO updatePersistenceTransactionalFlow(MovementPatchDTO input) {
		MovementEntity movToUpdate = null;
		RecurrentMovEntity recMov = null;
				
		// STEP 1 - Find movement to be updated
		movToUpdate =	
			repository.findEntityByCustAndMovId(
					input.getCustomerId(), 
					input.getMovementId()).orElse(null);
		
		if(movToUpdate == null)
			throw new MovementNotFoundException();
			
		if(movToUpdate.getReplicationSource() != null)
			recMov = 
					rmRepository.findByCustAndMovIds(
					movToUpdate.getCustomerId(), 
					movToUpdate.getReplicationSource()).orElse(null);				
		
		movToUpdate = converter.transferDataToUpdate(movToUpdate, input);		
				
		if(recMov != null) {
			//Check if the if the movement is updatable
			RecurrentMvnUpdatableCheck(recMov);
			
			recMov = converter.updateRecurrentMovEntity(recMov, input);
		} else if(recMov == null && input.getRecurrentSt() == 1) {
			recMov = converter.MovEntityToRecurrent(movToUpdate);
		}			
		
		movToUpdate = PersistMovement(movToUpdate,recMov);
		
		return converter.castToMovementOutputDTO(movToUpdate,recMov.getActivityStatus());
	}



	@Override
	public MovementOutputDTO findMovement(Long customerId, Long MovementId) {
		
		RecurrentMovEntity recMov = null;
		Integer recurrentSt = 0;
		
		log.debug("[Service Layer] - Searching data...");
		
		Optional<MovementEntity> result = 
				repository.findEntityByCustAndMovId(customerId, MovementId);
		
		if(result.isEmpty()) {
			log.debug("[Service Layer] - Data hasnt been found");
			throw new DataNotFoundException();
		}
		
		if(result.get().getReplicationSource() != null) 
			recMov = 
					rmRepository.findByCustAndMovIds(
						result.get().getCustomerId(), 
						result.get().getReplicationSource()).orElse(null);		
		
		if(recMov != null)
			recurrentSt = 1;
			
		return converter.castToMovementOutputDTO(result.get(),recurrentSt);
	}

	@Override
	public MovementOutputDTO MovementsFromCustomer(Long customerId, Long categoryId, Long MovementId, MovementType type,
			Date dtInit, Date dtEnd) {
		// TODO Auto-generated method stub
		return null;
	}


	@Transactional(rollbackFor = {Throwable.class})
	private MovementEntity PersistMovement(MovementEntity entity,RecurrentMovEntity rm) {
		
		try {
			
			if(rm != null) {
				log.debug("[Service Layer] - PersistMovement - Saving recurrent movement");
				rm = rm.getRecurrentMovId() != null ? em.merge(rm) : rmRepository.save(rm);
				entity.setReplicationSource(rm.getRecurrentMovId());
			}
			
			log.debug("[Service Layer] - PersistMovement - Saving Movement");
			entity = entity.getMovementId() != null ? em.merge(entity) : repository.save(entity);
			
		} catch(Exception e) {
			log.error("[Service Layer] - PersistMovement - failure during persistence : " + e.getMessage());
			throw new PersistenceInternalFailureException();
		}
		
		return entity;
	}
	

}
