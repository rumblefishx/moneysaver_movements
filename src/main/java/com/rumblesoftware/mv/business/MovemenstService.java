package com.rumblesoftware.mv.business;

import java.util.Date;

import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.MovementType;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;

public interface MovemenstService {
	
	public MovementOutputDTO addMovement(MovementInputDTO input) throws InternalValidationErrorException, ValidationException;
	public MovementOutputDTO updMovement(MovementPatchDTO input) throws InternalValidationErrorException, ValidationException;
	public MovementOutputDTO delMovement(Long customerId,Long categoryId,Long MovementId) throws InternalValidationErrorException, ValidationException;
	public MovementOutputDTO findMovement(Long customerId,Long categoryId,Long MovementId);
	public MovementOutputDTO MovementsFromCustomer(Long customerId,Long categoryId,Long MovementId, MovementType type, Date dtInit, Date dtEnd);

}
