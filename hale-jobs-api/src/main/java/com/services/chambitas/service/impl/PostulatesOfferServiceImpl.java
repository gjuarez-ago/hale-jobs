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
	IPostulatesOfferRepository postulatesOfferRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired 
	IOfferRepository offerRepository;
	
	@Override
	public PostulatesOffer createPostulation(PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element = new PostulatesOffer();
		
		User user =  existUser(request.getUserId());
		Offer offer = exisOffer(request.getOfferId());
		
		element.setConsecutive(generateConsecutive());
		element.setComments(request.getComments());
		element.setAmountOffered(request.getAmountOffered());
		element.setOffer(offer);
		element.setUser(user);
		element.setStatus("Enviada");
		element.setRegBorrado(0);
		element.setRegCreatedBy(user.getConsecutive());
		element.setRegDateCreated(new Date());
		
		postulatesOfferRepository.save(element);
		
		return element;
	}

	@Override
	public PostulatesOffer editPostulation(String id, PostulateByOfferDTO request) throws GenericException {
		
		PostulatesOffer element =  existPostulatesOffer(id);
		User user =  existUser(request.getUserId());
		
		
		element.setAmountOffered(request.getAmountOffered());
		element.setComments(request.getComments());
		element.setStatus(request.getStatus());
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(user.getConsecutive());
		
		postulatesOfferRepository.save(element);
		return element;
	}

	@Override
	public PostulatesOffer deletePostulation(String id, String userId) throws GenericException {
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
	public List<PostulatesOffer> getPostulatesByUserIdM(String userId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserMovil(userId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getPostulatesByUserIdW(String userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByUserWEB(userId, pageable); 
		return list;
	}

	@Override
	public List<PostulatesOffer> getAllPostulatesByOfferM(String offerId) {
		List<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferMovil(offerId); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesByOfferW(String offerId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferWEB(offerId, pageable); 
		return list;
	}

	@Override
	public Page<PostulatesOffer> getAllPostulatesAdmin(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize); 
		Page<PostulatesOffer> list = postulatesOfferRepository.findPostulatesByOfferWEB(keyword, pageable); 
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
