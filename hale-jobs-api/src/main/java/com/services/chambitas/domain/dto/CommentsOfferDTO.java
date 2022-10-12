package com.services.chambitas.domain.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CommentsOfferDTO {
	
	private String consecutive;

	private String offerId;
	
	private String userId;

	private String comment;

	private Date regDateCreated;

	@Column(columnDefinition = "integer default 0")
	private int RegBorrado;
	

}
