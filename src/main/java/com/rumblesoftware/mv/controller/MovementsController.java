package com.rumblesoftware.mv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.io.IOConverter;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.output.dto.MovementResponse;

@RestController(value="/moneysaver/movements")
public class MovementsController {
	
	@Autowired
	private MovemenstService service;
	
	@Autowired
	private IOConverter converter;

	@PostMapping
	public ResponseEntity<MovementResponse> addNewMovement(@Valid @RequestBody MovementInputDTO mv,BindingResult br){
		
		MovementResponse response = new MovementResponse();
		
		if(br.hasErrors()) {
			br.getAllErrors().forEach(e -> response.addErrorMsg(e.getDefaultMessage()));
			response.setResponse(converter.castToMovementOutputDTO(mv));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setResponse(service.addMovement(mv));
		
		return ResponseEntity.ok(response);
		
	}
}
