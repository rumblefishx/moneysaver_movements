package com.rumblesoftware.mv.io.input.dto;

import java.io.Serializable;

import javax.validation.Valid;

import com.rumblesoftware.mv.io.validation.MoneyInStringVal;

@Valid
public class MovementInputDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long customerId;

	@MoneyInStringVal(decimals = 2)
	private String mAmount;
	
	private String mDate;
	
	private int recurrentSt;
	
	/**
	 * Movement Type
	 * 0 : Income
	 * 1 : Outcome
	 */
	private int mType;
	
	private String mDescription;
	
	/*
	 * TODO: Must create a microservice to manage categories
	 * Now using two "default categories"
	 *   0 : Salary
	 *   1 : Credit Card
	 */
	private Long categoryId;

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

	public int getRecurrentSt() {
		return recurrentSt;
	}

	public void setRecurrentSt(int recurrentSt) {
		this.recurrentSt = recurrentSt;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	
}
