package com.rumblesoftware.mv.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.io.IOConverter;
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
	
	@Override
	public MovementOutputDTO addMovement(MovementInputDTO input) {		
		MovementEntity entity = converter.castToEntity(input);	
		
		entity = repository.save(entity);
		
		return converter.castToMovementOutputDTO(entity);
	}

	@Override
	public MovementOutputDTO delMovement(Long customerId, Long MovementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO updMovement(MovementPatchDTO input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO findMovement(Long customerId, Long MovementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MovementOutputDTO MovementsFromCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
