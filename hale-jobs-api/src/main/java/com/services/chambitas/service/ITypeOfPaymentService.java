package com.services.chambitas.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.dto.TypeOfPaymentDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ITypeOfPaymentService {
	
	
	// Crear tipo de pago
    TypeOfPayment createTypeOfPayment(TypeOfPaymentDTO request);
	
    // Editar tipo de pago
    TypeOfPayment editTypeOfPayment(String id, TypeOfPaymentDTO request) throws GenericException;
	
    // Eliminar tipo de pago
	TypeOfPayment deleteTypeOfPayment(String id) throws GenericException;
	
	// Buscar tipo de pago
	TypeOfPayment findTypeOfPayment(String id) throws GenericException;
	
	// Mostrar listado de pagos administrador
    Page<TypeOfPayment> getAllTypeOfPayment(int pageNo, int pageSize);
    
}
