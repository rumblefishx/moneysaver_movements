package com.rumblesoftware.mv.io.input.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.mv.io.validation.MoneyInStringVal;
import com.rumblesoftware.mv.io.validation.ValidDate;

@Valid
public class MovementInputDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long customerId;

	@MoneyInStringVal(decimals = 2)
	@NotBlank(message="{movement.input.amount.empty}")
	private String mAmount;
	
	@ValidDate
	@NotBlank(message="{movement.input.date.empty}")
	private String mDate;
	
	@Max(value = 1,message="{movement.input.recurrent.invalid.value}")
	@Min(value = 0,message="{movement.input.recurrent.invalid.value}")
	private int recurrentSt;
	
	/**
	 * Movement Type
	 * 0 : Income
	 * 1 : Outcome
	 */
	@Max(value = 1,message="{movement.input.mType.invalid.value}")
	@Min(value = 0,message="{movement.input.mType.invalid.value}")
	private int mType;
	
	@Length(min = 15,max=120,message="{movement.input.description.range}")
	@NotBlank(message="{movement.input.description.empty}")
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
