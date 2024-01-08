package com.services.chambitas.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	private String consecutive;

	private String title;

	private String content;

	// 1 : Aviso oferta // 2: Aceptación // 3 : Aceptación  // 
	private String typeAD;
	
	private String emailDestination;
	
	private String sendBy;
		
	private Long offerId;
	
	private Long userId;

}