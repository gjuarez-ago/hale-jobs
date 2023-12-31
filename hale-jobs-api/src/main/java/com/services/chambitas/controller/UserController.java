package com.services.chambitas.controller;

import static com.services.chambitas.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.services.chambitas.domain.HttpResponse;
import com.services.chambitas.domain.Permission;
import com.services.chambitas.domain.User;
import com.services.chambitas.domain.UserPrincipal;
import com.services.chambitas.domain.dto.LoginDTO;
import com.services.chambitas.domain.dto.RecoveryPasswordDTO;
import com.services.chambitas.domain.dto.ResetPasswordDTO;
import com.services.chambitas.domain.dto.UserDTO;
import com.services.chambitas.exception.domain.EmailNotFoundException;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.UserNotFoundException;
import com.services.chambitas.service.IUserService;
import com.services.chambitas.utility.JWTTokenProvider;

@RestController
@RequestMapping(path = { "/user"})
public class UserController {
	
	  public static final String EMAIL_SENT = "An email with a new password was sent to: ";
	  public static final String PASSWORD_RESET = "Contraseña restablecida correctamente";
	  public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	  private AuthenticationManager authenticationManager;
	  private IUserService userService;
	  private JWTTokenProvider jwtTokenProvider;

	  @Autowired
	    public UserController(AuthenticationManager authenticationManager, IUserService userService, JWTTokenProvider jwtTokenProvider) {
	        this.authenticationManager = authenticationManager;
	        this.userService = userService;
	        this.jwtTokenProvider = jwtTokenProvider;
	  }
	  
	  @PostMapping("/login")
	  public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO user) throws JsonMappingException, JsonProcessingException {

		authenticate(user.getUsername(), user.getPassword());
			
		User loginUser = userService.findUserByUsername(user.getUsername());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);

			// Is supplier
		String token = jwtHeader.get("Jwt-Token").get(0);
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("token",token);
		result.put("user",loginUser);

		 return new ResponseEntity<>(result, jwtHeader, HttpStatus.OK);
		}
		
//	   @PostMapping("/register")
//	    public ResponseEntity<User> register(@RequestBody RegisterDTO user) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
//	        User newUser = userService.registerClient(user.getUsername(), user.getEmail() ,user.getPassword());
//	        return new ResponseEntity<>(newUser, OK);
// 	   }
	   
	   @DeleteMapping("/desactivate-profile/{username}")
	   public ResponseEntity<User> desactivateProfile(@PathVariable("username") String username) throws UserNotFoundException {
		   User response = userService.desactiveProfile(username);
		   return new ResponseEntity<User>(response, HttpStatus.OK);
	   }
	   
	

//	    @PostMapping("/update")
//	    public ResponseEntity<User> update(@RequestParam("currentUsername") String currentUsername,
//	                                       @RequestParam("names") String firstName,
//	                                       @RequestParam("motherName") String mName,
//	                                       @RequestParam("fatherName") String fName,
//	                                       @RequestParam("username") String username,
//	                                       @RequestParam("email") String email,
//	                                       @RequestParam("role") String role,
//	                                       @RequestParam("gender") String gender,
//                                           @RequestParam("dateOfBirth") Date dateOfBirth,
//	                                       @RequestParam("isActive") String isActive,
//	                                       @RequestParam("isNonLocked") String isNonLocked) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {
//	        User updatedUser = userService.updateUser(currentUsername, firstName, mName, fName, username,email, role,gender, dateOfBirth, Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive));
//	        return new ResponseEntity<>(updatedUser, OK);
//	    }
//	    
	    
	    @PostMapping("/update-profile/{username}")
	    public ResponseEntity<User> updateProfile(@PathVariable("username") String username, @RequestBody UserDTO request) throws UserNotFoundException {
	    	User response = userService.updateProfile(username, request);
	    	return new ResponseEntity<User>(response, HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/find/{username}")
	    public ResponseEntity<User> getUser(@PathVariable("username") String username) throws UserNotFoundException {
	        User user = userService.findUserByUsername(username);
	        return new ResponseEntity<>(user, OK);
	    }

	    @GetMapping("/list")
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = userService.getUsers();
	        return new ResponseEntity<>(users, OK);
	    }
	    
	    @GetMapping("/paginate") 
		public ResponseEntity<Page<User>> paginate(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
	            @RequestParam(value = "username", defaultValue = "", required = false) String username,
	            @RequestParam(value = "names", defaultValue = "", required = false) String names,
	            @RequestParam(value = "surnames", defaultValue = "", required = false) String surnames) {
			Page<User> response = userService.getAllUsersPaginate(pageNo, pageSize, username, names, surnames);
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}

	    @PostMapping("/recovery-password")
	    public ResponseEntity<HttpResponse> recoveryPassword(@RequestBody RecoveryPasswordDTO request) throws MessagingException, EmailNotFoundException {
	        userService.recoveryPassword(request.getEmail());
	        return response(OK, EMAIL_SENT + request.getEmail());
	    }
	    
	    @PostMapping("/reset-password")
	    public ResponseEntity<HttpResponse> resetPassword(@RequestBody ResetPasswordDTO request) throws MessagingException, EmailNotFoundException, GenericException {
	    	userService.resetPassword(request.getPassword(), request.getEmail(), request.getToken());
	    	 return response(OK, PASSWORD_RESET);
	    }
	    
	    @DeleteMapping("/delete/{username}")
	    @PreAuthorize("hasAnyAuthority('user:delete')")
	    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) throws IOException {
	        userService.deleteUser(username);
	        return response(OK, USER_DELETED_SUCCESSFULLY);
	    }
	    

		@PostMapping("/update-permissions/{username}")
		public ResponseEntity<User> updatePermissions(@PathVariable("username") String username,
				@RequestBody List<Permission> permissions) {
			User response = userService.updatePermissionsByUsername(username, permissions);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		@GetMapping("/list-permissions/{username}")
		public ResponseEntity<List<Permission>> getPermissionByUsername(@PathVariable("username") String username) {
			List<Permission> response = userService.findPermissionsByUsername(username);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}



	    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
	        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
	                message), httpStatus);
	    }

	    private HttpHeaders getJwtHeader(UserPrincipal user) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
	        return headers;
	    }

	    private void authenticate(String username, String password) {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    }

}