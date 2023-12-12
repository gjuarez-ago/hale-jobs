package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class OfferDTO {

	// Titulo de la vacante
	private String title;
	
	private String description;
	
	private String comment;

	// Urgencia del trabajo
	private String urgency;
	
	// Dirección de trabajo
	private String address;

	// Mostrar salario
	private boolean showSalary;
	
    private boolean showCompany;
    
	private String[] benefits;

	private String[] mainActivities;
	
	private String[] skills;

	private int maxPostulations;
	
	private String workPlace; 
	
	private int typeOfOffer;
	
	private int status;

	private Long company;

	private Long typeOfJob;

	private Long typeOfPayment;
	
	private Long rangeAmount;
	
	private Long country;
	
	private Long state;

	private Long levelStudy;
	
	private Long city;
	
	private Long category;
		
	private Long subcategory;
	
	private Long userId;

}
