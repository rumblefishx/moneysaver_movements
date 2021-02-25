package com.rumblesoftware.mv.model;

import java.io.Serializable;

import javax.persistence.Column;

public class MovementID implements Serializable{

	private static final long serialVersionUID = 2320019713707945894L;

	private Long customerId;
	
	private Long movementId;
	
	public MovementID() {}

	public MovementID(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public MovementID(Long customerId, Long movementId) {
		super();
		this.customerId = customerId;
		this.movementId = movementId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((movementId == null) ? 0 : movementId.hashCode());
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
		MovementID other = (MovementID) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (movementId == null) {
			if (other.movementId != null)
				return false;
		} else if (!movementId.equals(other.movementId))
			return false;
		return true;
	}
	
	
}
