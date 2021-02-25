package com.rumblesoftware.mv.io;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;
import com.rumblesoftware.mv.model.MovementEntity;
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
	
	public MovementEntity castToEntity(MovementInputDTO input) {
		MovementEntity entity = new MovementEntity();
		entity.setAmount(new BigDecimal(input.getmAmount()));
		entity.setCategoryId(input.getCategoryId());
		entity.setmDescription(input.getmDescription());
		entity.setMovementDate(dutils.castStringToDate(input.getmDate()));
		entity.setCustomerId(input.getCustomerId());
		entity.setMovementType(MovementType.getMvTypeByCode(input.getmType()));
		entity.setRecurrentSt(input.getRecurrentSt());
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
		output.setRecurrentSt(entity.getRecurrentSt());
		return output;
	}
}
