package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.dto.TypeOfJobDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ITypeOfJobService {
	
	// Crear tipo de trabajo
	TypeOfJob createTypeOfJob(TypeOfJobDTO request);
	
	// Editar tipo de trabajo
	TypeOfJob editTypeOfJob(String id, TypeOfJobDTO request) throws GenericException;
	
	// Eliminar tipo de trabajo
	TypeOfJob deleteTypeOfJob(String id) throws GenericException;
	
	// Buscar tipo de trabjo
	TypeOfJob findTypeOfJob(String id) throws GenericException;
	
	// Obtener listado de tipos de trabajo
    Page<TypeOfJob> getAllTypeOfJob(int pageNo, int pageSize);

}
