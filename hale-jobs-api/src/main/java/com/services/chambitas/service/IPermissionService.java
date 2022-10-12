package com.services.chambitas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.exception.domain.GenericException;

@Service
public interface IPermissionService {
	
	    // Get Permission
		List<Permission> getAll();
		
		// Get all Permissions paginate
		Page<Permission> getAllP(int pageNo, int pageSize, String key, String description);
		
		// Add Permission
		Permission addPermission(String keyPermission, String description) throws GenericException;
		
		// Update Permission
		Permission updatePermission(String currentKeyPermission,String keyPermission, String description) throws GenericException;
		
		// Delete Permission
		Permission deletePermission(String keyPermission) throws GenericException;
		
		// Get Permission by keyPermisssion
		Permission findPermissionByKey(String keyPermission) throws GenericException;
		
		
	
}