package com.services.chambitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.services.chambitas.domain.StateINEGI;

@Repository
public interface IStateRepository extends JpaRepository<StateINEGI, Long>{
	
	StateINEGI findStateINEGIByClave(String clave);

}
