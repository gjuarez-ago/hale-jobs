package com.services.chambitas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String consecutive;
	
	// Monto ofrecido
	@Column(nullable = false)
	private double amountOffered;
	
	// Titulo de la vacante
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private int status;

	// Urgencia del trabajo
	@Column(nullable = false)
	private String urgency;
	
	// Estado del trabjo
	@Column(nullable = false)
	private String state;
	
	// Ciudad de empleo
	@Column(nullable = false)
	private String city;
	
	// Dirección de trabajo
	@Column(nullable = false)
	private String address;
	
	// Tiene un reporte 
	private boolean haveComplaint;
	
	// Busca mas de un candidato
	private boolean multiplePostulates;
	
	// Mostrar salario
	private boolean showSalary;
	
	private boolean showCompany;
	
	// Categoria 
	private String category;
	
	private String[] benefits;
	
	private String[] mainActivities;

	private int maxPostulations;
		
	// Remoto, City, State
	private String workPlace;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Company company;
	
	// Tipo de trabajo -- Jornada completa, Practicas, Media jornada 
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TypeOfJob typeOfJob;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TypeOfPayment typeOfPayment;
	
    // Usuario que creo la solicitud	
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	
	// Usuario que fue selecciona para llevar a cabo el trabajo
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User userSelected;
	
	@ManyToMany(targetEntity = File.class,cascade = CascadeType.ALL)
	private List<File> images;
	
	private double amountAcepted;
	 
    private Date regDateCreated;
	
	@Column(columnDefinition = "integer default 0")
	private int RegBorrado;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regCreatedBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateUpdated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regUpdateBy;	

}
