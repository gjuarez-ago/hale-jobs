package com.services.chambitas.domain.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CompanyDTO {
	
	@Column(nullable = false)
	private String name;
	
	private String description;
	
	private Long category;
	
	private String urlSite;
	
	private String urlLinkedin;
	
	private String sizeCompany;
	
	private Long ownerId;
	
	private String address;
	
	private boolean isvisible;
	
	private Long userId;
	
}
