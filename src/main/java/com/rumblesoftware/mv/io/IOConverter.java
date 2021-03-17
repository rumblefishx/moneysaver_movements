package com.rumblesoftware.mv.io;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;
import com.rumblesoftware.mv.model.MovementEntity;
import com.rumblesoftware.mv.model.RecurrentMovEntity;
import com.rumblesoftware.mv.utils.DateUtils;

@Component
public class IOConverter {
	
	@Autowired
	private DateUtils dutils;

	public MovementOutputDTO castToMovementOutputDTO(MovementInputDTO input) {
		MovementOutputDTO output = new MovementOutputDTO();
		
		output.setCategoryId(input.getCategoryId());
		output.setCustomerId(input.getCustomerId());
		output.setmDate(input.getmDate());
		output.setmDescription(input.getmDescription());
		output.setmType(input.getmType());
		output.setmAmount(input.getmAmount());
		output.setRecurrentSt(input.getRecurrentSt());
		
		return output;
	}
	
	public MovementOutputDTO castToMovementOutputDTO(MovementPatchDTO input) {
		MovementOutputDTO output = new MovementOutputDTO();
		
		output.setCategoryId(input.getCategoryId());
		output.setCustomerId(input.getCustomerId());
		output.setmDate(input.getmDate());
		output.setmDescription(input.getmDescription());
		output.setmType(input.getmType());
		output.setmAmount(input.getmAmount());
		output.setRecurrentSt(input.getRecurrentSt());
		
		return output;
	}
	
	public MovementEntity castToEntity(MovementInputDTO input) {
		MovementEntity entity = new MovementEntity();
		entity.setAmount(new BigDecimal(input.getmAmount()));
		entity.setCategoryId(input.getCategoryId());
		entity.setmDescription(input.getmDescription());
		entity.setMovementDate(dutils.castStringToDate(input.getmDate()));
		entity.setCustomerId(input.getCustomerId());
		entity.setMovementType(MovementType.getMvTypeByCode(input.getmType()));
		return entity;
	}
	
	public MovementOutputDTO castToMovementOutputDTO(MovementEntity entity) {
		MovementOutputDTO output = new MovementOutputDTO();
		output.setCategoryId(entity.getCategoryId());
		output.setCustomerId(entity.getCustomerId());
		output.setmDate(dutils.castDateToString(entity.getMovementDate()));
		output.setmDescription(entity.getmDescription());
		output.setMovementId(entity.getMovementId());
		output.setmType(entity.getMovementType().getCode());
		output.setmAmount(entity.getAmount().toString());
		return output;
	}
	
	public MovementOutputDTO castToMovementOutputDTO(MovementEntity entity,Integer statusRecCode) {
		MovementOutputDTO output = castToMovementOutputDTO(entity);
		output.setRecurrentSt(statusRecCode);
		return output;
	}
	
	public MovementEntity transferDataToUpdate(MovementEntity target, MovementPatchDTO input) {
						
		if(input.getmAmount() != null )
			target.setAmount(new BigDecimal(input.getmAmount()));		
		
		if(input.getmDescription() != null && input.getmDescription().trim().length() > 0)
			target.setmDescription(input.getmDescription());
		
		if(input.getmDate() != null)
			target.setMovementDate(dutils.castStringToDate(input.getmDate()));		
		
		if(input.getmType()  != null)
			target.setMovementType(MovementType.getMvTypeByCode(input.getmType()));
		
		return target;
	}
	
	public RecurrentMovEntity MovEntityToRecurrent(MovementEntity entity) {
		
		RecurrentMovEntity rm = new RecurrentMovEntity();
		
		rm.setAmount(entity.getAmount());
		rm.setCategoryId(entity.getCategoryId());
		rm.setCustomerId(entity.getCustomerId());
		rm.setmDescription(entity.getmDescription());
		rm.setMovementDate(entity.getMovementDate());
		rm.setMovementType(entity.getMovementType());
		rm.setActivityStatus(1);
		
		return rm;
	}
	
	public RecurrentMovEntity updateRecurrentMovEntity(
			RecurrentMovEntity destination,
			MovementPatchDTO source) {
		
		if(source.getmAmount() != null && source.getmAmount().trim().length() > 0) {
			destination.setAmount(new BigDecimal(source.getmAmount()));
		}

		if(source.getmDescription() != null && source.getmDescription().trim().length() > 0) 
			destination.setmDescription(source.getmDescription());
		
		if(source.getmDate() != null) 
			destination.setMovementDate(dutils.castStringToDate(source.getmDate()));
		
		if(source.getmType() != null)			
			destination.setMovementType(MovementType.getMvTypeByCode(source.getmType()));

		if(source.getCategoryId() != null)
			destination.setCategoryId(source.getCategoryId());
		
		destination.setCustomerId(source.getCustomerId());
		
		if(source.getRecurrentSt() != null) {
			destination.setActivityStatus(source.getRecurrentSt());
		}
		
		return destination;
	}
	
	
}
