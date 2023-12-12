package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class WorkExperiencesDTO {
	
	private String job;
	
	private String company;
	
	private String description;
	
	private Date begins;
	
	private Date ends;
	
	private String[] skills;
	
	private boolean currentlyWork;
	

}
