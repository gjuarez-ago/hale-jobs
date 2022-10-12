package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class NotificationDTO {

	private String title;

	private String content;

	private String typeAD;
	
	private String emailDestination;
	
	private String sendBy;
	
	private String relatedRequest;

	private String regCreatedBy;
	
	private String regUpdateBy;
	
}
