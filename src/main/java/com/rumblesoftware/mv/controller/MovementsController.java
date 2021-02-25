package com.rumblesoftware.mv.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.IOConverter;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.output.dto.MovementOutputDTO;
import com.rumblesoftware.mv.io.output.dto.MovementResponse;
import com.rumblesoftware.mv.utils.PostOfficer;

@RestController
public class MovementsController {
	
	@Autowired
	private MovemenstService service;
	
	@Autowired
	private IOConverter converter;
	
	@Autowired
	private PostOfficer po;
	
	private Logger log = LogManager.getLogger(MovementsController.class);

	@PostMapping(value = "/movements")
	public ResponseEntity<MovementResponse> addNewMovement(@Valid @RequestBody MovementInputDTO mv,BindingResult br){
		
		log.debug("[Controller Layer] - add movement endpoint - receiving request...");
		
		MovementResponse response = new MovementResponse();
		
		if(br.hasErrors()) {
			log.debug("[Controller Layer] - add movement endpoint - delivering input validation errors...");
			br.getAllErrors().forEach(e -> response.addErrorMsg(e.getDefaultMessage()));
			response.setResponse(converter.castToMovementOutputDTO(mv));
			return ResponseEntity.badRequest().body(response);
		}
		
		log.debug("[Controller Layer] - add movement endpoint - calling service layer");
		
		
		try {
			response.setResponse(service.addMovement(mv));
		} catch(ValidationException|InternalValidationErrorException e) {
			log.info("[Controller Layer] (add movement phase) returning an exception");
			e.printStackTrace();				
			response.setResponse(converter.castToMovementOutputDTO(mv));
			response.addErrorMsg(po.getMessage(e.getMessage()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		
		log.debug("[Controller Layer] - add movement endpoint - return result");
		
		return ResponseEntity.ok(response);
		
	}
}
