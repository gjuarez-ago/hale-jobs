package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface INotificationService {
		
	Notification createNotification(NotificationDTO request);
	
	Notification editNotificationById(String id, NotificationDTO request) throws GenericException;
	
	Notification deleteNotification(String id) throws GenericException;
	
	Notification findNotificationById(String id) throws GenericException;
	
	List<Notification> getAllNotificationsByUserMovil(String userId);
	
	Page<Notification> getAllNotificationsByUserWEB(String userId, int pageNo, int pageSize);
	
	Page<Notification> getAllNotificationByAdmin(int pageNo, int pageSize);	

}
