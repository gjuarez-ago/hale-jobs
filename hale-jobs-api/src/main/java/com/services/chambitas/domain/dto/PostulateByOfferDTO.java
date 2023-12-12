package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class PostulateByOfferDTO {

	private String offerId;

	private Long userId;
	
	private String comments;
			
	private int status;
		
}
