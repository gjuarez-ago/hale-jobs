package com.services.chambitas.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.repository.IComplaintRepository;
import com.services.chambitas.service.IComplaintService;


@Service
@Transactional
public class ComplaintServiceImpl implements IComplaintService{

	@Autowired
	IComplaintRepository repository;

	@Override
	public Page<Complaints> getAllComplaintsByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Complaints> response = repository.findAll(pageable);
		return response;
	}
	
}
