package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class OfferEditDTO {
	
	String offerId;
	
	String title;
	
	String description;
	
	String urgency; 
	
	String address;
	
	Long typeOfPayment;
	
	Long userId;
	
	Long rangeAmount;

}
