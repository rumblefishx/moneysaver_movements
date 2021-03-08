package com.rumblesoftware.mv.model;

import java.io.Serializable;

public class RecurrentMovID implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 6832716309942234377L;

private Long customerId;
	
	private Long categoryId;
	
	private Long movementId;
	
	public RecurrentMovID() {}

	public RecurrentMovID(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public RecurrentMovID(Long customerId, Long movementId,Long categoryId) {
		super();
		this.customerId = customerId;
		this.movementId = movementId;
		this.categoryId = categoryId;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}
