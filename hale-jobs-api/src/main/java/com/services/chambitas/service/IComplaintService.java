package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Complaints;

@Service
public interface IComplaintService {
	  Page<Complaints> getAllComplaintsByAdmin(int pageNo, int pageSize);
}

