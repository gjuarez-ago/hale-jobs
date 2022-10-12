package com.services.chambitas.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.UserDTO;
import com.services.chambitas.exception.domain.EmailExistException;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;

@Service
public interface IUserService {
	
	    User register(String numberPhone, String email, String password) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

	    List<User> getUsers();
	    
	    Page<User> getAllUsersPaginate(int pageNo, int pageSize, String username, String names, String surnames);

	    User findUserByUsername(String username);

	    User desactiveProfile(String username) throws UserNotFoundException;
	    
	    User updateProfile(String currentUsername, UserDTO request) throws UserNotFoundException;

	    User addNewUser(String firstName, String newMotherLastName, String newFatherLastName, String username, String email ,  String role, String gender, Date dateOfBirth,  boolean isNonLocked) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException, MessagingException;

	    User updateUser(String currentUsername, String newFirstName, String newMotherLastName, String newFatherLastName, String newUsername, String email, String role, String gender, Date dateOfBirth, boolean isNonLocked, boolean isActive) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

	    void deleteUser(String username) throws IOException;

	    void resetPassword(String newPassword, String numberPhone, String token) throws MessagingException, EmailNotFoundException, GenericException;
	    
	    void recoveryPassword(String token) throws MessagingException, EmailNotFoundException;
	    
	    User updatePermissionsByUsername(String username, List<Permission> permissions);
	    
	    List<Permission> findPermissionsByUsername(String username);
	   
	
}
