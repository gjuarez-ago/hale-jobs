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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Company;
import com.services.chambitas.domain.dto.CompanyDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.service.ICompanyService;

@RestController
@RequestMapping(path = {"/company"})
public class CompanyController{

	@Autowired
	private ICompanyService service;

	@GetMapping("/find/{id}")
	public ResponseEntity<Company> findCompanyById(@PathVariable(value = "id") Long id) throws GenericException  {
		Company response = service.findCompanyById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	
	@PostMapping("create")
	public ResponseEntity<Company> createCompany(
			@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "category") Long category,
			@RequestParam(value = "urlSite") String urlSite,
			@RequestParam(value = "urlLinkedin") String urlLinkedin,
			@RequestParam(value = "ownerId") Long ownerId,
			@RequestParam(value = "regimenFiscal") String regimenFiscal,
			@RequestParam(value = "rfc") String rfc,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "size") String sizeCompany,
			@RequestParam(value = "public") boolean isPublic
			) throws NotAnImageFileException, IOException, GenericException {
		Company response = service.createCompany(image, name, description, category, urlSite, urlLinkedin, ownerId, regimenFiscal, rfc, address, sizeCompany, isPublic);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Company> deleteCompanyById(@PathVariable(value = "id") Long id) throws GenericException {
		Company response = service.findCompanyById(id);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PutMapping("/update-image/{userId}")
	public ResponseEntity<String> updateProfileImage(@PathVariable(value="userId") Long userId, @RequestParam(value = "id") Long id, @RequestParam(value = "image") MultipartFile image) throws IOException, NotAnImageFileException, GenericException {
		String response = service.updateProfileImage(userId, id, image);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/find-companies-field")
	public ResponseEntity<List<Company>> getCompaniesOnlyKeywords() {
		List<Company> response = service.getCompaniesOnlyKeywords();
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@GetMapping("/get-companies")
	public ResponseEntity<Page<Company>> getCompaniesGlobal(
     @RequestParam(value = "name", required = false) String keyword,
     @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
     @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		Page<Company> response = service.getCompaniesGlobal(keyword, pageNo, pageSize);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping("/get-companies-by-owner")
	public ResponseEntity<Page<Company>> getCompaniesGlobal(
     @RequestParam(value = "ownerId", required = false) Long ownerId,
     @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
     @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
		Page<Company> response = service.getCompaniesByOwner(ownerId, pageNo, pageSize);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}

	@PutMapping("update/{id}/{userId}")
	public ResponseEntity<Company> updateCompany(@PathVariable(value="id") Long id, @RequestBody CompanyDTO request) throws GenericException {
		Company response = service.updateCompany(id, request);
	    return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	
	
	
	
}
