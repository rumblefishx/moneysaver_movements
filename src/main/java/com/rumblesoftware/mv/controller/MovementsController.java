package com.rumblesoftware.mv.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rumblesoftware.mv.business.MovemenstService;
import com.rumblesoftware.mv.exception.DataNotFoundException;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.PersistenceInternalFailureException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.IOConverter;
import com.rumblesoftware.mv.io.input.dto.MovementInputDTO;
import com.rumblesoftware.mv.io.input.dto.MovementPatchDTO;
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
	
	private static final String APP_ERR_PREFIX_CODE = "movement.";
	
	private static final String GENERIC_INT_ERR_MSG = "movement.internal.error.message";
	

	@PostMapping(value = "/movement")
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
		} catch(ValidationException exception) {
			return getResponseAfterFailure(converter.castToMovementOutputDTO(mv),exception,HttpStatus.BAD_REQUEST);
		} catch(PersistenceInternalFailureException|InternalValidationErrorException exception) {
			return getResponseAfterFailure(converter.castToMovementOutputDTO(mv),exception,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		log.debug("[Controller Layer] - add movement endpoint - return result");
		
		return ResponseEntity.ok(response);
		
	}
	
	@RequestMapping(value = "/movement",method=RequestMethod.PATCH)
	public ResponseEntity<MovementResponse> updMovement(@Valid @RequestBody MovementPatchDTO mv,BindingResult br){
		
		log.debug("[Controller Layer] - upd movement endpoint - receiving request...");
		
		MovementResponse response = new MovementResponse();
		
		if(br.hasErrors()) {
			log.debug("[Controller Layer] - upd movement endpoint - delivering input validation errors...");
			br.getAllErrors().forEach(e -> response.addErrorMsg(e.getDefaultMessage()));
			response.setResponse(converter.castToMovementOutputDTO(mv));
			return ResponseEntity.badRequest().body(response);
		}
		
		log.debug("[Controller Layer] - upd movement endpoint - calling service layer");
		
		
		try {			
			response.setResponse(service.updMovement(mv));
		} catch(ValidationException exception) {
			return getResponseAfterFailure(converter.castToMovementOutputDTO(mv),exception,HttpStatus.BAD_REQUEST);
		} catch(PersistenceInternalFailureException|InternalValidationErrorException exception) {
			return getResponseAfterFailure(converter.castToMovementOutputDTO(mv),exception,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		log.debug("[Controller Layer] - upd movement endpoint - return result");
		
		return ResponseEntity.ok(response);
		
	}
	
	@RequestMapping(value = "/movement/{movementId}/customer/{customerId}"
			,method=RequestMethod.GET)
	public ResponseEntity<MovementResponse> findMovByIdKeys(
			@PathVariable Long customerId,
			@PathVariable Long movementId){
		
		MovementResponse response = new MovementResponse();
		MovementOutputDTO output = null;
		
		try {
			output = service.findMovement(customerId,movementId);
		} catch(DataNotFoundException e) {
			response.addErrorMsg(e.getMessage());
			response.setResponse(new MovementOutputDTO());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		response.setResponse(output);
		
		return ResponseEntity.ok(response);
	}

	private ResponseEntity<MovementResponse> getResponseAfterFailure(
			MovementOutputDTO output, 
			Exception exception,
			HttpStatus httpStatus) {
		
		MovementResponse response = new MovementResponse();
		
		String msgCode = GENERIC_INT_ERR_MSG;
		
		if(exception.getMessage().contains(APP_ERR_PREFIX_CODE)) 
			msgCode = exception.getMessage();

		if(httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR))
			exception.printStackTrace();	
		
		response.setResponse(output);
		response.addErrorMsg(po.getMessage(msgCode));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	

}
