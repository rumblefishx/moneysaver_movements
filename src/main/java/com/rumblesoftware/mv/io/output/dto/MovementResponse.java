package com.rumblesoftware.mv.io.output.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovementResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MovementResponse() {
		this.errorMsgs = new ArrayList<String>();
	}

	private List<String> errorMsgs;
	private MovementOutputDTO response;
	
	public void addErrorMsg(String message) {
		this.errorMsgs.add(message);
	}

	public MovementOutputDTO getResponse() {
		return response;
	}

	public void setResponse(MovementOutputDTO response) {
		this.response = response;
	}

	
	
}
