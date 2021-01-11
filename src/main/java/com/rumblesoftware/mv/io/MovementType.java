package com.rumblesoftware.mv.io;

import java.util.Arrays;

public enum MovementType {
	INCOME(0,"INCOME"),
	OUTCOME(1,"OUTCOME");
	
	private Integer code;
	private String description;
	
	private MovementType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static MovementType getMvTypeByCode(Integer code) {
		for(MovementType mov : MovementType.values()) {
			if(mov.getCode() == code)
				return mov;
		}
		return null;
	}
}
