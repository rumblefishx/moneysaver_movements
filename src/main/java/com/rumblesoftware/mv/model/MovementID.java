package com.rumblesoftware.mv.model;

import java.io.Serializable;

import javax.persistence.Column;

public class MovementID implements Serializable{

	private static final long serialVersionUID = 2320019713707945894L;

	private Long customerId;
	
	private Long movementId;
	
	public MovementID() {}

	public MovementID(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public MovementID(Long customerId, Long movementId,Long categoryId) {
		super();
		this.customerId = customerId;
		this.movementId = movementId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}
	
}
