package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class ComplaintDTO {

	private String title;
	
	private String comments;
	
	private String status;
	
	private Long user;

	private String offer;
	
	// 1: user 2 : company 3: other
	private int type;
	
}
