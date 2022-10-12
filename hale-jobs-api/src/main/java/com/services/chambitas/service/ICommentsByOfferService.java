package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.CommentsOffer;
import com.services.chambitas.domain.dto.CommentsOfferDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface ICommentsByOfferService {
	
	// Crear comentario
	CommentsOffer createComment(CommentsOfferDTO request) throws GenericException;
	
	// Editar comentario
	CommentsOffer editCommentById(String id, CommentsOfferDTO request) throws GenericException;
	
	// Buscar comentario por ID
	CommentsOffer findCommentById(String id) throws GenericException;
	
	// Eliminar comentario por ID
	CommentsOffer deleteCommentById(String id) throws GenericException;
	
	// Obtener todos los comentarios por oferta versión movil
	List<CommentsOffer> getAllCommentsByOfferMovil(String id);
	
	// Obtener todos los comentarios por oferta versión web
	Page<CommentsOffer> getAllCommentsByOfferWEB(String id, int pageNo, int pageSize);
	
	// Obtener todos los comentarios vistar por el administrador.
	Page<CommentsOffer> getAllCommentsWebAdmin(String keyword, int pageNo, int pageSize);	

}
