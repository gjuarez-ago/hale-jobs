package com.services.chambitas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportOfferDTO {
	
	private String category;
	
	private String comments;
	
	private String offerId;
	
	private Long userId;

}
