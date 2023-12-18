package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.dto.OfferDTO;
import com.services.chambitas.domain.dto.OfferEditDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IOfferService {
	
	Offer createOffer(OfferDTO request) throws GenericException;
	
	Offer editOffer(OfferEditDTO request) throws GenericException;	
	
	Offer deleteOfferById(String id, Long userId) throws GenericException;
		
	Offer findOfferById(String id) throws GenericException;	
	
	Offer reportOffer(String id, String comment, String category, Long userId) throws GenericException;
		
	List<Offer> getAllOfferByUserMovil(Long userId);
	
	Page<Offer> getAllOfferByUserWEB(Long userId, String subcategory,String title, int status,  String workPlace,   String urgency,  String levelStudy, int pageNo, int pageSize);
	
	List<Offer> findOfferGeneralMovil(String keyword);

	Page<Offer> findOfferGeneralWEB(String keyword, int pageNo, int pageSize);
	
	Page<Offer> getAllOfferByAdmin(String keyword,int pageNo, int pageSize);
	
}
