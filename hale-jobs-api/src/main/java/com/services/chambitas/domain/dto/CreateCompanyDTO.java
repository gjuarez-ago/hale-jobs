package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class CreateCompanyDTO {
	
	private String name;
	
	private String description;
	
	private Long category;
	
	private String imageURL;	
	
	private String urlSite;
	
	private String urlLinkedin;
	
	private String sizeCompany;

	private String RFC;
	
	private String regimenFiscal;
	
	private String address;
	
}
