package com.rumblesoftware.mv.io.output.dto;

import java.io.Serializable;

public class MovementOutputDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long movementId;
	
	private Long customerId;

	private String mAmount;
	
	private String mDate;
	
	private Integer recurrentSt;
	
	/**
	 * Movement Type
	 * 0 : Income
	 * 1 : Outcome
	 */
	private Integer mType;
	
	private String mDescription;
	
	/*
	 * TODO: Must create a microservice to manage categories
	 * Now using two "default categories"
	 *   0 : Salary
	 *   1 : Credit Card
	 */
	private Long categoryId;

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getmAmount() {
		return mAmount;
	}

	public void setmAmount(String mValue) {
		this.mAmount = mValue;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public Integer getRecurrentSt() {
		return recurrentSt;
	}

	public void setRecurrentSt(Integer recurrentSt) {
		this.recurrentSt = recurrentSt;
	}

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryCode) {
		this.categoryId = categoryCode;
	}


		
}
