package com.services.chambitas.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.dto.CreateCompanyDTO;
import com.services.chambitas.domain.dto.UserCVDTO;
import com.services.chambitas.domain.dto.UserDTO;
import com.services.chambitas.exception.domain.EmailExistException;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;

@Service
public interface IUserService {
	
	    /* Registro para un cliente */
	    User registerClient(UserDTO request);
	    
	    User registerCompany(CreateCompanyDTO request);
	    
	    // Servicio para crear CV
	    User createUserCV(UserCVDTO request);
	    
	    // Servicio para actualizar CV
	    User updateUserCV(Long userId,UserCVDTO request);
	    
	    // Servicio para adjuntar el CV (PDF, Word)
	    User addCvFileByUser(Long userId, MultipartFile cv);
	    
	    // Servicio para cambiar a perfil publico
	    User changeProfileView(Long userId, boolean value);
	    
	    // Restablecer contraseña
	    void resetPassword(String newPassword, String numberPhone, String token) throws MessagingException, EmailNotFoundException, GenericException;
	    
	    // Recuperar contraseña
	    void recoveryPassword(String token) throws MessagingException, EmailNotFoundException;
	   
	    // Listado de usuarios sin páginación
	    List<User> getUsers();
	    
	    // Listado de usuarios con páginación
	    Page<User> getAllUsersPaginate(int pageNo, int pageSize, String username, String names, String surnames);

	    // Buscar usuario por username
	    User findUserByUsername(String username);

	    // Desactivar profile
	    User desactiveProfile(String username) throws UserNotFoundException;
	    
	    // Actuaizar un usuario administrador
	    User updateProfile(String currentUsername, UserDTO request) throws UserNotFoundException;
	    
	    // Actualizar un usuario como administrador
	    User updateUser(String currentUsername, String newFirstName, String newMotherLastName, String newFatherLastName, String newUsername, String role, String gender, Date dateOfBirth, boolean isNonLocked, boolean isActive) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

	    // Eliminar usuario
	    void deleteUser(String username) throws IOException;
	    
	    // Actualizar permisos por usuario
	    User updatePermissionsByUsername(String username, List<Permission> permissions);
	    
	    // Listar permisos por usuario
	    List<Permission> findPermissionsByUsername(String username);
	   
	
}
