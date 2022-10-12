package com.services.chambitas.service.impl;

import static com.services.chambitas.constant.FileConstant.DIRECTORY_CREATED;
import static com.services.chambitas.constant.FileConstant.DOT;
import static com.services.chambitas.constant.FileConstant.FILE_SAVED_IN_FILE_SYSTEM;
import static com.services.chambitas.constant.FileConstant.IMAGES_FOLDER;
import static com.services.chambitas.constant.FileConstant.JPG_EXTENSION;
import static com.services.chambitas.constant.FileConstant.NOT_AN_IMAGE_FILE;
import static com.services.chambitas.constant.FileConstant.PRODUCT_IMAGE_PATH;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.services.chambitas.domain.Complaints;
import com.services.chambitas.domain.File;
import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.TypeOfJob;
import com.services.chambitas.domain.TypeOfPayment;
import com.services.chambitas.domain.User;
import com.services.chambitas.exception.domain.GenericException;
import com.services.chambitas.exception.domain.NotAnImageFileException;
import com.services.chambitas.repository.IComplaintRepository;
import com.services.chambitas.repository.IFileRepository;
import com.services.chambitas.repository.IOfferRepository;
import com.services.chambitas.repository.ITypeOfJobRepository;
import com.services.chambitas.repository.ITypeOfPaymentRepository;
import com.services.chambitas.repository.IUserRepository;
import com.services.chambitas.service.IOfferService;


@Service
@Transactional
public class OfferServiceImpl implements IOfferService{
	
	
	@Autowired
	IOfferRepository offerRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IFileRepository fileRepository;
	
	@Autowired 
	ITypeOfPaymentRepository TypeOfPaymentRepository;
	
	@Autowired
	ITypeOfJobRepository typeOfJobRepository;
	
