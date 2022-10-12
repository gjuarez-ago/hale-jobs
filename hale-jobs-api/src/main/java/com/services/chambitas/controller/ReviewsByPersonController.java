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

import com.services.chambitas.domain.ReviewsPerson;
import com.services.chambitas.domain.dto.ReviewsByPersonDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IReviewsByPersonService;

@RestController
@RequestMapping(path = {"/reviews-by-person"})
public class ReviewsByPersonController {
	
	@Autowired
	IReviewsByPersonService service;
	
	// Crear review luego de finalizar el trabajo
		@PostMapping("/create")
		public ResponseEntity<ReviewsPerson> createReview(@RequestBody ReviewsByPersonDTO request) throws GenericException {
			ReviewsPerson response = service.createReviewsByPerson(request);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		}
	   	
		// Eliminar review By ID
		@DeleteMapping("/delete/{id}/{userId}")
		public ResponseEntity<ReviewsPerson> deleteReview(@PathVariable(value = "id") String id, @PathVariable(value = "userId") String userId) throws GenericException {
			ReviewsPerson response = service.deleteReviewsByPerson(id, userId);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		}
			
		// Editar review
		@PostMapping("/edit/{id}")
		public ResponseEntity<ReviewsPerson> editReview(@PathVariable(value = "id") String id, @RequestBody ReviewsByPersonDTO request) throws GenericException {
			ReviewsPerson response = service.editReviewsByPerson(id, request);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		}
		
		// Buscar review por ID
		@GetMapping("/find/{id}")
		public ResponseEntity<ReviewsPerson> findReviewById(@PathVariable(value = "id") String id) throws GenericException {
			ReviewsPerson response = service.findReviewsById(id);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		}

		// Visualizar reviews por persona que ofrecio el servicio Móvil
		@GetMapping("/view-reviews-by-user-m/{id}")
		public ResponseEntity<List<ReviewsPerson>> viewReviewsByUserMovil(@PathVariable(value = "id") String id) throws GenericException {
			List<ReviewsPerson> response = service.getAllReviewsByPersonMovil(id);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		}	
			
		// Visualizar reviews por persona que ofrecio el servicio WEB
		@GetMapping("/view-reviews-by-user-w")
		public ResponseEntity<Page<ReviewsPerson>> viewReviewsByUserWEB(@RequestParam("userId") String userId,
				@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) throws GenericException {
			Page<ReviewsPerson> response = service.getAllReviewsByPersonWEB(userId, pageNo, pageSize);
			    return new ResponseEntity<>(response , HttpStatus.OK);
		}	
		
		// Visualizar reviews admin el servicio WEB
		@GetMapping("/view-reviews-admin")
		public ResponseEntity<Page<ReviewsPerson>> viewReviewsAdmin(@RequestParam("keyword") String keyword,
				@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) throws GenericException {
			Page<ReviewsPerson> response = service.getAllReviewsByPersonAdmin(keyword, pageNo, pageSize);
			    return new ResponseEntity<>(response , HttpStatus.OK);
		}

		// Visualizar reviews por oferta
		@GetMapping("/reviews-by-offer/{offerId}")
		public ResponseEntity<List<ReviewsPerson>> viewReviewsByOffer(@PathVariable(value = "offerId") String offerId) throws GenericException {
			List<ReviewsPerson> response = service.findReviewsPersonByOffer(offerId);
		    return new ResponseEntity<>(response , HttpStatus.OK);
		 }	

}
