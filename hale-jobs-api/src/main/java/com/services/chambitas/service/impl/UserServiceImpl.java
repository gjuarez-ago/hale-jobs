package com.services.chambitas.service.impl;

import static com.services.chambitas.constant.UserImplConstant.EMAIL_ALREADY_EXISTS;
import static com.services.chambitas.constant.UserImplConstant.FOUND_USER_BY_USERNAME;
import static com.services.chambitas.constant.UserImplConstant.NO_USER_FOUND_BY_EMAIL;
import static com.services.chambitas.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;
import static com.services.chambitas.enumeration.Role.ROLE_USER;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.UserPrincipal;
import com.services.chambitas.domain.dto.CreateCompanyDTO;
import com.services.chambitas.domain.dto.UserCVDTO;
import com.services.chambitas.domain.dto.UserDTO;
import com.services.chambitas.enumeration.Role;
import com.services.chambitas.exception.domain.EmailExistException;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.exception.domain.UsernameExistException;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IUserService;
import com.services.chambitas.utility.EmailService;
import com.services.chambitas.utility.LoginAttemptService;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements IUserService, UserDetailsService {
	
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private IUserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		   User user = userRepository.findUserByUsername(username);
	        if (user == null) {
	            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
	            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
	        } else {
	            validateLoginAttempt(user);
	            user.setLastLoginDateDisplay(user.getLastLoginDate());
	            user.setLastLoginDate(new Date());
	            userRepository.save(user);
	            UserPrincipal userPrincipal = new UserPrincipal(user);
	            LOGGER.info(FOUND_USER_BY_USERNAME + username);
	            return userPrincipal;
	        }
	}

	
	@Override
	public User registerClient(UserDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public User createUserCV(UserCVDTO request) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User updateUserCV(Long userId, UserCVDTO request) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User addCvFileByUser(Long userId, MultipartFile cv) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User changeProfileView(Long userId, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User registerCompany(CreateCompanyDTO request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<User> getUsers() {
		  return userRepository.findAll();
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	
//	@Override
//	public User addNewUser(String names, String newMotherLastName, String newFatherLastName, String username, String email, String role, String gender, Date dateOfBirth,
//			boolean isNonLocked) throws UserNotFoundException,
//			UsernameExistException, EmailExistException, IOException, NotAnImageFileException, MessagingException {
//		
//		  validateUsername(username);
//		  User user = new User();
//	      String password = generatePassword();
//	      user.setNames(names);
//	      user.setSurnames(newFatherLastName + " "  + newMotherLastName);
//	      user.setMotherLastName(newMotherLastName);
//	      user.setFatherLastName(newFatherLastName);
//	      user.setJoinDate(new Date());
//	      user.setUsername(username);
//	      user.setPassword(encodePassword(password));
//	      user.setGender(gender);
//	      user.setDateOfBirth(dateOfBirth);
//	      user.setActive(true);
//	      user.setNotLocked(isNonLocked);
//	      user.setRole(getRoleEnumName(role).name());
//	      user.setAuthorities(getRoleEnumName(role).getAuthorities());
//	      userRepository.save(user);
//	      LOGGER.info("New user password: " + password);
//	      emailService.sendNewPasswordEmail(names, password, username);
//	      
//	      return user;  
//	}

	@Override
	public User updateUser(String currentUsername, String newFirstName, String motherName, String fatherName, String newUsername, String role, String gender, Date dateOfBirth, boolean isNonLocked, boolean isActive)
			throws UserNotFoundException, UsernameExistException, EmailExistException, IOException,
			NotAnImageFileException {
		
		    User currentUser = validateNewUsernameAndEmail(currentUsername, newUsername);
	        currentUser.setNames(newFirstName);
	        currentUser.setSurnames(fatherName + " "  + motherName);
	        currentUser.setMotherLastName(motherName);
	        currentUser.setFatherLastName(fatherName);
	        currentUser.setUsername(newUsername);
	        currentUser.setActive(isActive);
	        currentUser.setGender(gender);
	        currentUser.setDateOfBirth(dateOfBirth);
	        
	        currentUser.setNotLocked(isNonLocked);
	        currentUser.setRole(getRoleEnumName(role).name());
	        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
	        userRepository.save(currentUser);
	        return currentUser;
	}

	@Override
	public void deleteUser(String username) throws IOException {
		User user = userRepository.findUserByUsername(username);
//        Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
//        FileUtils.deleteDirectory(new File(userFolder.toString()));
        userRepository.deleteById(user.getId());
	}

	@Override
	public void resetPassword(String newPassword, String email, String token) throws MessagingException, EmailNotFoundException, GenericException {
	
		User user = userRepository.findUserByTokenAndUsername(token, email);
	
		if(user == null ) { 
	    	 throw new GenericException("El token es incorrecto o bien ya fue utilizado.");
		}
		
	    if(user.getExpireToken().before(new Date())) {
	    	 throw new GenericException("El token se encuentra expirado!");
	    }
	    
	    user.setToken("");
	    user.setPassword(encodePassword(newPassword));
	    userRepository.save(user);
	    
	}
     
	@Override
	public User updateProfile(String currentUsername, UserDTO request) throws UserNotFoundException {
		
		User element = validateUpdateUsername(currentUsername);
		element.setNames(request.getNames());
		element.setGender(request.getGender());
		element.setDateOfBirth(request.getDateOfBirth());
		userRepository.save(element);
		
		return element;
	}
	

	@Override
	public void recoveryPassword(String email) throws EmailNotFoundException, MessagingException {
		
		    User user = userRepository.findUserByUsername(email);
		    
	        if (user == null) {
	            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
	        }
	        
	        Calendar calendar = Calendar.getInstance();	
	        calendar.setTime(new Date()); 
	        calendar.add(Calendar.HOUR, 1);  
	        Date expireToken = calendar.getTime();
	        
	        String token = generatePassword();
	        user.setToken(token);
	        user.setExpireToken(expireToken);	        
	        userRepository.save(user);
	        
	        LOGGER.info("Token generate: " + token);
	        emailService.resetPassword(user.getNames(), token, user.getUsername());
	}

	@Override
	public User desactiveProfile(String currentUsername) throws UserNotFoundException {
		User element = validateUpdateUsername(currentUsername);
		element.setActive(false);		
		return element;
	}

	@Override
	public Page<User> getAllUsersPaginate(int pageNo, int pageSize, String username, String names, String surnames) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<User> response = userRepository.searchByFilters(username, names, surnames, pageable);	  
		return response;
	}
	
	@Override
	public User updatePermissionsByUsername(String username, List<Permission> permissions) {
	
	    User user = userRepository.findUserByUsername(username);
	    user.setPermissions(permissions);
		userRepository.save(user);
		
		return user;
	}

	@Override
	public List<Permission> findPermissionsByUsername(String username) {
		List<Permission> list =  userRepository.getPermissionsByUserName(username);
		return list;
	}
	

	private User validateUpdateUsername(String currentUsername) throws UserNotFoundException {
		
		 User currentUser = findUserByUsername(currentUsername);
		
		  if(StringUtils.isNotBlank(currentUsername)) { 
			  if(currentUser == null) {
			    	 throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			   }     
			}
		  
		return currentUser;
	}
	
	private User validateNewUsernameAndEmail(String currentUsername, String newUsername) throws UserNotFoundException, UsernameExistException, EmailExistException{
		
		   User userByNewUsername = findUserByUsername(newUsername);
			 
		  if(StringUtils.isNotBlank(currentUsername)) { 
			 User currentUser = findUserByUsername(currentUsername);
		     if(currentUser == null) {
		    	 throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
		     }
		     
		     if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
		    	 throw new UsernameExistException(EMAIL_ALREADY_EXISTS);
		     }
		      
		     return currentUser;
		  } else {
			  
		      if(userByNewUsername != null) {
			    	 throw new UsernameExistException(EMAIL_ALREADY_EXISTS);
			  }
			  
			  return userByNewUsername;
		  }  
    }
	
	
	private void validateUsername(String email) throws UsernameExistException {
		
		User user = userRepository.findUserByUsername(email);
		
		if(user != null) {
			 throw new UsernameExistException(EMAIL_ALREADY_EXISTS);
		}
		
		
	}
	
	private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }



    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(23);
    }

    private void validateLoginAttempt(User user) {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
    

	private String generateConsecutive() {

		String consecutive = "";
		long lastElement = userRepository.count();
		lastElement += 1;

		if (lastElement >= 0 && lastElement < 10) {
			consecutive = "100000000" + lastElement;
		}
		if (lastElement >= 10 && lastElement < 100) {
			consecutive = "10000000" + lastElement;
		}
		if (lastElement >= 100 && lastElement < 1000) {
			consecutive = "1000000" + lastElement;
		}
		if (lastElement >= 1000 && lastElement < 10000) {
			consecutive = "100000" + lastElement;
		}
		if (lastElement >= 10000 && lastElement < 100000) {
			consecutive = "10000" + lastElement;
		}
		if (lastElement >= 100000 && lastElement < 1000000) {
			consecutive = "1000" + lastElement;
		}
		if (lastElement >= 1000000 && lastElement < 10000000) {
			consecutive = "100" + lastElement;
		}
		if (lastElement >= 10000000 && lastElement < 100000000) {
			consecutive = "10" + lastElement;
		}
		if (lastElement >= 100000000 && lastElement < 1000000000) {
			consecutive = "1" + lastElement;
		}
		return consecutive;
	}




	


		
    
    

}