	@Autowired
	IComplaintRepository complaintRepository;
	
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());


	@Override
	public Offer createOffer(double amountOffered, String title, String description, boolean status, String urgency,
			String state, String city, String address, MultipartFile[] images, String typeOfJob,String typeOfPayment, String userId) throws NotAnImageFileException, IOException, GenericException {
		
		
		List<File> list = new ArrayList<>();
		
		Offer element = new Offer();
		
		for(MultipartFile file : images) {
			if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(file.getContentType())) {
                throw new NotAnImageFileException(file.getOriginalFilename() + NOT_AN_IMAGE_FILE);
            }
		}
		
		for(MultipartFile file :  images) {
		    File entityFile = new File();
			File entity = saveImage(userId,entityFile, file);
			list.add(entity);
		}
		
		// Entidades
		TypeOfPayment tpyePayment = existTypePayment(typeOfPayment);
		TypeOfJob typeJob = existTypeJob(typeOfJob);
		User user = existUser(userId);
		
		element.setConsecutive(generateConsecutive());
		element.setTitle(title);
		element.setDescription(description); // Descripción de la chambita
		element.setImages(list); // Imagenes de la chambita
		element.setStatus(1); // Estatus de la solicitud (Creada)
		element.setUser(user);
		element.setUrgency(urgency);
		element.setHaveComplaint(false);
		element.setTypeOfPayment(tpyePayment);
		element.setTypeOfJob(typeJob);
		element.setAmountOffered(amountOffered);
		
		element.setState(state);
		element.setCity(city); 
		element.setAddress(address);
		
		element.setRegDateCreated(new Date());
		element.setRegCreatedBy(state);
		element.setRegBorrado(0);
		
		
		return element;
	}

	@Override
	public Offer editOffer(String offerId, double amountOffered, String description, String urgency, String address,String typeOfPayment, String userId) throws GenericException {
	
		Offer element = exisOffer(offerId);
		
		TypeOfPayment tpyePayment = existTypePayment(typeOfPayment);
		User user = existUser(userId);
		
		element.setDescription(description);
		element.setAmountOffered(amountOffered);
		element.setUrgency(urgency);
		element.setAddress(address);
		element.setTypeOfPayment(tpyePayment);
		element.setUser(user);
		
		offerRepository.save(element);
		
		return element;
	}

	@Override
	public Offer deleteOfferById(String id, String userId) throws GenericException {
		
		Offer element = exisOffer(id);
		
		element.setRegBorrado(1);
		element.setRegDateUpdated(new Date());
		element.setRegUpdateBy(userId);
		
		offerRepository.save(element);
		
		return element;
	}

	@Override
	public Offer findOfferById(String id) throws GenericException {
		Offer element = exisOffer(id);
		return element;
	}

	@Override
	public Offer reportOffer(String id, String comment, String category, String userId) throws GenericException {
		
		Offer element = exisOffer(id);
		User user = existUser(userId);
		
		Complaints complaint = new Complaints();
		
		complaint.setComments(comment);
		complaint.setOfferId(id);
		complaint.setRegCreatedBy(user.getConsecutive());
		complaint.setUserId(user.getConsecutive());
		complaint.setRegDateCreated(new Date());
		complaint.setTitle(category);
		
		element.setHaveComplaint(true); // La oferta ha sido reportada
		
		complaintRepository.save(complaint);
		offerRepository.save(element);

		return element;
	}

	@Override
	public Offer selectPostulate(String id, String userPostulate, double amountAcepted) throws GenericException {
		
		Offer element = exisOffer(id);
		
		User user = existUser(userPostulate);
		
		element.setStatus(2);
		element.setUserSelected(user);
		element.setAmountAcepted(amountAcepted);
		
		offerRepository.save(element);
		
		return element;
	}

	@Override
	public List<Offer> getAllOfferByUserMovil(String userId) {
	    List<Offer> list = offerRepository.findOfferByUserMovil(userId);
		return list;
	}

	@Override
	public Page<Offer> getAllOfferByUserWEB(String userId, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.findOfferByUserWEB(userId, pageable);
		return response;	
	}

	@Override
	public List<Offer> findOfferGeneralMovil(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Offer> findOfferGeneralWEB(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.findOfferGeneralWEB(keyword, pageable);
		return response;	
	}

	@Override
	public Page<Offer> getAllOfferByAdmin(String keyword,int pageNo, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNo, pageSize);   
		Page<Offer> response = offerRepository.findOfferAdmin(keyword, pageable);
		return response;
	}
	
	

	// private methods
	private File saveImage(String id, File entity,MultipartFile profileImage) throws IOException, NotAnImageFileException {
        if (profileImage != null) {
           
       	final String uuid = UUID.randomUUID().toString().toLowerCase();
       	  
       	Path userFolder = Paths.get(IMAGES_FOLDER + uuid).toAbsolutePath().normalize();
       	  
           if(!Files.exists(userFolder)) {
                 Files.createDirectories(userFolder);
                 LOGGER.info(DIRECTORY_CREATED + userFolder);
           }
             
           Files.deleteIfExists(Paths.get(userFolder + uuid + DOT + JPG_EXTENSION));
           Files.copy(profileImage.getInputStream(), userFolder.resolve(uuid + DOT + JPG_EXTENSION), REPLACE_EXISTING);
           entity.setRouteFile(setProfileImageUrl(uuid));
           entity.setNameFile(uuid);
           entity.setNameEntity("PRODUCT_" + id);
           entity.setRegCreatedBy("");
           entity.setRegDateCreated(new Date());
           fileRepository.save(entity);
           LOGGER.info(FILE_SAVED_IN_FILE_SYSTEM + profileImage.getOriginalFilename());
       }
		return entity;
    }
	
	
	
	private String setProfileImageUrl(String uuid) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(PRODUCT_IMAGE_PATH + uuid + "/"
        + uuid + DOT + JPG_EXTENSION).toUriString();
    }
	
	
	private TypeOfJob existTypeJob(String clave) throws GenericException {
		TypeOfJob element = typeOfJobRepository.findTypeOfJobByClave(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	private TypeOfPayment existTypePayment(String clave) throws GenericException {
		TypeOfPayment element = TypeOfPaymentRepository.findTypeOfPaymentByClave(clave);
		
		if(element == null) {
			throw new GenericException("No se encontro el recurso");
		}
		
		return element;
	}
	
	private User existUser(String consecutive) throws GenericException {
		User user = userRepository.findUserByConsecutive(consecutive);
		if(user == null) {throw new GenericException("No se encontro el usuario");}
		return user;
	}
	
	private Offer exisOffer(String consecutive) throws GenericException {
		Offer offer = offerRepository.findOfferById(consecutive);
		if(offer ==  null) {throw new GenericException("No se encontro la oferta");}
		return offer;
	}
	
	private String generateConsecutive() {
		
		String consecutive = "";
		long lastElement = offerRepository.count();
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
