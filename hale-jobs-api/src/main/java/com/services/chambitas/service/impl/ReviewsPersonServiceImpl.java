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
import com.services.chambitas.domain.ReviewsPerson;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.ReviewsByPersonDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IReviewsPersonRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IReviewsByPersonService;

@Service
@Transactional
public class ReviewsPersonServiceImpl implements IReviewsByPersonService{

	@Autowired
	IReviewsPersonRepository reviewsPersonRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IOfferRepository offerRepository;

	@Override
	public ReviewsPerson createReviewsByPerson(ReviewsByPersonDTO request) throws GenericException {
		
		ReviewsPerson element = new ReviewsPerson();
		
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
		
		reviewsPersonRepository.save(element);
		return element;
		
		
	}

	@Override
	public ReviewsPerson deleteReviewsByPerson(String id, String userId) throws GenericException {
		
		ReviewsPerson element = existReviewsPerson(id);
			
		element.setRegBorrado(1);
		element.setRegUpdateBy(userId);
		element.setRegDateUpdated(new Date());
		reviewsPersonRepository.save(element);  
		
		return element;
	}

	@Override
	public ReviewsPerson editReviewsByPerson(String id, ReviewsByPersonDTO request) throws GenericException {
		
	     ReviewsPerson element = existReviewsPerson(id);
		 
		 element.setComments(request.getComments());
		 element.setRegUpdateBy(request.getUserIdSupplier());
		 element.setRegDateUpdated(new Date());
		 reviewsPersonRepository.save(element);  
		
		 return element;
	}

	@Override
	public ReviewsPerson findReviewsById(String id) throws GenericException {
   	   ReviewsPerson element = existReviewsPerson(id);
		return element;
	}

	@Override
	public List<ReviewsPerson> getAllReviewsByPersonMovil(String userId) {
		List<ReviewsPerson> list = reviewsPersonRepository.findReviewsPersonByUserMovil(userId);
		return list;
	}

	@Override
	public Page<ReviewsPerson> getAllReviewsByPersonWEB(String userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<ReviewsPerson> list = reviewsPersonRepository.findReviewsPersonByUserWEB(userId,pageable);
		return list;
	}

	@Override
	public List<ReviewsPerson> findReviewsPersonByOffer(String offerId) {
		
		List<ReviewsPerson> list = reviewsPersonRepository.findReviewsPersonByOffer(offerId);
		return list;
	}
	
	@Override
	public Page<ReviewsPerson> getAllReviewsByPersonAdmin(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<ReviewsPerson> list = reviewsPersonRepository.findReviewsBusinessAdmin(keyword, pageable);
		return list;
	}
	
	// Private Methods
	
	private ReviewsPerson existReviewsPerson(String id) throws GenericException {
		
		ReviewsPerson element = reviewsPersonRepository.findReviewsPersonById(id);
		
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
		long lastElement = reviewsPersonRepository.count();
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
