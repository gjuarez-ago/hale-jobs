package com.services.chambitas.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.service.IOfferService;

@RestController
@RequestMapping(path = {"/offer"})
public class OfferController {
	
	
	@Autowired
	IOfferService service;
	
   // Crear oferta
	@PostMapping("/create")
	public ResponseEntity<Offer> createOffer(
	@RequestParam("amountOffered") int amountOffered, @RequestParam("title") String title,
	@RequestParam("description") String description,@RequestParam("status")  boolean status,
	@RequestParam("urgency") String urgency, @RequestParam("state") String state,
	@RequestParam("city") String city, @RequestParam("address") String address,
	@RequestParam("images") MultipartFile[] images, @RequestParam("typeOfJob") String typeOfJob,@RequestParam("typeOfPayment") String typeOfPayment) throws NotAnImageFileException, IOException, GenericException  
	{
		Offer response = service.createOffer(amountOffered, title, description, status, urgency, state, city, address, images, typeOfJob, typeOfPayment, typeOfPayment);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
   // Editar oferta
	@PostMapping("/edit")
	public ResponseEntity<Offer> editOffer(
	@RequestParam("amountOffered") int amountOffered, @RequestParam("title") String title,
	@RequestParam("description") String description,@RequestParam("status")  boolean status,
	@RequestParam("urgency") String urgency, @RequestParam("state") String state,
	@RequestParam("city") String city, @RequestParam("address") String address,
	@RequestParam("images") MultipartFile[] images, @RequestParam("typeOfJob") String typeOfJob,@RequestParam("typeOfPayment") String typeOfPayment) throws NotAnImageFileException, IOException, GenericException  
	{
		Offer response = service.createOffer(amountOffered, title, description, status, urgency, state, city, address, images, typeOfJob, typeOfPayment, typeOfPayment);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
   
   // Eliminar oferta
	@DeleteMapping("/delete/{offerId}/{userId}")
	public ResponseEntity<Offer> deleteOffer(@PathVariable("offerId") String offerId, @PathVariable("userId") String userId) throws GenericException  {
		Offer response = service.deleteOfferById(offerId, userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
   // Buscar oferta por ID
   @DeleteMapping("/find/{offerId}")
   public ResponseEntity<Offer> findOfferById(@PathVariable("offerId") String offerId) throws GenericException  {
		Offer response = service.findOfferById(offerId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   // Reportar oferta
   @PostMapping("/report-offer")
   public ResponseEntity<Offer> reportOffer(@RequestParam("offerId") String offerId,@RequestParam("comments") String comments, @RequestParam("category") String category, @RequestParam("userId") String userId) throws GenericException  {
		Offer response = service.reportOffer(offerId, comments, category, userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   // Seleccionar postulado
   @PostMapping("/select-postulate")
   public ResponseEntity<Offer> selectPostulate(@RequestParam("offerId") String offerId,@RequestParam("userId") String userId,@RequestParam("amountAcepted") double amountAcepted  ) throws GenericException  {
		Offer response = service.selectPostulate(offerId, userId, amountAcepted);
	    return new ResponseEntity<>(response , HttpStatus.OK);
   }   
   
   // Mostrar ofertas por usuario móvil
   @GetMapping("/view-offer-m/{key}")
   public ResponseEntity<List<Offer>> getAllOffersByUserMovil(@PathVariable("key") String offerId) {
		List<Offer> response = service.getAllOfferByUserMovil(offerId);
		return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   // Mostrar ofertas WEB
   @GetMapping("/view-offer-w")
	public ResponseEntity<Page<Offer>> getAllOffersByUserWEB(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
           @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<Offer> response = service.getAllOfferByUserWEB(keyword, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
		
   // Buscador de ofertas en la móvil
   @GetMapping("/search-offers-m/{key}")
   public ResponseEntity<List<Offer>> searchOffersMovil(@PathVariable("keyword") String keyword) {
		List<Offer> response = service.findOfferGeneralMovil(keyword);
		return new ResponseEntity<>(response , HttpStatus.OK);
   }
	
   // Buscador de ofertas en la WEB
   @GetMapping("/search-offers-w")
  	public ResponseEntity<Page<Offer>> searchOffersWEB(@RequestParam("keyword") String keyword,
  			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
  		
  		Page<Offer> response = service.findOfferGeneralWEB(keyword, pageNo, pageSize);
  		return new ResponseEntity<>(response , HttpStatus.OK);
  	}	
		
   // Mostrar ofertas administrador
   @GetMapping("/view-admin")
  	public ResponseEntity<Page<Offer>> viewAdmin(@RequestParam("keyword") String keyword,
  			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
             @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
	   
  		Page<Offer> response = service.getAllOfferByAdmin(keyword, pageNo, pageSize);
  		return new ResponseEntity<>(response , HttpStatus.OK);
  	}	
  		
   
}
