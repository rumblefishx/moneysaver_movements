package com.rumblesoftware.mv.model;

import java.io.Serializable;

public class RecurrentMovID implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 6832716309942234377L;

	private Long customerId;
		
	private Long movementId;
	
	private Long recurrentMovId;
	
	public RecurrentMovID() {}

	public RecurrentMovID(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public RecurrentMovID(Long customerId, Long movementId,Long categoryId,Long recurrentMovId) {
		super();
		this.customerId = customerId;
		this.movementId = movementId;
		this.recurrentMovId = recurrentMovId;
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

	public Long getRecurrentMovId() {
		return recurrentMovId;
	}

	public void setRecurrentMovId(Long recurrentMovId) {
		this.recurrentMovId = recurrentMovId;
	}
	
	
}
