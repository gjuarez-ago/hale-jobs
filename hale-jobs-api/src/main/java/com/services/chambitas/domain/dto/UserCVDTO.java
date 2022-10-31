package com.services.chambitas.domain.dto;

import java.util.List;

import com.services.chambitas.domain.SchoolByUser;
import com.services.chambitas.domain.SkillsByUser;
import com.services.chambitas.domain.WorkExperiences;

import lombok.Data;

@Data
public class UserCVDTO {
	
	private String aboutMe;
	
	private String titleJob;
	
	private Long country;
	
	private Long state;
	
	private Long city;
	
	private double salary;
	
	private Long category;
	
	private Long subcategory;
	
	private List<SkillsByUser> skills;
	
	private List<SchoolByUser> school;
	
	private List<WorkExperiences> experiences;
	
}
