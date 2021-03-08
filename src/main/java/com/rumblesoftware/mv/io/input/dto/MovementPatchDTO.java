package com.rumblesoftware.mv.io.input.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.rumblesoftware.mv.io.validation.MoneyInStringVal;
import com.rumblesoftware.mv.io.validation.ValidDate;

public class MovementPatchDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="{movement.input.customer.id.null}")
	private Long customerId;
	
	@NotNull(message="{movement.input.category.id.null}")
	private Long categoryId;
	
	@NotNull(message="{movement.input.movement.id.null}")
	private Long movementId;

	@MoneyInStringVal(decimals = 2)
	@NotBlank(message="{movement.input.amount.empty}")
	private String mAmount;
	
	@ValidDate
	@NotBlank(message="{movement.input.date.empty}")
	private String mDate;
	
	@Max(value = 1,message="{movement.input.recurrent.invalid.value}")
	@Min(value = 0,message="{movement.input.recurrent.invalid.value}")
	private Integer recurrentSt;
	
	/**
	 * Movement Type
	 * 0 : Income
	 * 1 : Outcome
	 */
	@Max(value = 1,message="{movement.input.mType.invalid.value}")
	@Min(value = 0,message="{movement.input.mType.invalid.value}")
	private Integer mType;
	
	@Length(min = 5,max=120,message="{movement.input.description.range}")
	@NotBlank(message="{movement.input.description.empty}")
	private String mDescription;

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

	public String getmAmount() {
		return mAmount;
	}

	public void setmAmount(String mAmount) {
		this.mAmount = mAmount;
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



}
