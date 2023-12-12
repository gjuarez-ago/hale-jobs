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

import com.services.chambitas.domain.PostulatesOffer;
import com.services.chambitas.domain.dto.PostulateByOfferDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.service.IPostulatesByOfferService;

@RestController
@RequestMapping(path = {"/postulates-by-offer"})
public class PostulatesByOfferController {

	@Autowired
	IPostulatesByOfferService service;
	
	@PostMapping("/create")
	public ResponseEntity<PostulatesOffer> createPostulation(@RequestBody PostulateByOfferDTO request) throws GenericException {
		PostulatesOffer response = service.createPostulation(request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@PostMapping("/change-status/{key}")
	public ResponseEntity<PostulatesOffer> editPostulation(@PathVariable("key") String id,@RequestBody PostulateByOfferDTO request) throws GenericException {
		PostulatesOffer response = service.changeStatus(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{key}/{user}")
	public ResponseEntity<PostulatesOffer> editPostulation(@PathVariable("key") String id, @PathVariable("user") Long userId) throws GenericException {
		PostulatesOffer response = service.deletePostulation(id, userId);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/find/{key}")
	public ResponseEntity<PostulatesOffer> findPostulation(@PathVariable("key") String id) throws GenericException {
		PostulatesOffer response = service.findPostulationById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/find-by-user-m")
	public ResponseEntity<List<PostulatesOffer>> findPostulationByUserM(@PathVariable("key") Long id) throws GenericException {
		List<PostulatesOffer> response = service.getPostulatesByUserIdM(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/postulates-by-user-w")
	public ResponseEntity<Page<PostulatesOffer>> getAllPostulatesByUser(@RequestParam("userId") Long userId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<PostulatesOffer> response = service.getPostulatesByUserIdW(userId, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	

	@GetMapping("/find-by-offer-m/{key}")
	public ResponseEntity<List<PostulatesOffer>> findPostulationByOfferM(@PathVariable("key") Long id) throws GenericException {
		List<PostulatesOffer> response = service.getPostulatesByUserIdM(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/postulates-by-offer-w")
	public ResponseEntity<Page<PostulatesOffer>> getAllPostulatesByOfferW(@RequestParam("offerId") Long offerId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<PostulatesOffer> response = service.getAllPostulatesByOfferW(offerId, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
	@GetMapping("/postulates-admin")
	public ResponseEntity<Page<PostulatesOffer>> getAllPostulatesAdmin(@RequestParam("offerId") String offerId,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<PostulatesOffer> response = service.getAllPostulatesAdmin(offerId, pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}
		
}
