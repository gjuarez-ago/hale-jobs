package com.services.chambitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.ReviewsBusiness;
import com.services.chambitas.domain.dto.ReviewsByBusinessDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IReviewsByBusinessService;

@RestController
@RequestMapping(path = {"/reviews-by-business"})
public class ReviewsByBusinessController {
	
	@Autowired
	IReviewsByBusinessService service;
	
	@PostMapping("/create")
	public ResponseEntity<ReviewsBusiness> createReview(@RequestBody ReviewsByBusinessDTO request) throws GenericException {
		ReviewsBusiness response = service.createReviewBusiness(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
   	
	@DeleteMapping("/delete/{id}/{userId}")
	public ResponseEntity<ReviewsBusiness> deleteReview(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) throws GenericException {
		ReviewsBusiness response = service.deleteReviewBusiness(id,userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
		
	@PostMapping("/edit/{id}")
	public ResponseEntity<ReviewsBusiness> editReview(@PathVariable(value = "id") String id, @RequestBody ReviewsByBusinessDTO request) throws GenericException {
		ReviewsBusiness response = service.editReviewBusiness(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<ReviewsBusiness> findReviewById(@PathVariable(value = "id") String id) throws GenericException {
		ReviewsBusiness response = service.findReviewBusinessById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/view-reviews-by-user-m/{id}")
	public ResponseEntity<List<ReviewsBusiness>> viewReviewsByUserMovil(@PathVariable(value = "id") String id) throws GenericException {
		List<ReviewsBusiness> response = service.getAllReviewBusinessByUserMovil(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}	
		
	@GetMapping("/view-reviews-by-user-w")
	public ResponseEntity<Page<ReviewsBusiness>> viewReviewsByUserWEB(@RequestParam("userId") String userId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) throws GenericException {
		Page<ReviewsBusiness> response = service.getAllReviewBusinessByUserWEB(userId, pageNo, pageSize);
		    return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
	@GetMapping("/view-reviews-admin")
	public ResponseEntity<Page<ReviewsBusiness>> viewReviewsAdmin(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) throws GenericException {
		Page<ReviewsBusiness> response = service.getAllReviewBusinessAdmin(keyword, pageNo, pageSize);
		    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/reviews-by-offer/{offerId}")
	public ResponseEntity<List<ReviewsBusiness>> viewReviewsByOffer(@PathVariable(value = "offerId") String offerId) throws GenericException {
		List<ReviewsBusiness> response = service.findReviewsBusinessByOffer(offerId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	 }	

	
}
