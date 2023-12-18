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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.dto.OfferDTO;
import com.services.chambitas.domain.dto.OfferEditDTO;
import com.services.chambitas.domain.dto.ReportOfferDTO;
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
	public ResponseEntity<Offer> createOffer(@RequestBody OfferDTO request) throws NotAnImageFileException, IOException, GenericException  
	{
		Offer response = service.createOffer(request);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
   // Editar oferta
	@PostMapping("/edit")
	public ResponseEntity<Offer> editOffer(@RequestBody OfferEditDTO request) throws NotAnImageFileException, IOException, GenericException  
	{
		Offer response = service.editOffer(request);
	   return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
   
   // Eliminar oferta
	@DeleteMapping("/delete/{offerId}/{userId}")
	public ResponseEntity<Offer> deleteOffer(@PathVariable("offerId") String offerId, @PathVariable("userId") Long userId) throws GenericException  {
		Offer response = service.deleteOfferById(offerId, userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
   // Buscar oferta por ID
   @GetMapping("/find/{offerId}")
   public ResponseEntity<Offer> findOfferById(@PathVariable("offerId") String offerId) throws GenericException  {
		Offer response = service.findOfferById(offerId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   // Reportar oferta
   @PostMapping("/report-offer")
   public ResponseEntity<Offer> reportOffer(@RequestBody ReportOfferDTO request) throws GenericException  {
		Offer response = service.reportOffer(request.getCategory(), request.getComments(), request.getCategory(), request.getUserId());
	    return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   
 
   // Mostrar ofertas por usuario móvil
   @GetMapping("/view-offer-m/{user}")
   public ResponseEntity<List<Offer>> getAllOffersByUserMovil(@PathVariable("user") Long userId) {
		List<Offer> response = service.getAllOfferByUserMovil(userId);
		return new ResponseEntity<>(response , HttpStatus.OK);
   }
   
   // Mostrar ofertas WEB
   @GetMapping("/view-offer-w")
	public ResponseEntity<Page<Offer>> getAllOffersByUserWEB(
		@RequestParam("user") Long userId,
		@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
		@RequestParam(value = "subcategory", required = true, defaultValue = "") String subcategory,
		@RequestParam(value = "title", required = false, defaultValue = "") String title, 
		@RequestParam(value = "status", required = false) int status,
		@RequestParam(value = "workPlace", required = false, defaultValue = "") String workPlace,
		@RequestParam(value = "urgency", required = false, defaultValue = "") String urgency,
		@RequestParam(value = "levelStudy", required = false, defaultValue = "") String levelStudy
        ) {
	   
		Page<Offer> response = service.getAllOfferByUserWEB(userId, subcategory, title, status, workPlace, urgency, levelStudy, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
		
   // Buscador de ofertas en la móvil
   @GetMapping("/search-offers-m/{keyword}")
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
