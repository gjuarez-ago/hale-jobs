package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class ReviewsByPersonDTO {
	
	private String consecutive;
	
	private double qualification;
	
	private String comments;	
	
	private String userIdClient;
	
	private String userIdSupplier;
	
	private String offerId;

}
