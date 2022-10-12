package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.ReviewsPerson;
import com.services.chambitas.domain.dto.ReviewsByPersonDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IReviewsByPersonService {
	
	// Crear review para calificar a una persona luego de finalizar el trabajo
	ReviewsPerson createReviewsByPerson(ReviewsByPersonDTO request) throws GenericException;
	
	// Eliminar review By ID
	ReviewsPerson deleteReviewsByPerson(String id, String userId) throws GenericException;
	
	// Editar review
	ReviewsPerson editReviewsByPerson(String id, ReviewsByPersonDTO request) throws GenericException;
	
	// Buscar review por ID
	ReviewsPerson findReviewsById(String id) throws GenericException;
	
	// Visualizar reviews por persona que ofrecio el servicio Móvil
	List<ReviewsPerson> getAllReviewsByPersonMovil(String userId);
	
	// Visualizar reviews por persona que ofrecio el servicio WEB
	Page<ReviewsPerson> getAllReviewsByPersonWEB(String userId, int pageNo, int pageSize);
	
	// Visualizar por admin
	Page<ReviewsPerson> getAllReviewsByPersonAdmin(String keyword,int pageNo, int pageSize);

	// Visualizar reviews por oferta
	List<ReviewsPerson> findReviewsPersonByOffer(String offerId);
	

}
