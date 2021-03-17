package com.rumblesoftware.mv.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.rumblesoftware.mv.io.MovementType;

@Entity(name = "TRecurrent_Mov")
@IdClass(value = RecurrentMovID.class)
public class RecurrentMovEntity implements Serializable {

	private static final String RESPONSIBLE_USER = "MOVEMENTS_MS";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Id
	@Column(name = "rec_mov_Id")
	@GenericGenerator(name = "rec_increment", strategy = "increment")
	@GeneratedValue(generator = "rec_increment")
	private Long recurrentMovId;

	@Column(name = "category_id")
	private Long categoryId;
	
	@NotNull
	@Column(name = "amount")
	private BigDecimal amount;

	@NotNull
	@Column(name = "movement_type")
	private MovementType movementType;

	@NotNull
	@Column(name = "movement_date")
	private Date movementDate;

	@Column(name = "movement_description",length = 15)
	private String mDescription;

	@Column(name = "creation_date")
	@NotNull
	private Date creationDate;

	@NotNull
	@Column(name = "last_update_date")
	private Date lastUpdateDate;

	@NotNull
	@Column(name = "last_update_made_by")
	private String lastUpdateMadeBy;
	
	@Column(name = "activity_status")
	private Integer activityStatus;

	@PrePersist
	@PreUpdate
	private void prePersist() {
		Date now = new Date();

		if (this.creationDate == null)
			creationDate = now;
		lastUpdateDate = now;

		lastUpdateMadeBy = RESPONSIBLE_USER;
		
		if(activityStatus == null)
			activityStatus = 1;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public static String getResponsibleUser() {
		return RESPONSIBLE_USER;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public String getLastUpdateMadeBy() {
		return lastUpdateMadeBy;
	}

	public Long getRecurrentMovId() {
		return recurrentMovId;
	}

	public void setRecurrentMovId(Long recurrentMovId) {
		this.recurrentMovId = recurrentMovId;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}	

	
	
}
