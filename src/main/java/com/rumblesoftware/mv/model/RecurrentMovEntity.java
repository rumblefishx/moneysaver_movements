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
	@Column(name = "movement_id")
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	private Long movementId;

	@Id
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

	@Column(name = "movement_description")
	@Size(max=15)
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

	@PrePersist
	private void prePersist() {
		Date now = new Date();

		if (this.creationDate == null)
			creationDate = now;
		lastUpdateDate = now;

		lastUpdateMadeBy = RESPONSIBLE_USER;
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

	public Long getMovementId() {
		return movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((lastUpdateDate == null) ? 0 : lastUpdateDate.hashCode());
		result = prime * result + ((lastUpdateMadeBy == null) ? 0 : lastUpdateMadeBy.hashCode());
		result = prime * result + ((mDescription == null) ? 0 : mDescription.hashCode());
		result = prime * result + ((movementDate == null) ? 0 : movementDate.hashCode());
		result = prime * result + ((movementId == null) ? 0 : movementId.hashCode());
		result = prime * result + ((movementType == null) ? 0 : movementType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecurrentMovEntity other = (RecurrentMovEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (lastUpdateDate == null) {
			if (other.lastUpdateDate != null)
				return false;
		} else if (!lastUpdateDate.equals(other.lastUpdateDate))
			return false;
		if (lastUpdateMadeBy == null) {
			if (other.lastUpdateMadeBy != null)
				return false;
		} else if (!lastUpdateMadeBy.equals(other.lastUpdateMadeBy))
			return false;
		if (mDescription == null) {
			if (other.mDescription != null)
				return false;
		} else if (!mDescription.equals(other.mDescription))
			return false;
		if (movementDate == null) {
			if (other.movementDate != null)
				return false;
		} else if (!movementDate.equals(other.movementDate))
			return false;
		if (movementId == null) {
			if (other.movementId != null)
				return false;
		} else if (!movementId.equals(other.movementId))
			return false;
		if (movementType != other.movementType)
			return false;
		return true;
	}

	

}
