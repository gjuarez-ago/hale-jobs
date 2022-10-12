package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class PostulateByOfferDTO {

	
	private String offerId;

	private String userId;
	
	private double amountOffered;
	
	private String comments;
	
	private String status;
	
}
