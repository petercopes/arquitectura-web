package com.tp2.dto;

import lombok.Data;

@Data
public class CareerInscriptionsDTO {

	private String careerName;
	private long careerId;
	private long studentsAmount;
	
	public CareerInscriptionsDTO(String careerName, Long careerId, long studentsAmount) {
		this.careerName = careerName;
		this.careerId = careerId;
		this.studentsAmount = studentsAmount;
	}
	
	
	
}
