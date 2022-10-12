package com.services.chambitas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.service.IComplaintService;

@RestController
@RequestMapping(path = {"/complaint"})
public class ComplaintController {

	@Autowired
	IComplaintService service;
	
	@GetMapping("/view-admin")
	public ResponseEntity<Page<Complaints>> getAllCommentsAdmin(@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		
		Page<Complaints> response = service.getAllComplaintsByAdmin(pageNo, pageSize);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}	
	
	
	
}
