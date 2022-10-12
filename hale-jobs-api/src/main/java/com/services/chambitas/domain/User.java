/**
 * 
 */
package com.services.chambitas.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author gjuarezd
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
public class User {
	

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    
    private String consecutive;

    @Column(columnDefinition = "varchar(100) default 'Pendiente'")
	private String names;

    @Column(columnDefinition = "varchar(100) default 'Pendiente'")
	private String surnames;
	
    @Column(columnDefinition = "varchar(100) default 'Pendiente'")
	private String motherLastName;
	
    @Column(columnDefinition = "varchar(100) default 'Pendiente'")
	private String fatherLastName;
	
	// Dirección de correo o número telefonico
	@Column(nullable = false)	
	private String username;
	
	private String gender;
	
	private Date dateOfBirth;
	
	@Column(columnDefinition = "varchar(100) default 'Pendiente'")
	private String email;
	
	private String numberPhone;
	
	private String role;
	
	private String profileImageUrl;
	
	private Date joinDate;
	
	private String[] authorities;
	
	private String country;
	
	private String city;
	
	private String state;

	private boolean isActive;
	
	private boolean isNotLocked;
	
    private Date lastLoginDate;
    
    private Date lastLoginDateDisplay;
    
    // Ultimo día de premium
    private String lastDayPremium;
    
    // Es premium
    private boolean isPremium;
    
    // URL del CV
    private String urlCV;
    
    // Acerca de mi
    private String aboutMe;
    
    // Puesto
    private String job;
    
    // Salario
    private double salary;
    
    // Skills
    private String[] skills;
    
    
    // Education
    private String school;
    
    @ManyToMany(targetEntity = WorkExperiences.class,cascade = CascadeType.ALL)
  	private List<WorkExperiences> workExperiences;
       
    @ManyToMany(targetEntity = Permission.class,cascade = CascadeType.ALL)
	private List<Permission> permissions;
    
    // Datos delicados
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String curp;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String ocr;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String cic;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String urlINEF;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String urlINEB;	
	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  	private String token;
  	
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  	private Date expireToken;
    
    // Campo de auditoria
	@Column(columnDefinition = "integer default 0")
	private int regBorrado;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateCreated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regCreatedBy;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date regDateUpdated;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String regUpdateBy;
	
	

}
