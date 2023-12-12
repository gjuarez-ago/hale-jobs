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
import com.services.chambitas.domain.PostulatesOffer;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.PostulateByOfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.IPostulatesOfferRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IPostulatesByOfferService;

@Service
@Transactional
public class PostulatesOfferServiceImpl implements IPostulatesByOfferService{

	@Autowired
	private IPostulatesOfferRepository postulatesOfferRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired 
	private IOfferRepository offerRepository;
	
	@Override
	public PostulatesOffer createPostulation(PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element = new PostulatesOffer();
		
		Offer offer = exisOffer(request.getOfferId());
		existPostulation(request.getUserId(),offer.getId());		
		User user =  existUser(request.getUserId());

		element.setConsecutive(generateConsecutive());
		element.setOffer(offer);
		element.setUser(user);
		element.setStatus(0);
		element.setComments(request.getComments());
		
		element.setRegBorrado(0);
		element.setRegCreatedBy(user.getId());
		element.setRegDateCreated(new Date());
		
		postulatesOfferRepository.save(element);
		
		return element;
	}

	@Override
	public PostulatesOffer changeStatus(String id, PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element =  existPostulatesOffer(id);
		
		element.setStatus(request.getStatus());
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(request.getUserId());
		
		postulatesOfferRepository.save(element);
		return element;
	}

	@Override
	public PostulatesOffer deletePostulation(String id, Long userId) throws GenericException {
		PostulatesOffer element =  existPostulatesOffer(id);
		element.setRegBorrado(1);
		postulatesOfferRepository.save(element);
		return element;
	}

	@Override
	public PostulatesOffer findPostulationById(String id) throws GenericException {
		PostulatesOffer element =  existPostulatesOffer(id);
		return element;
	}

	@Override
	public List<PostulatesOffer> getPostulatesByUserIdM(Long userId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserMovil(userId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getPostulatesByUserIdW(Long userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserWEB(userId, pageable); 
		return list;
	}

	@Override
	public List<PostulatesOffer> getAllPostulatesByOfferM(Long offerId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferMovil(offerId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesByOfferW(Long offerId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferWEB(offerId, pageable); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesAdmin(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findAll(pageable); 
		return list;
	}

	
	
	// Privates methods
	private PostulatesOffer existPostulatesOffer(String consecutive) throws GenericException {
		
		PostulatesOffer postulate = postulatesOfferRepository.findPostulateById(consecutive);
		
		if(postulate == null) {
			throw new GenericException("No se encontro la postulación");
		}
		
		return postulate;
	}
	
	
	private PostulatesOffer existPostulation(Long userId, Long offerId) throws GenericException {
	     
		PostulatesOffer postulate = postulatesOfferRepository.findPostulateByUserAndOffer(userId, offerId);
		
		if(postulate != null) {
			throw new GenericException("Ya te has postulado a esta vacante.");
		}
		
		return postulate;
	}
	
	
	private User existUser(Long id) throws GenericException {
		User user = userRepository.findUserById(id);
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
		long lastElement = postulatesOfferRepository.count();
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
