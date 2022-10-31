package com.services.chambitas.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;

@Service
public interface IOfferService {
	
	// Crear oferta por usuario
	Offer createOffer(int amountOffered,String title,
	String description,boolean status,
	String urgency,String state,
	String city,String address,
	MultipartFile[] images,String typeOfJob,String typeOfPayment,
	String userId) throws NotAnImageFileException, IOException, GenericException;
	
	// Editar oferta
	Offer editOffer(String offerId,double amountOffered,String description,String urgency, String address,String typeOfPayment,  String userId) throws GenericException;	
	
	// Eliminar oferta
	Offer deleteOfferById(String id, String userId) throws GenericException;
		
	// Buscar oferta por ID
	Offer findOfferById(String id) throws GenericException;	
	
	// Reportar oferta por ID
	Offer reportOffer(String id, String comment, String category, String userId) throws GenericException;
	
	// Selecionar al postulado
	Offer selectPostulate(String id, String userPostulate, double amountAcepted) throws GenericException;
	
	// Mostrar ofertas móvil
	List<Offer> getAllOfferByUserMovil(String userId);
	
	// Mostrar ofertas WEB
	Page<Offer> getAllOfferByUserWEB(String userId, int pageNo, int pageSize);
	
	// Buscador de ofertas en la móvil
	List<Offer> findOfferGeneralMovil(String keyword);

	// Buscador de ofertas en la WEB
	Page<Offer> findOfferGeneralWEB(String keyword, int pageNo, int pageSize);
	
	// Mostrar ofertas administrador
	Page<Offer> getAllOfferByAdmin(String keyword,int pageNo, int pageSize);
	
}
