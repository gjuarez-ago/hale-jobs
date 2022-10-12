package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.services.chambitas.domain.File;

@Repository
public interface IFileRepository extends JpaRepository<File, Long> {
	
	File findFileByConsecutive(String consecutive);
	
	
	
}
