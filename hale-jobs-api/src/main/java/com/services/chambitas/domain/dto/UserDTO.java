package com.services.chambitas.domain.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	
private String names;
	
    private String motherLN;
    
    private String fatherLN;
   
	private String gender;
	
	private String emailContact;
	
	private Date dateOfBirth;
	
	private String location;

}
