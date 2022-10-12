package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.chambitas.domain.Complaints;

public interface IComplaintRepository extends JpaRepository<Complaints, Long>{

	
}
