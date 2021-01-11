package com.rumblesoftware.mv.business;

import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;

public interface MovemenstService {
	
	public MovementOutputDTO addMovement(MovementInputDTO input);
	public MovementOutputDTO delMovement(Long customerId,Long MovementId);
	public MovementOutputDTO updMovement(MovementPatchDTO input);
	public MovementOutputDTO findMovement(Long customerId,Long MovementId);
	public MovementOutputDTO MovementsFromCustomer(long customerId);

}
