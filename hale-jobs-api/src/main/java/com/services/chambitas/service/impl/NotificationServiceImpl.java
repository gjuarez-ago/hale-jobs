package com.services.chambitas.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Notification;
import com.services.chambitas.domain.dto.NotificationDTO;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.repository.INotificationRepository;
import com.services.chambitas.service.INotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements INotificationService{

	@Autowired
	INotificationRepository notificationRepository;
	
	@Override
	public Notification createNotification(NotificationDTO request) {
		
		Notification element =  new Notification();
		
		element.setConsecutive(generateConsecutive());
		element.setContent(request.getContent());
		element.setTitle(request.getTitle());
		element.setEmailDestination(request.getEmailDestination());
		element.setRelatedRequest(request.getRelatedRequest());
		element.setTypeAD(request.getTypeAD());
		
		element.setRegDateCreated(new Date());
		element.setRegCreatedBy(request.getRegCreatedBy());
		element.setRegBorrado(0);
		
		notificationRepository.save(element);
	
		return element;
	}

	@Override
	public Notification editNotificationById(String id, NotificationDTO request) throws GenericException {
		
		Notification element = existNotification(id);
		
		element.setTitle(request.getTitle());
		element.setContent(request.getContent());
		element.setEmailDestination(request.getEmailDestination());
		element.setRelatedRequest(request.getRelatedRequest());
		element.setRegUpdateBy(request.getRegUpdateBy());
		element.setRegDateUpdated(new Date());
		element.setTypeAD(id);
		
		notificationRepository.save(element);
		
		return element;
		
	}

	@Override
	public Notification deleteNotification(String id) throws GenericException {
		
		Notification element = existNotification(id);
		element.setRegBorrado(1);
		notificationRepository.save(element);
		return element;
	}

	@Override
	public Notification findNotificationById(String id) throws GenericException {
	
		Notification element = existNotification(id);
		return element;
	}

	@Override
	public List<Notification> getAllNotificationsByUserMovil(String userId) {
	   List<Notification> list = notificationRepository.findNotificationByUserMovil(userId);
		return list;
	}

	@Override
	public Page<Notification> getAllNotificationsByUserWEB(String userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Notification> response = notificationRepository.findNotificationByUserWEB(userId, pageable);
		return response;
	}

	@Override
	public Page<Notification> getAllNotificationByAdmin(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Notification> response = notificationRepository.findNotificationByAdmin(pageable);
		return response;
	}
	
	// Private methods
	private Notification existNotification(String id) throws GenericException {
		
		Notification element = notificationRepository.findNotificationById(id);
		if(element == null) {
			throw new GenericException("No se encontro la notificación");
		}
		
		return element;
	}
	

	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = notificationRepository.count();
	    lastElement += 1;
	    
	    if(lastElement >= 0 && lastElement < 10) {consecutive = "100000000"  + lastElement;}
		if(lastElement >= 10 && lastElement < 100) {consecutive = "10000000"  + lastElement;}
		if(lastElement >= 100 && lastElement < 1000) {consecutive = "1000000"  + lastElement;}
		if(lastElement >= 1000 && lastElement < 10000) {consecutive = "100000"  + lastElement;}
		if(lastElement >= 10000 && lastElement < 100000) {consecutive = "10000"  + lastElement;}
		if(lastElement >= 100000 && lastElement < 1000000) {consecutive = "1000"  + lastElement;}
		if(lastElement >= 1000000 && lastElement < 10000000) {consecutive = "100"  + lastElement;}
		if(lastElement >= 10000000 && lastElement < 100000000) {consecutive = "10"  + lastElement;}
		if(lastElement >= 100000000 && lastElement < 1000000000) {consecutive = "1"  + lastElement;}
		return consecutive;
	}
	
	
	

}
