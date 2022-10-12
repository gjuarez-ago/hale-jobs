/**
 * 
 */
package com.services.chambitas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String consecutive;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String[] sectors;
	
	private String imageURL;	
	
	private String urlSite;
	
	private String sizeCompany;
	
	private String typeCompany;
	
	private String ownerId;
	
	private Date regDateCreated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regCreatedBy;

	@Column(columnDefinition = "integer default 0")
	private int regBorrado;	
	
	
}
