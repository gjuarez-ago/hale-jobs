package com.services.chambitas.domain.dto;

import lombok.Data;

@Data
public class PaymentDTO {
	
	private double amount;
	
	private String methodPayment;
	
	private String idReference;
	
	private String statusReference;
	
	private int estatus;
	
	private String currency;
	
	private String userId; 
	
	private String loanId;
	
	private String regCreatedBy;

	private String regUpdateBy;	
	
}
