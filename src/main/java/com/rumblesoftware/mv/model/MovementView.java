package com.rumblesoftware.mv.model;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.Immutable;

import com.rumblesoftware.mv.io.MovementType;

@Entity
@Immutable
public class MovementView {

	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Id
	@Column(name = "movement_id")
	private Long movementId;

	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name= "replication_source")
	private Long replicationSource;
	
	@Column
	private Boolean isRecurrent;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "movement_type")
	private MovementType movementType;

	@Column(name = "movement_date")
	private Date movementDate;

	@Column(name = "movement_description")
	private String mDescription;

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

	public Long getReplicationSource() {
		return replicationSource;
	}

	public void setReplicationSource(Long replicationSource) {
		this.replicationSource = replicationSource;
	}

	public Boolean getIsRecurrent() {
		return isRecurrent;
	}

	public void setIsRecurrent(Boolean isRecurrent) {
		this.isRecurrent = isRecurrent;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	
	

}
