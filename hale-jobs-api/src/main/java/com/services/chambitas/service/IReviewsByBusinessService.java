package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.ReviewsBusiness;
import com.services.chambitas.domain.dto.ReviewsByBusinessDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IReviewsByBusinessService {
	
	// Crear review luego de finalizar el trabajo
	ReviewsBusiness createReviewBusiness(ReviewsByBusinessDTO request) throws GenericException;
	
	// Eliminar review By ID
	ReviewsBusiness deleteReviewBusiness(String id, String userId) throws GenericException;
	
	// Editar review
	ReviewsBusiness editReviewBusiness(String id, ReviewsByBusinessDTO request) throws GenericException;
	
	// Buscar review por ID
	ReviewsBusiness findReviewBusinessById(String id) throws GenericException;
	
	// Visualizar reviews por persona que ofrecio el servicio Móvil
	List<ReviewsBusiness> getAllReviewBusinessByUserMovil(String userId);
	
	// Visualizar reviews por persona que ofrecio el servicio WEB
	Page<ReviewsBusiness> getAllReviewBusinessByUserWEB(String userId, int pageNo, int pageSize);
	
	// Visualizar reviews por persona que ofrecio el servicio WEB
	Page<ReviewsBusiness> getAllReviewBusinessAdmin(String keyword, int pageNo, int pageSize);

	// Visualizar reviews por oferta
	List<ReviewsBusiness> findReviewsBusinessByOffer(String offerId);
	
}
