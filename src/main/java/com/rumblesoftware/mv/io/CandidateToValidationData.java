package com.rumblesoftware.mv.io;

public class CandidateToValidationData {
	
	public CandidateToValidationData() {}
	public CandidateToValidationData(Long customerId,Long categoryId) {
		this.categoryId = categoryId;
		this.customerId = customerId;
	}
	
	public CandidateToValidationData(Long customerId,Long categoryId,Long movementId) {
		this.categoryId = categoryId;
		this.customerId = customerId;
		this.movementId = movementId;
	}
	
	private Long customerId;
	private Long categoryId;
	private Long movementId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getMovementId() {
		return movementId;
	}
	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}
	
	

}
