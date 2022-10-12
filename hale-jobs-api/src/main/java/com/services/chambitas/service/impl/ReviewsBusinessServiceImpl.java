package com.services.chambitas.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.ReviewsBusiness;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.ReviewsByBusinessDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IReviewsBusinessRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IReviewsByBusinessService;

@Service
@Transactional
public class ReviewsBusinessServiceImpl implements IReviewsByBusinessService{

	@Autowired
	IReviewsBusinessRepository reviewsBusinessRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IOfferRepository offerRepository;
	
	@Override
	public ReviewsBusiness createReviewBusiness(ReviewsByBusinessDTO request) throws GenericException {
		
		ReviewsBusiness element = new ReviewsBusiness();
		
		User userClient =  existUser(request.getUserIdClient());
		User userSupplier = existUser(request.getUserIdSupplier());
		Offer offer = exisOffer(request.getOfferId());
		
		element.setConsecutive(generateConsecutive());
		element.setOffer(offer);
		element.setQualification(request.getQualification());
		element.setComments(request.getComments());
		element.setUserClient(userClient);
		element.setUserSupplier(userSupplier);
		element.setRegCreatedBy(request.getUserIdSupplier());
		element.setRegDateCreated(new Date());
		element.setRegBorrado(0);
		
		reviewsBusinessRepository.save(element);
		return element;
	}

	@Override
	public ReviewsBusiness deleteReviewBusiness(String id, String userId) throws GenericException {
		
	   ReviewsBusiness element = existReviewsBusiness(id);
	
	   element.setRegBorrado(1);
	   element.setRegUpdateBy(userId);
	   element.setRegDateUpdated(new Date());
	   reviewsBusinessRepository.save(element);  
	
		return element;
	}

	@Override
	public ReviewsBusiness editReviewBusiness(String id, ReviewsByBusinessDTO request) throws GenericException {
		
		 ReviewsBusiness element = existReviewsBusiness(id);
		 
		 element.setComments(request.getComments());
		 element.setRegUpdateBy(request.getUserIdSupplier());
		 element.setRegDateUpdated(new Date());
		 reviewsBusinessRepository.save(element);  
		 return element;
	}

	@Override
	public ReviewsBusiness findReviewBusinessById(String id) throws GenericException {
		 ReviewsBusiness element = existReviewsBusiness(id);
		return element;
	}

	@Override
	public List<ReviewsBusiness> getAllReviewBusinessByUserMovil(String userId) {
		
		List<ReviewsBusiness> list = reviewsBusinessRepository.findReviewsBusinessByUserMovil(userId);
		return list;
	}

	@Override
	public Page<ReviewsBusiness> getAllReviewBusinessByUserWEB(String userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<ReviewsBusiness> list = reviewsBusinessRepository.findReviewsBusinessByUserWEB(userId, pageable);
		return list;
	}
	
	@Override
	public Page<ReviewsBusiness> getAllReviewBusinessAdmin(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<ReviewsBusiness> list = reviewsBusinessRepository.findReviewsBusinessAdmin(keyword, pageable);
		return list;
	}

	@Override
	public List<ReviewsBusiness> findReviewsBusinessByOffer(String offerId) {
	 	 List<ReviewsBusiness> list = reviewsBusinessRepository.findReviewsBusinessByOffer(offerId);
		return list;
	}
		
	// private methods
	
	private ReviewsBusiness existReviewsBusiness(String id) throws GenericException {
		
		ReviewsBusiness element = reviewsBusinessRepository.findReviewsBusinessById(id);
		
		if(element == null) {
			throw new GenericException("No se encontro el review");
		}
		
		return element;
	}
	
	
	private User existUser(String consecutive) throws GenericException {
		User user = userRepository.findUserByConsecutive(consecutive);
		if(user == null) {throw new GenericException("No se encontro el usuario");}
		return user;
	}
	
	private Offer exisOffer(String consecutive) throws GenericException {
		Offer offer = offerRepository.findOfferByConsecutive(consecutive);
		if(offer ==  null) {throw new GenericException("No se encontro la oferta");}
		return offer;
	}
	
     private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = reviewsBusinessRepository.count();
	    lastElement += 1;
	    
	    if(lastElement >= 0 && lastElement < 10) {consecutive = "100000000"  + lastElement;}
		if(lastElement >= 10 && lastElement < 100) {consecutive = "10000000"  + lastElement;}
		if(lastElement >= 100 && lastElement < 1000) {consecutive = "1000000"  + lastElement;}
		if(lastElement >= 1000 && lastElement < 10000) {consecutive = "100000"  + lastElement;}
		if(lastElement >= 10000 && lastElement < 100000) {consecutive = "10000"  + lastElement;}
		if(lastElement >= 100000 && lastElement < 1000000) {consecutive = "1000"  + lastElement;}
		if(lastElement >= 1000000 && lastElement < 10000000) {consecutive = "100"  + lastElement;}
		if(lastElement >= 10000000 && lastElement < 100000000) {consecutive = "10"  + lastElement;}
		if(lastElement >= 100000000 && lastElement < 1000000000) {consecutive = "1"  + lastElement;}
		return consecutive;
	}

	

	
	
}
