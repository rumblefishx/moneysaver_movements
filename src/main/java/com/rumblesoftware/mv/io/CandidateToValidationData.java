package com.rumblesoftware.mv.io;

public class CandidateToValidationData {
	
	public CandidateToValidationData() {}
	public CandidateToValidationData(Long customerId,Long categoryId) {
		this.categoryId = categoryId;
		this.customerId = customerId;
	}
	
	private Long customerId;
	private Long categoryId;
	
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
	
	

}
