package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface INotificationService {
		
	// Crear notificación
	Notification createNotification(NotificationDTO request);
	
	// Editar notificación
	Notification editNotificationById(String id, NotificationDTO request) throws GenericException;
	
	// Eliminar notificación
	Notification deleteNotification(String id) throws GenericException;
	
	// Visualizar notificación por ID
	Notification findNotificationById(String id) throws GenericException;
	
	// Ver notificaciones por usuario móvil
	List<Notification> getAllNotificationsByUserMovil(String userId);
	
	// Ver notificaciones por usuario WEB
	Page<Notification> getAllNotificationsByUserWEB(String userId, int pageNo, int pageSize);
	
	// Visualizar notificaciones general por administrador
	Page<Notification> getAllNotificationByAdmin(int pageNo, int pageSize);
	

}
